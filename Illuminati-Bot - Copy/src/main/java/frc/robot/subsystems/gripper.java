// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class gripper extends SubsystemBase {
  /** Creates a new gripper. */
  public gripper() {}
  public static DoubleSolenoid Solenoid0 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
  public static Compressor AirCompressor = new Compressor(Constants.PCMCANID, PneumaticsModuleType.REVPH); 
  public static void EnableCompressor(){
    AirCompressor.enableDigital();
  }

  public static void DissableCompressor(){
    AirCompressor.disable();
  }

  public static void OpenGripper(){
    Solenoid0.set(Value.kForward);
    
  }

  public static void CloseGripper(){
    Solenoid0.set(Value.kReverse);
    
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
