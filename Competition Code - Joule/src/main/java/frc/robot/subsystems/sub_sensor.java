// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.text.rtf.RTFEditorKit;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElbowConstants;

public class sub_sensor extends SubsystemBase {
  /** Creates a new sub_sensor. */
  public sub_sensor() {
   
    
  }
public static double X_lime;
public static double Y_lime;
public static double Area_lime;

public static double set_speed(double postion, double target){
double error = target - postion;
if(target-1 <= postion && postion < target+1){
  return error*1;
}
else{return 0;}
}
public static NetworkTable limeTable = NetworkTableInstance.getDefault().getTable("limelight");
public static NetworkTableEntry tx = limeTable.getEntry("tx");
public static NetworkTableEntry ty = limeTable.getEntry("ty");
public static NetworkTableEntry ta = limeTable.getEntry("ta");

public static AnalogInput Distence_sensor = new AnalogInput(0);
public static double Distence;

public static double distince_from_target(AnalogInput x){ 
  return  ((3.55*x.getAverageVoltage())-0.159);
}

public static String GamePiece = "";
private static I2C.Port i2cPort = I2C.Port.kOnboard;
public static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

public void GetColor() {

  Color detectedColor = m_colorSensor.getColor();
  double IR = m_colorSensor.getIR();
  SmartDashboard.putNumber("Red", detectedColor.red);
  SmartDashboard.putNumber("Green", detectedColor.green);
  SmartDashboard.putNumber("Blue", detectedColor.blue);
  SmartDashboard.putNumber("IR", IR);

  if (IR > 6) {
    if (detectedColor.red > 0.2 && detectedColor.red < 0.3) {
      if (detectedColor.green > 0.3 && detectedColor.green < 0.5) {
        if (detectedColor.blue > 0.2 && detectedColor.blue < 0.48) {
          GamePiece = "Cube";
          SmartDashboard.putString("Game piece 1", GamePiece);

        } else {
          GamePiece = "none";
          SmartDashboard.putString("Game piece 1", GamePiece);

        }
      } else {
        GamePiece = "none";
        SmartDashboard.putString("Game piece 1", GamePiece);
      }

    } else if (detectedColor.red < 0.38 && detectedColor.red > 0.3) {
      if (detectedColor.green < 0.56 && detectedColor.green > 0.47) {
        if (detectedColor.blue > 0.07 && detectedColor.blue < 0.21) {
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

}
public static void SwitchPipline0(){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
}

public static void SwitchPipline1(){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
}

public static void SwitchPipline2(){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(2);
}

  @Override
  public void periodic() {
    GetColor();
    if(GamePiece == "Cube"){
      SwitchPipline0();
    }
    else if(GamePiece == "Cone"){
      SwitchPipline1();
    }

   
    Distence = distince_from_target(Distence_sensor);
//at 7 feet voltage=2
//at 6 feet voltage=1.7
//at 5 feet voltage=1.5
//at 4 feet voltage=1.2
//at 3 feet voltage=.91
//at 2 feet voltage=.62
//at 1 feet and 4 inches voltage= .4
    X_lime = tx.getDouble(0);
    Y_lime = ty.getDouble(0);
    Area_lime = ta.getDouble(0);
    
    SmartDashboard.putNumber("x lime", X_lime);
    SmartDashboard.putNumber("y lime", Y_lime);
    SmartDashboard.putNumber("Area lime", Area_lime);

    SmartDashboard.putNumber("distance", Distence);
    // This method will be called once per scheduler run
    
  }
}
