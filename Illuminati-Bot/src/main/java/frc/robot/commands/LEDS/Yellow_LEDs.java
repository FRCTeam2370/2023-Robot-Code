// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LEDS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.sub_LEDs;

public class Yellow_LEDs extends CommandBase {
  /** Creates a new Yellow_LEDs. */
  public Yellow_LEDs(sub_LEDs m_LEDs) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_LEDs);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sub_LEDs.LEDs_Yellow();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
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
