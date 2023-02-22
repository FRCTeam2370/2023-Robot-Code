package frc.robot.commands.Shoulder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.sub_Shoulder;

public class Reset_Shoulder_Encoder extends CommandBase {

  public Reset_Shoulder_Encoder(sub_Shoulder shoulder) {
    addRequirements(shoulder);
  }

  @Override
  public void initialize() {
    sub_Shoulder.Shoulder_Reset_Encoder();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
