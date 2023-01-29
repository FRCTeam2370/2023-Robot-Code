// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Sensors extends SubsystemBase {
  /** Creates a new Sensors. */
  private  I2C.Port i2cPort = I2C.Port.kOnboard;
  public ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
 
  public Sensors() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Color detectedColor = m_colorSensor.getColor();
     double IR = m_colorSensor.getIR();
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("IR", IR);

    if (IR > 6){
      if (detectedColor.red > 0.2 && detectedColor.red < 0.3)
      {
        if(detectedColor.green > 0.3 && detectedColor.green < 0.5){ 
          if(detectedColor.blue > 0.2 && detectedColor.blue < 0.48){
            SmartDashboard.putString("Game piece", "Cube");
          } else {
            SmartDashboard.putString("Game piece", "none");
          }
        } else{
          SmartDashboard.putString("Game piece", "none");
        }
  
      } else if(detectedColor.red < 0.38 && detectedColor.red > 0.3){
    if(detectedColor.green < 0.56 && detectedColor.green > 0.47){
      if(detectedColor.blue > 0.07 && detectedColor.blue < 0.21){
        SmartDashboard.putString("Game piece", "Cone");
      } else {
        SmartDashboard.putString("Game piece", "none");
      }
    } else {
      SmartDashboard.putString("Game piece", "none");
    }
  } else {
    SmartDashboard.putString("Game piece", "none");
  }} else{
      SmartDashboard.putString("Game piece", "none");
    }
  }
}

