// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_LED;
import frc.robot.subsystems.sub_Shoulder;
import frc.robot.subsystems.sub_sensor;

public class load_mode extends CommandBase {
  /** Creates a new load_mode. */
  Swerve m_Swerve;
  double translationSup;
  double strafeSup;
  double turn;
  public load_mode(Swerve m_Swerve, double translationSup, double strafeSup, sub_LED LED, double turn, sub_Elbow elbow, sub_Shoulder shoulder) {
    this.m_Swerve = m_Swerve;
    addRequirements(m_Swerve, LED, elbow, shoulder);
    this.translationSup= translationSup;
    this.strafeSup = strafeSup; 
    this.turn = turn;
       // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sub_Elbow.Elbow_motor.set(ControlMode.Position, 76300);
    sub_Shoulder.Shoulder_motor.set(ControlMode.Position, 15700);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  //  sub_LED.LEDset(sub_LED.rearLEDs,sub_LED.rearLEDSbuffer, Rint, Gint, 0);
    double translationVal;
    double strafeVal;
    
    if( RobotContainer.Deadband(strafeSup, Constants.stickDeadband) <0){
      translationVal = RobotContainer.Deadband(translationSup, Constants.stickDeadband);
      strafeVal = RobotContainer.Deadband(strafeSup, Constants.stickDeadband);
    }
    else if(sub_sensor.Distence > 5){
       translationVal = RobotContainer.Deadband(translationSup, Constants.stickDeadband);
       strafeVal = RobotContainer.Deadband(strafeSup, Constants.stickDeadband);
       sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 125, 0, 0);
    }
    else if(sub_sensor.Distence < 1){
       translationVal = RobotContainer.Deadband(translationSup, Constants.stickDeadband)*.1;
       strafeVal = RobotContainer.Deadband(strafeSup, Constants.stickDeadband)*.1;
       sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 93, 213, 0);
    }
    else{
       translationVal = Swerve.findlinerequationandpoint(RobotContainer.Deadband(translationSup, Constants.stickDeadband), 5, 1, 1, .1);
       strafeVal = Swerve.findlinerequationandpoint(RobotContainer.Deadband(strafeSup, Constants.stickDeadband), 5, 1, 1, .1);
       sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 245, 242, 49);
    }

    double rotationVal = RobotContainer.Deadband(turn, Constants.stickDeadband);

  m_Swerve.drive(new Translation2d(strafeVal,translationVal).times(Constants.Swerve.maxSpeed), rotationVal , !true, true);
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