// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Sensors;
import frc.robot.subsystems.sub_LEDs;

public class Game_Piece_Detector extends CommandBase {
    /** Creates a new Game_Piece_Detector. */

    public String GamePiece = "";
    private  I2C.Port i2cPort = I2C.Port.kOnboard;
    public ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

    public Game_Piece_Detector(Sensors s, sub_LEDs m_LEDs) {
    addRequirements(s);    
    addRequirements(m_LEDs);
  }
   
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(GamePiece == "Cube"){
      sub_LEDs.LEDs_Green();
    }else if(GamePiece == "Cone"){
      sub_LEDs.LEDs_Green();
    } else {
      sub_LEDs.LEDs_Off();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Color detectedColor = m_colorSensor.getColor();
    double IR = m_colorSensor.getIR();
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("IR", IR);
    SmartDashboard.putString("Game Piece", GamePiece);

   

   

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

    } else if(detectedColor.red < 0.38 && detectedColor.red > 0.3){
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
     }
    } else {
      GamePiece = "none";


     
    }

  if(GamePiece == "Cube"){
    sub_LEDs.LEDs_Green();
  } else if(GamePiece == "Cone"){
    sub_LEDs.LEDs_Green();
  } else {
    sub_LEDs.LEDs_Off();
  }
 
   
  }
  
 


    
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   
    return false;
    
  }
}
