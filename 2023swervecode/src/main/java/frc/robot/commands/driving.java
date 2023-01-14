// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class driving extends CommandBase {
  /** Creates a new driving. */
  public driving(Drivetrain m_Drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Drivetrain.leftback.setdtivemode(true);
    Drivetrain.leftfront.setdtivemode(true);
    Drivetrain.rightback.setdtivemode(true);
    Drivetrain.rightfront.setdtivemode(true);
    Drivetrain.fullreset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Drivetrain.fullswervecontrol(RobotContainer.deadband(0, .05, RobotContainer.controller), -RobotContainer.deadband(1, .05, RobotContainer.controller), RobotContainer.deadband(4, .05, RobotContainer.controller));
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
