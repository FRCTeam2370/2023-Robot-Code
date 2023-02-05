// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class Driving extends CommandBase {
  /** Creates a new Driving. */
  public Driving(Drivetrain m_Drivetrain) {
    addRequirements(m_Drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Drivetrain.fullswervecontrol(RobotContainer.Deadband( 0, 0.5, RobotContainer.driver), -RobotContainer.Deadband(1, 0.5, RobotContainer.driver), RobotContainer.Deadband(4, 0.5, RobotContainer.driver));
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
