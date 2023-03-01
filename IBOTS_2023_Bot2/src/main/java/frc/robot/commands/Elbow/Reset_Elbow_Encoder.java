// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Elbow;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.sub_Elbow;

public class Reset_Elbow_Encoder extends CommandBase {
  /** Creates a new Reset_Elbow_Encoder. */
  public Reset_Elbow_Encoder(sub_Elbow elbow) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elbow);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sub_Elbow.Elbow_Reset_Encoder();
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
    return false;
  }
}
