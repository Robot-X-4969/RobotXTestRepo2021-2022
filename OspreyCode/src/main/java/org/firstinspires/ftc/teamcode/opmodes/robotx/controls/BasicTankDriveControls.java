package org.firstinspires.ftc.teamcode.opmodes.robotx.controls;

import org.firstinspires.ftc.teamcode.opmodes.robotx.libraries.DriveSystemControls;
import org.firstinspires.ftc.teamcode.opmodes.robotx.libraries.XGamepad;

/**
 * Created by Nicholas on 11/12/16.
 */
public class BasicTankDriveControls extends DriveSystemControls {

    public double getLeftPower(XGamepad gamepad1, XGamepad gamepad2) {
        double leftPower = -1.0 * gamepad1.left_stick_y; // Invert it because -1.0 is up on the gamepads.

        return leftPower;
    }
    public double getRightPower(XGamepad gamepad1, XGamepad gamepad2) {
        double rightPower = -1.0 * gamepad1.right_stick_x; // Invert it because -1.0 is up on the gamepads.

        return rightPower;
    }

}