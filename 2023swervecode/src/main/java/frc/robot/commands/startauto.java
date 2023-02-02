// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class startauto extends CommandBase {
  /** Creates a new startauto. */
  private double x,y,z;
  public startauto(Drivetrain m_Drivetrain, double x, double y, double z) {
    addRequirements(m_Drivetrain);
    this.x = x;
    this.y = y;
    this.z = z;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   Drivetrain.x = x;
   Drivetrain.y = y;
  Drivetrain.angleoffset = z;
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
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
