package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class UnloadTopRedDepotCommandGroup extends SequentialCommandGroup {
    public UnloadTopRedDepotCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake) {
        super(
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DEPOT_START_TO_ALLIANCE_HUB_LEVEL3), // Different duck constant
                new DumpUnloadTopLevelCommand(bucket),
                new WaitCommand(0.7),
                new DumpCollectCommand(bucket),
                new IntakeInCommand(intake), // Intake command - spin the intake before arrived at the depot
                new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL3_TO_DEPOT_COLLECT1) // Different park command
        );
    }
}
