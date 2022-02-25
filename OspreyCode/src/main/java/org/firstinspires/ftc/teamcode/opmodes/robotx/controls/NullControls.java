package org.firstinspires.ftc.teamcode.opmodes.robotx.controls;

import org.firstinspires.ftc.teamcode.opmodes.robotx.libraries.DriveSystemControls;
import org.firstinspires.ftc.teamcode.opmodes.robotx.libraries.*;



/**
 * Created by Nicholas on 11/21/16.
 * An empty control class to be used by default so null pointer errors aren't thrown when there is no control scheme.
 */
public class NullControls extends DriveSystemControls {

    public double getLeftPower(XGamepad gamepad1, XGamepad gamepad2) {
        return 0.0;
    }
    public double getRightPower(XGamepad gamepad1, XGamepad gamepad2) {
        return 0.0;
    }

}