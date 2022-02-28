package org.firstinspires.ftc.teamcode.opmodes.robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.opmodes.robotx.opmodes.autonomous.Opencv;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;


@Autonomous(name = "EncoderSpin")
@Disabled

public class EncoderTester extends LinearOpMode {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    MecanumDrive mecanumDrive;

    public EncoderTester (Opencv opencv) {
    }


    @Override
    public void runOpMode() throws InterruptedException  {
        //front left motor
        frontLeft = opMode.hardwareMap.dcMotor.get("frontLeft");

        frontRight = opMode.hardwareMap.dcMotor.get("frontRight");
        // frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //back left motor
        backLeft = opMode.hardwareMap.dcMotor.get("backLeft");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);             //reverse for same reason as above

        //back right motor
        backRight = opMode.hardwareMap.dcMotor.get("backRight");
        //backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Different Encoder Commands'
        //frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //frontLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);

        //frontLeft.getCurrentPosition();
        //frontLeft.setTargetPosition(foo);
        //frontLeft.isBusy();


        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //wait for start
        waitForStart();

        // Find Value for Rev Encoder Ticks
        int REV_TICKS_PER_REV = 300;
    }

    public void DriveForwardDistance(double power, int distance) {
        // Reset encoders
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        frontLeft.setTargetPosition(distance);
        frontRight.setTargetPosition(distance);
        backLeft.setTargetPosition(distance);
        backRight.setTargetPosition(distance);

        // Set to RUN_TO_POSITION_MODE
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set drive power
        DriveForward(power);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() )
        {
            // Wait until target position is reached
        }

        // Stop and change modes back to normal
        StopDriving();
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void DriveBackwardDistance(double power, int distance) {
        // Reset encoders
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        frontLeft.setTargetPosition(-distance);
        frontRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(-distance);

        // Set to RUN_TO_POSITION_MODE
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set drive power
        DriveForward(power);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() )
        {
            // Wait until target position is reached
        }

        // Stop and change modes back to normal
        StopDriving();
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void StrafeLeftDistance(double power, int distance) {
        // Reset encoders
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        frontLeft.setTargetPosition(distance);
        frontRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(distance);

        // Set to RUN_TO_POSITION_MODE
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set drive power
        DriveForward(power);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() )
        {
            // Wait until target position is reached
        }

        // Stop and change modes back to normal
        StopDriving();
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void StrafeRightDistance(double power, int distance) {
        // Reset encoders
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        frontLeft.setTargetPosition(-distance);
        frontRight.setTargetPosition(distance);
        backLeft.setTargetPosition(distance);
        backRight.setTargetPosition(-distance);

        // Set to RUN_TO_POSITION_MODE
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set drive power
        DriveForward(power);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() )
        {
            // Wait until target position is reached
        }

        // Stop and change modes back to normal
        StopDriving();
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    //Controls
    public void DriveForward(double power) {
        mecanumDrive.frontLeft.setPower(power);  //top left when rev is down and ducky wheel is right
        mecanumDrive.frontRight.setPower(power); //bottom left
        mecanumDrive.backLeft.setPower(-power);   //top right
        mecanumDrive.backRight.setPower(power); // bottom right
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void DriveBackward(double power) {
        mecanumDrive.frontLeft.setPower(-power);
        mecanumDrive.frontRight.setPower(-power);
        mecanumDrive.backLeft.setPower(power);
        mecanumDrive.backRight.setPower(-power);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void StrafeLeft(double power, int time) {
        mecanumDrive.frontLeft.setPower(-power);
        mecanumDrive.frontRight.setPower(power);
        mecanumDrive.backLeft.setPower(-power);
        mecanumDrive.backRight.setPower(-power);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void StrafeRight(double power, int time) {
        mecanumDrive.frontLeft.setPower(power);
        mecanumDrive.frontRight.setPower(-power);
        mecanumDrive.backLeft.setPower(power);
        mecanumDrive.backRight.setPower(power);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void TurnLeft(double power, int time) {
        mecanumDrive.frontLeft.setPower(power);
        mecanumDrive.frontRight.setPower(-power);
        mecanumDrive.backLeft.setPower(-power);
        mecanumDrive.backRight.setPower(-power);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void TurnRight(double power, int time) {
        mecanumDrive.frontLeft.setPower(-power);
        mecanumDrive.frontRight.setPower(power);
        mecanumDrive.backLeft.setPower(power);
        mecanumDrive.backRight.setPower(power);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }
    public void StopDriving()
    {
        DriveForward(0);
    }
}
