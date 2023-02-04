// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm.Arm_basic;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class eblow_movement extends CommandBase {
  /** Creates a new eblow_movement. */
  double postiontogoto;
  public eblow_movement(Arm m_arm, double postiontogoto) {
    addRequirements();
    this.postiontogoto = postiontogoto;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Arm.leftelbowMoter.set(TalonFXControlMode.PercentOutput, Arm.leftelbowpid.calculate(Arm.Leftelbowcoder.getPosition(), postiontogoto));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   return Arm.leftelbowpid.atSetpoint();
    
  }
}
