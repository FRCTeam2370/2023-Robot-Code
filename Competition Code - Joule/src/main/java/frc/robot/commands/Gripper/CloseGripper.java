package frc.robot.commands.Gripper;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.sub_Gripper;

public class CloseGripper extends CommandBase {
  public CloseGripper(sub_Gripper gripper) {
    addRequirements(gripper);
  }

  @Override
  public void initialize() {
    sub_Gripper.CloseGripper();
  }

  @Override
  public void execute() {
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
