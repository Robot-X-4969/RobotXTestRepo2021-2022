package org.firstinspires.ftc.teamcode.opmodes.robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.robotx.libraries.XOpMode;
import org.firstinspires.ftc.teamcode.opmodes.robotx.modules.DuckRotation;
import org.firstinspires.ftc.teamcode.opmodes.robotx.modules.IntakeSystem;
import org.firstinspires.ftc.teamcode.opmodes.robotx.modules.LiftSystem;
import org.firstinspires.ftc.teamcode.opmodes.robotx.modules.OrientationDrive;

@TeleOp(name = "OpMode2021v2", group = "Default")

public class OpMode2021v2 extends XOpMode {

    OrientationDrive orientationDrive;
    IntakeSystem intakeSystem;
    LiftSystem liftSystem;
    DuckRotation duckRotation;


    public void initModules() {                                                                 

        super.initModules();

        intakeSystem= new IntakeSystem(this);
        activeModules.add(intakeSystem);

        liftSystem = new LiftSystem(this);
        activeModules.add(liftSystem);

        duckRotation = new DuckRotation(this);
        activeModules.add(duckRotation);

        orientationDrive = new OrientationDrive(this);
        activeModules.add(orientationDrive);

    }

    public void init() {
        super.init();
    }
}

/*
Controls
GamePad 1:
Joysticks to move
B to reset robot orientation
Left bumper to toggle slow mode
Right bumper to toggle super slow mode

Gamepad 2:
intake x and y
lift a and b
duck motor right back and left back


}*/