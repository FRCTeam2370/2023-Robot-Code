// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm.Arm_basic;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;

public class Arm_set_up extends CommandBase {
  /** Creates a new Arm_set_up. */
  public Arm_set_up(Arm m_Arm) {
    addRequirements(m_Arm);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (Arm.leftshouldmagneticsensor.get() == true) {
      Arm.leftshouldMoter.set(ControlMode.PercentOutput, -.1);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Arm.leftshouldMoter.stopMotor();
    Arm.shoulderstartstuff(Arm.leftshouldMoter, Arm.Leftshouldercoder);
    Arm.ElbowStartStuff(Arm.leftelbowMoter, Arm.ElbowCanCoder);
  }

  // Returns true when the command should end.
  /*
   * (non-Javadoc)
   * 
   * @see edu.wpi.first.wpilibj2.command.Command#isFinished()
   */
  @Override
  public boolean isFinished() {
    if (Arm.leftshouldmagneticsensor.get() == true) {
      return false;
    } else {
      return true;
    }
  }
}
