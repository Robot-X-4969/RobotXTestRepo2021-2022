package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.InstantCommand;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.logger.Loggable;

import org.firstinspires.ftc.teamcode.Robot;
import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.commands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.commands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.drivebase.AlignToShootCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmExtendCommand;
import org.firstinspires.ftc.teamcode.commands.index.ArmRetractCommand;
import org.firstinspires.ftc.teamcode.commands.index.IndexPivotDownCommand;
import org.firstinspires.ftc.teamcode.commands.index.IndexPivotUpCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterSetFlapCommand;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterStopCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleOpenCommand;
import org.firstinspires.ftc.teamcode.commands.wobble.WobbleRaiseCommand;
import org.firstinspires.ftc.teamcode.subsystems.WobbleSubsystem;

/** Main OpMode
 *
 */
@Autonomous(name = "AutoV1")
public class AutoV1 extends CommandOpMode implements Loggable {
    /** The robot
     *
     */
    public Robot robot;
    @Override
    public void uponInit() {
        robot = new Robot();

    }

    @Override
    public void uponStart() {
        CommandScheduler.getInstance().schedule(new SequentialCommandGroup(
                //prep to shoot
                new ParallelCommandGroup(
                        new TurnCommand(robot.drivebaseSubsystem, -10),
                        new InstantCommand(()->robot.wobbleSubsystem.setClawPosition(WobbleSubsystem.ClawPosition.CLOSED)),
                        new IndexPivotUpCommand(robot.indexSubsystem),
                        new AlignToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem),
                        new ShooterSetFlapCommand(robot.shooterSubsystem, ()->0.4),
                        new WaitCommand(0.3)),
                //shoot 3 rings
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                new WaitCommand(0.2),
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
//                new WaitCommand(0.1),
//                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                //move to stack
                new ParallelCommandGroup(
                        new IndexPivotDownCommand(robot.indexSubsystem),
                        new ShooterStopCommand(robot.shooterSubsystem),
                        new IntakeInCommand(robot.intakeSubsystem),
                        new SplineCommand(robot.drivebaseSubsystem, 48, 12, 0, -15)),
                //prep to shoot
                new ParallelCommandGroup(
                        new IndexPivotUpCommand(robot.indexSubsystem),
                        new AlignToShootCommand(robot.drivebaseSubsystem, robot.shooterSubsystem),
                        new IntakeStopCommand(robot.intakeSubsystem),
                        new ShooterSetFlapCommand(robot.shooterSubsystem, ()->0.3)
                        ),
                //shoot 3 rings
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                new WaitCommand(0.2),
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                new WaitCommand(0.2),
                new SequentialCommandGroup(new ArmExtendCommand(robot.indexSubsystem), new ArmRetractCommand(robot.indexSubsystem)),
                //move to drop point
                new ParallelCommandGroup(
                        new IndexPivotDownCommand(robot.indexSubsystem),
                        new ShooterStopCommand(robot.shooterSubsystem),
                        new StrafeCommand(robot.drivebaseSubsystem, 120, 1,  90),
                        new WobbleLowerCommand(robot.wobbleSubsystem)),
                //drop 1st wobble
                //new TurnCommand(robot.drivebaseSubsystem, 90),
                new WobbleOpenCommand(robot.wobbleSubsystem),
                //move to 2nd wobble
                new StrafeCommand(robot.drivebaseSubsystem, 30, 29, -30),
                //grab 2nd wobble
                new WobbleCloseCommand(robot.wobbleSubsystem),
                //move to drop point
                new StrafeCommand(robot.drivebaseSubsystem, 115, 1, 90),
                //drop 2nd wobble
                new WobbleOpenCommand(robot.wobbleSubsystem),
                //goto line
                new ParallelCommandGroup(
                        new StrafeCommand(robot.drivebaseSubsystem, 80, 10, 82),
                        new SequentialCommandGroup(new WobbleRaiseCommand(robot.wobbleSubsystem), new WobbleCloseCommand(robot.wobbleSubsystem))),
                new InstantCommand(()->robot.drivebaseSubsystem.setPoseEstimate(new Pose2d())),
                new InstantCommand(this::terminate)
        ));
    }
}
