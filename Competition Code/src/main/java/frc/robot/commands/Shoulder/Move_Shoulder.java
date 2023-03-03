package frc.robot.commands.Shoulder;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.sub_Shoulder;

public class Move_Shoulder extends CommandBase {

  private double shoulderposition;

  public Move_Shoulder(sub_Shoulder shoulder, double shoulderposition) {
    addRequirements(shoulder);
    this.shoulderposition = shoulderposition;
  }

  @Override
  public void initialize() {
    sub_Shoulder.Shoulder_motor.set(ControlMode.Position, shoulderposition);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if(Math.abs(shoulderposition - sub_Shoulder.Shoulder_motor.getSensorCollection().getIntegratedSensorPosition())<1000){
      return true;
    } else {
      return false; 
    }
   
  }
}
