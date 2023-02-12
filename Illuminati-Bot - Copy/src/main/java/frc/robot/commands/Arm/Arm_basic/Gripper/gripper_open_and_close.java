// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm.Arm_basic.Gripper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Arm.Arm_postions.Arm_back;
import frc.robot.subsystems.Arm;

public class gripper_open_and_close extends CommandBase {
  /** Creates a new gripper_open_and_close. */
  public gripper_open_and_close(Arm m_Arm) {
    addRequirements(m_Arm);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Arm.OpenGripper();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Arm.CloseGripper();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
