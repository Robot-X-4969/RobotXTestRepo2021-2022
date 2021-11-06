package org.firstinspires.ftc.teamcode.commands.carousel;
import com.technototes.library.command.Command;
import org.firstinspires.ftc.teamcode.subsystems.CarouselSubsystem;

public class CarouselLeftCommand implements Command{
    private CarouselSubsystem subsystem;

    public CarouselLeftCommand(CarouselSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.left();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.stop();

    }
}