// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;



public class Sensors extends SubsystemBase{
  public String GamePiece = "";
  private I2C.Port i2cPort = I2C.Port.kOnboard;
  public ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry tx = table.getEntry("tx");
  public static NetworkTableEntry ty = table.getEntry("ty");
  public static NetworkTableEntry ta = table.getEntry("ta");
  public static NetworkTableEntry tid = table.getEntry("tid");
  /** Creates a new Sensors. */
  public Sensors() {}
    //Limelight Networktable collection
  
   public void GetColor() {
  
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
            GamePiece = "Cube";
            SmartDashboard.putString("Game piece 1", GamePiece);

          } else {
            GamePiece = "none";
            SmartDashboard.putString("Game piece 1", GamePiece);

          }
        } else{
            GamePiece = "none";
            SmartDashboard.putString("Game piece 1", GamePiece);
        }
  
      } 
    else if(detectedColor.red < 0.38 && detectedColor.red > 0.3){
     if(detectedColor.green < 0.56 && detectedColor.green > 0.47){
      if(detectedColor.blue > 0.07 && detectedColor.blue < 0.21){
        GamePiece = "Cone";
        
      } else {
        GamePiece = "none";
      }
    } else {
      GamePiece = "none";
    }
  } else {
    GamePiece = "none";
  }} else{
    GamePiece = "none";
    }
  
    if(GamePiece == "Cube"){
      sub_LEDs.LEDs_Green();
    }
    if(GamePiece == "Cone"){
      sub_LEDs.LEDs_Green();
    } 
    
  }
    
  


    
@Override
  public void periodic() {

  } 
}
