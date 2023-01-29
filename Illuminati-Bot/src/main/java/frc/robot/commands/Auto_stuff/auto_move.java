// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_stuff;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class auto_move extends CommandBase {
  /** Creates a new auto_move. */
  private double x;
  private double y;
  private double z;
  public auto_move(Drivetrain m_Drivetrain, double x, double y, double z) {
    addRequirements(m_Drivetrain);
    this.x = x;
    this.y = y;
    this.z = z;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
    Drivetrain.fullswervecontrol(Drivetrain.automovePID.calculate(Drivetrain.x, x), Drivetrain.automovePID.calculate(Drivetrain.y, y), Drivetrain.autoturn.calculate(Drivetrain.gyro.getAngle(), z));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Drivetrain.automovePID.atSetpoint();
  }
}
