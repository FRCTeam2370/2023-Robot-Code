// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm.Arm_basic;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class Arm_movment extends CommandBase {
  /** Creates a new Arm_movment. */
  public Arm_movment(Arm m_Arm, double shoulderpostion, double elbowpostion) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Arm);
    this.shoulderpostion = shoulderpostion;
    this.elbowpostion = elbowpostion;
  }

  double shoulderpostion;
  double elbowpostion;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(shoulderpostion <= Arm.Leftshouldercoder.getPosition()){
      Arm.leftshouldpid.setPID(.014, 0.001, 0);
    }
    else{
      Arm.leftshouldpid.setPID(.01, .001, 0);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    Arm.leftshouldMoter.set(ControlMode.PercentOutput,
        Arm.leftshouldpid.calculate(Arm.Leftshouldercoder.getPosition(), shoulderpostion));

    

    Arm.leftelbowMoter.set(ControlMode.Position,
    elbowpostion); 

    // Arm.leftelbowMoter.set(ControlMode.PercentOutput,
    // Arm.leftelbowpid.calculate(Arm.Leftelbowcoder.getPosition(), elbowpostion));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Arm.leftshouldMoter.set(ControlMode.PercentOutput, 0);
     Arm.leftelbowMoter.set(ControlMode.Position, shoulderpostion);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   
    return Arm.leftelbowMoter.getSensorCollection().getIntegratedSensorPosition() == elbowpostion &&  Arm.leftshouldpid.atSetpoint() ? true : false;
  }
}
