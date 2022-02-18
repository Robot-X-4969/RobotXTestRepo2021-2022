package robotx.modules;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class DuckRotation extends XModule {

    public DcMotor duckMotor;
    public Servo blockServo;
    public Servo duckServo;

    boolean blocked = false;
    double power = .6;


    public void blockServo() {
        if (!blocked) {
            blockServo.setPosition(0.600);
            blocked = true;
        }
        else {
            blockServo.setPosition(0.900);
            blocked = false;
        }
    }



    public DuckRotation (OpMode op) {
        super(op);
    }

    public void init() {

        duckMotor = opMode.hardwareMap.dcMotor.get("duckMotor");
        blockServo = opMode.hardwareMap.servo.get("BlockServo");
        duckServo = opMode.hardwareMap.servo.get("duckServo");

    }



    public void loop() {
        if (xGamepad2().right_bumper.isDown()){
            duckMotor.setPower(power);
        }

        else if (xGamepad2().left_bumper.isDown()) {
            duckMotor.setPower(-power);
        }

        else {
            duckMotor.setPower(0.0);
        }

        if (xGamepad2().right_stick_button.wasPressed()) {
            blockServo();
        }



    }
}
//Open: 0.1
//Close: 0.267






