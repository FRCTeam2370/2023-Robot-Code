// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm.Arm_basic;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class Elbow_only_movement extends CommandBase {
  /** Creates a new Elbow_only_movement. */
  public Elbow_only_movement(Arm m_Arm, double elbowpostion) {
addRequirements(m_Arm);
this.elbowpostion= elbowpostion;
    // Use addRequirements() here to declare subsystem dependencies.
  }
private double elbowpostion;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Arm.leftelbowMoter.set(ControlMode.Position,
    elbowpostion); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Arm.leftelbowMoter.set(ControlMode.Position,
    elbowpostion); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
