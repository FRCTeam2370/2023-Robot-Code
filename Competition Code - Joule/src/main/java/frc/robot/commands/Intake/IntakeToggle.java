// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Cube_Intake;

public class IntakeToggle extends CommandBase {

 public Cube_Intake m_Cube_Intake;
  /** Creates a new IntakeToggle. */
  public IntakeToggle(Cube_Intake m_Cube_Intake) {
    // Use addRequirements() here to declare subsystem dependencies.
   addRequirements(m_Cube_Intake);
    this.m_Cube_Intake = m_Cube_Intake;
  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Cube_Intake.DropIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(RobotContainer.trigger(RobotContainer.driver, 3).getAsBoolean() == true){
      Cube_Intake.IntakeMotor.set(ControlMode.PercentOutput, 0.6);
    }else if(RobotContainer.trigger(RobotContainer.driver, 2).getAsBoolean() == true){
      Cube_Intake.IntakeMotor.set(ControlMode.PercentOutput, -1);
    }else{
      Cube_Intake.IntakeMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Cube_Intake.RiseIntake();
    Cube_Intake.IntakeMotor.set(ControlMode.PercentOutput, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
