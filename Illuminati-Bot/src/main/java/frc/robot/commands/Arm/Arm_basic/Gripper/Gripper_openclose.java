// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm.Arm_basic.Gripper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class Gripper_openclose extends CommandBase {
  /** Creates a new Gripper_openclose. */
  private boolean openclose;
  public Gripper_openclose(Arm m_Arm, Boolean openclose) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Arm);
    this.openclose = openclose;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(openclose){
      Arm.OpenGripper();
    }
    else{
      Arm.CloseGripper();
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
