package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.util.Alliance;
import com.technototes.library.util.MapUtils;
import com.technototes.path.geometry.ConfigurablePose;
import com.technototes.path.subsystem.DistanceSensorLocalizer;
import com.technototes.path.subsystem.MecanumConstants;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.RobotConstants;

import java.util.function.Supplier;

import static org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem.DriveConstants.FRONT_SENSOR_POSE;
import static org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem.DriveConstants.LEFT_SENSOR_POSE;
import static org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem.DriveConstants.RIGHT_SENSOR_POSE;
import static org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem.DriveConstants.TIP_AUTHORITY;
import static org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem.DriveConstants.TIP_TOLERANCE;

import android.util.Pair;

@SuppressWarnings("unused")

public class DrivebaseSubsystem extends MecanumDrivebaseSubsystem implements Supplier<Pose2d> {

    @Config
    public abstract static class DriveConstants implements MecanumConstants {

        @TicksPerRev
        public static final double TICKS_PER_REV = 28;

        @MaxRPM
        public static final double MAX_RPM = 6000;

        @UseDriveEncoder
        public static final boolean RUN_USING_ENCODER = true;

        @MotorVeloPID
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(25, 0, 4,
                MecanumConstants.getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

        @WheelRadius
        public static double WHEEL_RADIUS = 1.88976; // in
        @GearRatio
        public static double GEAR_RATIO = 1 / 13.7; // output (wheel) speed / input (motor) speed
        @TrackWidth
        public static double TRACK_WIDTH = 9; // in
        @WheelBase
        public static double WHEEL_BASE = 8.5;
        @KV
        public static double kV = 1.0 / MecanumConstants.rpmToVelocity(MAX_RPM, WHEEL_RADIUS, GEAR_RATIO);
        @KA
        public static double kA = 0;
        @KStatic
        public static double kStatic = 0;

        @MaxVelo
        public static double MAX_VEL = 60;
        @MaxAccel
        public static double MAX_ACCEL = 50;
        @MaxAngleVelo
        public static double MAX_ANG_VEL = Math.toRadians(180);
        @MaxAngleAccel
        public static double MAX_ANG_ACCEL = Math.toRadians(90);

        @TransPID
        public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(8, 0, 0);
        @HeadPID
        public static PIDCoefficients HEADING_PID = new PIDCoefficients(4, 0, 0);

        @LateralMult
        public static double LATERAL_MULTIPLIER = 1;

        @VXWeight
        public static double VX_WEIGHT = 1;
        @VYWeight
        public static double VY_WEIGHT = 1;
        @OmegaWeight
        public static double OMEGA_WEIGHT = 1;

        @PoseLimit
        public static int POSE_HISTORY_LIMIT = 100;

        public static double TIP_TOLERANCE = Math.toRadians(10);

        public static double TIP_AUTHORITY = 0.9;

        public static ConfigurablePose LEFT_SENSOR_POSE = new ConfigurablePose(0.5, -5.5, Math.toRadians(-90));
        public static ConfigurablePose RIGHT_SENSOR_POSE = new ConfigurablePose(0.5, 5.5, Math.toRadians(90));
        public static ConfigurablePose FRONT_SENSOR_POSE = new ConfigurablePose(-5.7, -2.5, Math.toRadians(180));


    }

    public Rev2MDistanceSensor left, right, front;
//    protected FtcDashboard dashboard;

    public float xOffset, yOffset;

    public DistanceSensorLocalizer distanceSensorLocalizer;

    public DrivebaseSubsystem(EncodedMotor<DcMotorEx> fl, EncodedMotor<DcMotorEx> fr,
                              EncodedMotor<DcMotorEx> rl, EncodedMotor<DcMotorEx> rr,
                              IMU i, Rev2MDistanceSensor l, Rev2MDistanceSensor r, Rev2MDistanceSensor f) {
        super(fl, fr, rl, rr, i, () -> DriveConstants.class);

        distanceSensorLocalizer = new DistanceSensorLocalizer(i, MapUtils.of(
                        new Pair<>(l, LEFT_SENSOR_POSE.toPose()),
                        new Pair<>(r, RIGHT_SENSOR_POSE.toPose()),
                        new Pair<>(f, FRONT_SENSOR_POSE.toPose())));
        left = l;
        right = r;
        front = f;

        resetGyro();
//        dashboard = FtcDashboard.getInstance();
//        dashboard.setTelemetryTransmissionInterval(25);
    }

    public DrivebaseSubsystem(Hardware hardware){
        this(hardware.flDriveMotor, hardware.frDriveMotor, hardware.rlDriveMotor, hardware.rrDriveMotor, hardware.imu, hardware.leftRangeSensor, hardware.rightRangeSensor, hardware.frontRangeSensor);
    }


    public void relocalize(){
        distanceSensorLocalizer.update(getPoseEstimate());
        setPoseEstimate(distanceSensorLocalizer.getPoseEstimate());
    }
    public void relocalizeUnsafe(){
        distanceSensorLocalizer.update();
        setPoseEstimate(distanceSensorLocalizer.getPoseEstimate());
    }

    public void resetGyro(){
        Orientation i = imu.getAngularOrientation();
        xOffset = i.secondAngle;
        yOffset = i.thirdAngle;
        distanceSensorLocalizer.setGyroOffset(i.firstAngle-Math.toRadians(RobotConstants.getAlliance().selectOf(-90, 90)));

    }

    public void setSafeDrivePower(Pose2d raw){
        Orientation i = imu.getAngularOrientation();
        float x = 0, y = 0, adjX = xOffset-i.secondAngle, adjY = i.thirdAngle-yOffset;
        if(Math.abs(adjY) > TIP_TOLERANCE) y = adjY;
        if(Math.abs(adjX) > TIP_TOLERANCE) x = adjX;
//        setWeightedDrivePower(raw.plus(new Pose2d(x>0 ? Math.max(x-TIP_TOLERANCE, 0) : Math.min(x+TIP_TOLERANCE, 0), y>0 ? Math.max(y-TIP_TOLERANCE, 0) : Math.min(y+TIP_TOLERANCE, 0), 0)));
        setWeightedDrivePower(raw.plus(new Pose2d(Range.clip(x*2, -TIP_AUTHORITY, TIP_AUTHORITY), Range.clip(y*2, -TIP_AUTHORITY, TIP_AUTHORITY), 0)));
    }



    @Override
    public Pose2d get() {
        return getPoseEstimate();
    }



}
