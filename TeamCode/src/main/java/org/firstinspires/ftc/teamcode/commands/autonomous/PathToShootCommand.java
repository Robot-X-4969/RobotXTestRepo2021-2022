package org.firstinspires.ftc.teamcode.commands.autonomous;

import android.util.Pair;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.TrajectoryCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.VisionAlignCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetSpeedCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionAimSubsystem;

public class PathToShootCommand extends SequentialCommandGroup {
    public PathToShootCommand(DrivebaseSubsystem d, ShooterSubsystem s, AutoState st){
        super(new ParallelCommandGroup(
                new TrajectoryCommand(d, new Pair(st.correctedTan(-90), st.correctedPos(60, 0, 0))),
                new ShooterSetSpeedCommand(s, ()->0.8).with(new ShooterSetFlapCommand(s, ()->0.55))
                ));
    }
}