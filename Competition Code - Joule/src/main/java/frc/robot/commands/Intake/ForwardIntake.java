// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cube_Intake;

public class ForwardIntake extends CommandBase {
  /** Creates a new ForwardIntake. */
  public ForwardIntake(Cube_Intake mCube_Intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mCube_Intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Cube_Intake.ForwardIntake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Cube_Intake.StopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
