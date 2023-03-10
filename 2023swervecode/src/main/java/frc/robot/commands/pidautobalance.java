// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class pidautobalance extends CommandBase {
  /** Creates a new pidautobalance. */
  public pidautobalance(Drivetrain m_Drivetrain) {
    addRequirements(m_Drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
    Drivetrain.fullswervecontrol(0, Drivetrain.autobalancepid.calculate(Drivetrain.gyro.getPitch(), 0), 0);
 
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
