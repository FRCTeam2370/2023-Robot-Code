// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm.Arm_basic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.gripper;

public class Gripper_Test extends CommandBase {
  /** Creates a new Gripper_Test. */
  public Gripper_Test(gripper m_Arm) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.A_driver.getAsBoolean() == true){
      gripper.OpenGripper();
    } else if(RobotContainer.B_driver.getAsBoolean() == true){
      gripper.CloseGripper();
    } 
    
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
