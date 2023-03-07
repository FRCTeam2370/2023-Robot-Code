package frc.robot.commands.Elbow;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.sub_Elbow;

public class Move_Elbow extends CommandBase {
  private double elbowposition;

  public Move_Elbow(sub_Elbow elbow, double elbowposition) {
    addRequirements(elbow);
    this.elbowposition = elbowposition;
  }

  @Override
  public void initialize() {
    sub_Elbow.Elbow_motor.set(ControlMode.Position, elbowposition);
    sub_Elbow.Send_Elbow_PID_Variables();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if(Math.abs(elbowposition - sub_Elbow.Elbow_motor.getSensorCollection().getIntegratedSensorPosition())<1000){
      return true;
    } else {
      return false; 
    }
   
  }
}
