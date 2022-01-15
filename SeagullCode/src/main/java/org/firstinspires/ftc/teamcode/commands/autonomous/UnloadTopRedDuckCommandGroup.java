package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.teamcode.commands.dump.DumpCollectCommand;
import org.firstinspires.ftc.teamcode.commands.dump.DumpUnloadTopLevelCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;

public class UnloadTopRedDuckCommandGroup extends SequentialCommandGroup {
    public UnloadTopRedDuckCommandGroup(DrivebaseSubsystem drive, DumpSubsystem bucket, IntakeSubsystem intake, VisionSubsystem vision) {
        super(
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_DUCK_START_TO_ALLIANCE_HUB_LEVEL3), // Different duck constant
                  new DumpUnloadTopLevelCommand(bucket).withTimeout(1.5),
                  new WaitCommand(1),
                  new DumpCollectCommand(bucket),
                  new IntakeInCommand(intake), // Intake command - spin the intake before arrived at the depot
                  new TrajectorySequenceCommand(drive, AutonomousConstants.RED_ALLIANCE_HUB_LEVEL3_TO_DUCK_COLLECT1), // Different park command

                  CommandScheduler.getInstance()::terminateOpMode); //ending
    }
}
