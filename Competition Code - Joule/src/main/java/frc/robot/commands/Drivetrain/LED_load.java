// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.sub_Gripper;
import frc.robot.subsystems.sub_LED;
import frc.robot.subsystems.sub_sensor;

public class LED_load extends CommandBase {
  /** Creates a new LED_load. */
  public LED_load(sub_sensor msSensor, sub_LED mLed, sub_Gripper mGripper) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(msSensor, mLed, mGripper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sub_Gripper.OpenGripper();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(sub_sensor.Distence > 5){
      sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 93, 0, 0);
      RobotContainer.driver.setRumble(RumbleType.kBothRumble, .2);
    } else if(sub_sensor.Distence < 1){
      sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 93, 213, 0);
      RobotContainer.driver.setRumble(RumbleType.kBothRumble, 1);
    }else{
      sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 93, 213, 0);
      RobotContainer.driver.setRumble(RumbleType.kBothRumble, 0);
    }
    if(sub_sensor.GamePiece == "Cone" && sub_sensor.m_colorSensor.getIR() > 20){
      sub_Gripper.CloseGripper();
      RobotContainer.driver.setRumble(RumbleType.kBothRumble, 0);
      sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 245, 242, 49);
  }else if(sub_sensor.GamePiece == "Cube" && sub_sensor.m_colorSensor.getIR()> 6.5){
    sub_Gripper.CloseGripper();
    RobotContainer.driver.setRumble(RumbleType.kBothRumble, 0);
    sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 128,0, 128);
  }
  if(sub_sensor.GamePiece == "none"){
    sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 128,0, 0);
  }
  }

  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.driver.setRumble(RumbleType.kBothRumble, 0);
    sub_LED.LEDset(sub_LED.rearLEDs, sub_LED.rearLEDSbuffer, 93, 213, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
