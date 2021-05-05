package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.drivebase.VisionAlignCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.subsystems.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionAimSubsystem;

public class AimAndShootCommand extends SequentialCommandGroup {
    public AimAndShootCommand(IndexSubsystem i, TurretSubsystem t, VisionAimSubsystem v){
        super(
                new ArmRetractCommand(i).with(new VisionAlignCommand(t, v)),
                new SendRingsToShooterCommand(i, 3)
        );
    }
}