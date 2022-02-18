package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;


public class MecanumLock extends XModule {

    public Servo mecanumServo;

    double servoPosition = 0.0;
    boolean closed = true;

    public void loop() {
        if (xGamepad2().left_stick_button.wasPressed()) {
            Servo();
        }
    }


    public MecanumLock(OpMode op) {
        super(op);
    }

    public void Servo() {
        if (!closed) {
            mecanumServo.setPosition(0);
            closed = true;
        } else {
            mecanumServo.setPosition(0.900);
            closed = false;
        }
    }

}
