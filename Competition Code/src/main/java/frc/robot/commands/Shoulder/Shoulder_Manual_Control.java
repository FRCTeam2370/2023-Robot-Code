package frc.robot.commands.Shoulder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.sub_Shoulder;

public class Shoulder_Manual_Control extends CommandBase {

  public Shoulder_Manual_Control(sub_Shoulder shoulder) {
      addRequirements(shoulder);
  }

  @Override
  public void initialize() {
    sub_Shoulder.Set_Shoulder_Motor_Up();
  }

  @Override
  public void execute() {
    //sub_Shoulder.Shoulder_motor.set(ControlMode.PercentOutput, RobotContainer.driver.getRawAxis(1));
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
