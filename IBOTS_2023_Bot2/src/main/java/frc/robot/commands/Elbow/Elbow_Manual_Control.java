package frc.robot.commands.Elbow;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.sub_Elbow;

public class Elbow_Manual_Control extends CommandBase {
  public Elbow_Manual_Control(sub_Elbow elbow) {
    addRequirements(elbow);
  }

  @Override
  public void initialize() {
    sub_Elbow.Elbow_Reset_Encoder();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
