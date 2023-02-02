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
    if(Drivetrain.firststart == false){
      Drivetrain.leftback.setdtivemode(true);
      Drivetrain.leftfront.setdtivemode(true);
      Drivetrain.rightback.setdtivemode(true);
      Drivetrain.rightfront.setdtivemode(true);
      Drivetrain.fullreset();
      Drivetrain.firststart = true; 
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Drivetrain.fullswervecontrol(RobotContainer.Deadband( RobotContainer.Controler, 0.5, 0), RobotContainer.Deadband(RobotContainer.Controler, 0.5, 1), RobotContainer.Deadband(RobotContainer.Controler, 0.5, 4));
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
