package org.firstinspires.ftc.teamcode.opmodes.robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import  org.firstinspires.ftc.teamcode.opmodes.robotx.modules.DuckRotation;
import  org.firstinspires.ftc.teamcode.opmodes.robotx.modules.IntakeSystem;
import  org.firstinspires.ftc.teamcode.opmodes.robotx.modules.LiftSystem;
import  org.firstinspires.ftc.teamcode.opmodes.robotx.modules.MecanumDrive;
import  org.firstinspires.ftc.teamcode.opmodes.robotx.modules.OrientationDrive;

@Autonomous(name = "EncoderLiftTest", group = "Default")

public class EncoderLiftTest extends LinearOpMode {

    //private ElapsedTime runtime = new ElapsedTime();

    //Modules being imported
    MecanumDrive mecanumDrive;
    DuckRotation duckRotation;
    IntakeSystem intakeSystem;
    OrientationDrive orientationDrive;
    LiftSystem liftSystem;

    public Servo duckServo;


    @Override

    public void runOpMode() {

        //Text at bottom of phone
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        mecanumDrive = new MecanumDrive(this);
        mecanumDrive.init();

        duckRotation = new DuckRotation(this);
        duckRotation.init();

        intakeSystem = new IntakeSystem(this);
        intakeSystem.init();

        orientationDrive = new OrientationDrive(this);
        orientationDrive.init();

        liftSystem = new LiftSystem(this);
        liftSystem.init();

        mecanumDrive.start();
        duckRotation.start();
        intakeSystem.start();
        orientationDrive.start();

        mecanumDrive.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData(">", "Press Play to Start Op Mode");
        telemetry.update();

        waitForStart();
        //runtime.reset();

        if (opModeIsActive()) {

            DcMotor LiftMotor;
            DcMotor LiftMotor2;

            LiftMotor = hardwareMap.dcMotor.get("LiftMotor");
            LiftMotor2 = hardwareMap.dcMotor.get("LiftMotor2");


            // reset encoder counts kept by motors.
            LiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LiftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // set motors to run forward for 5000 encoder counts.
            LiftMotor.setTargetPosition(1600);
            LiftMotor2.setTargetPosition(1600);

            // set motors to run to target encoder position and stop with brakes on.
            LiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LiftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.

            waitForStart();

            telemetry.addData("Mode", "running");
            telemetry.update();

            // set both motors to 25% power. Movement will start. Sign of power is
            // ignored as sign of target encoder position controls direction when
            // running to position.

            LiftMotor.setPower(0.25);
            LiftMotor2.setPower(0.25);

            // wait while opmode is active and left motor is busy running to position.

            while (opModeIsActive() && LiftMotor.isBusy())   //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
            {
                telemetry.addData("encoder-fwd-left", LiftMotor.getCurrentPosition() + "  busy=" + LiftMotor.isBusy());
                telemetry.addData("encoder-fwd-right", LiftMotor2.getCurrentPosition() + "  busy=" + LiftMotor2.isBusy());
                telemetry.update();
                idle();
            }

            // set motor power to zero to turn off motors. The motors stop on their own but
            // power is still applied so we turn off the power.

            LiftMotor.setPower(0.0);
            LiftMotor2.setPower(0.0);

            // wait 5 sec to you can observe the final encoder position.

            resetStartTime();

            while (opModeIsActive() && getRuntime() < 5) {
                telemetry.addData("encoder-fwd-left-end", LiftMotor.getCurrentPosition());
                telemetry.addData("encoder-fwd-right-end", LiftMotor2.getCurrentPosition());
                telemetry.update();
                idle();
            }

            // From current position back up to starting point. In this example instead of
            // having the motor monitor the encoder we will monitor the encoder ourselves.

            LiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            LiftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            LiftMotor.setTargetPosition(0);
            LiftMotor2.setTargetPosition(0);

            // Power sign matters again as we are running without encoder.
            LiftMotor.setPower(-0.25);
            LiftMotor2.setPower(-0.25);

            while (opModeIsActive() && LiftMotor.getCurrentPosition() > LiftMotor.getTargetPosition()) {
                telemetry.addData("encoder-back-left", LiftMotor.getCurrentPosition());
                telemetry.addData("encoder-back-right", LiftMotor2.getCurrentPosition());
                telemetry.update();
                idle();
            }

            // set motor power to zero to stop motors.

            LiftMotor.setPower(0.0);
            LiftMotor2.setPower(0.0);

            resetStartTime();

            while (opModeIsActive() && getRuntime() < 5) {
                telemetry.addData("encoder-back-left-end", LiftMotor.getCurrentPosition());
                telemetry.addData("encoder-back-right-end", LiftMotor2.getCurrentPosition());
                telemetry.update();
                sleep(1000);
            }

        }

    }
}