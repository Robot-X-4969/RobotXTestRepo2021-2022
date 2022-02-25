package org.firstinspires.ftc.teamcode.opmodes.robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.opmodes.robotx.libraries.XModule;

public class
LiftSystem extends XModule {

    public DcMotor liftMotor;
    public DcMotor liftMotor2;

    public Servo armServo;
    public Servo pushServo;

    double power = 1;

    boolean closed2 = true;
    boolean closed = true;

    public LiftSystem (OpMode op) {
        super(op);
    }

    public void init() {

        liftMotor = opMode.hardwareMap.dcMotor.get("LiftMotor");
        liftMotor2 = opMode.hardwareMap.dcMotor.get("LiftMotor2");

    }

    public void armServo() {
        if (!closed) {
            armServo.setPosition(0.1);
            closed = true;
        } else {
            armServo.setPosition(0.55);
            closed = false;
        }
    }
    public void pushServo() {
        if (!closed2) {
            pushServo.setPosition(0.89);
            closed = true;
        } else {
            pushServo.setPosition(0.41);
            closed = false;
        }
    }


    public void loop() {
        if (xGamepad2().a.isDown()){
            liftMotor.setPower(power);
        }

        else if (xGamepad2().b.isDown()) {
            liftMotor.setPower(-power);
        }

        else {
            liftMotor.setPower(0.0);
        }

        if (xGamepad2().a.isDown()){
            liftMotor2.setPower(power);
        }

        else if (xGamepad2().b.isDown()) {
            liftMotor2.setPower(-power);
        }

        else {
            liftMotor2.setPower(0.0);
        }

        if (xGamepad2().right_stick_button.wasPressed()){
            armServo();
        }
        if (xGamepad2().right_stick_button.wasPressed()){
            pushServo();
        }



    }
}
//Open: 0.1
//Close: 0.267





