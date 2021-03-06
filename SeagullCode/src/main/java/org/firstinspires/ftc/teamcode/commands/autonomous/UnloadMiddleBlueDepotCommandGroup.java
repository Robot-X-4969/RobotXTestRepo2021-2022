package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadMiddleLevelCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class UnloadMiddleBlueDepotCommandGroup extends SequentialCommandGroup {
    public UnloadMiddleBlueDepotCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake) {
        super(
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_DEPOT_START_TO_ALLIANCE_HUB_LEVEL2), // Different duck constant
                new DumpUnloadMiddleLevelCommand(bucket).withTimeout(1.5),
                new WaitCommand(1),
                new DumpCollectCommand(bucket),
                new IntakeInCommand(intake), // Intake command - spin the intake before arrived at the depot
                new TrajectorySequenceCommand(drive, AutonomousConstants.BLUE_ALLIANCE_HUB_LEVEL2_TO_DEPOT_COLLECT1) // Different park command
        );
    }
}
