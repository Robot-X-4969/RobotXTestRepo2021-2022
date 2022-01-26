package org.firstinspires.ftc.teamcode.commands.autonomous;


import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadBottomLevelCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

public class UnloadBottomBlueDuckCommandGroup extends SequentialCommandGroup {
    public UnloadBottomBlueDuckCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket) {
        super(
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DUCK_START_TO_ALLIANCE_HUB_LEVEL_1), // Different duck constant
                new DumpUnloadBottomLevelCommand(bucket).withTimeout(1.5),
                new WaitCommand(1),
                new DumpCollectCommand(bucket),
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_ALLIANCE_HUB_LEVEL1_TO_CAROUSEL) // Different park command
        );
    }
}
