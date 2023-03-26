// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class sub_LED extends SubsystemBase {

  public static AddressableLED rearLEDs = new AddressableLED(0);
  public static AddressableLEDBuffer rearLEDSbuffer = new AddressableLEDBuffer(22);
  

  /** Creates a new sub_LED. */
  public sub_LED() {
    
    rearLEDs.setLength(rearLEDSbuffer.getLength());
    rearLEDs.setData(rearLEDSbuffer);
     rearLEDs.start();
  }
public static void setLeds(AddressableLED LED, AddressableLEDBuffer LEDBuffer){
    LED.setLength(LEDBuffer.getLength());
   LED.setData(LEDBuffer);
    LED.start();
  }
  public static void LEDset(AddressableLED LED, AddressableLEDBuffer LEDbuffer, int  R, int G, int B){
    for(var i = 0; i<LEDbuffer.getLength(); i++){
      LEDbuffer.setRGB(i, R, G, B);
    }
    LED.setData(LEDbuffer);
 }


  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
}
