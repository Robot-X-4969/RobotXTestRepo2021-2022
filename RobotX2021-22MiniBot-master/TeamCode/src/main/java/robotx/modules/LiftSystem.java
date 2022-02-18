package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import robotx.libraries.XModule;

public class
LiftSystem extends XModule {

    public DcMotor liftMotor;



    double power = 1;

    boolean clawOpen = false;

    public LiftSystem (OpMode op) {
        super(op);
    }

    public void init() {

        liftMotor = opMode.hardwareMap.dcMotor.get("LiftMotor");

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



    }
}
//Open: 0.1
//Close: 0.267





