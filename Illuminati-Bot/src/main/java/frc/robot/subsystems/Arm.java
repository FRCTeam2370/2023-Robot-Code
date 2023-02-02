// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.security.PublicKey;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  public Arm() {}
public static boolean arminplace = false;
public static WPI_TalonFX leftshouldMoter = new WPI_TalonFX(Constants.Leftarm);
public static DigitalInput magneticsensor = new DigitalInput(0);
public static CANCoder Leftarmencoder = new CANCoder(0);
public static WPI_TalonFX leftelbowmotor = new WPI_TalonFX(0);
public static boolean getmagneticsonsor(){
 return magneticsensor.get();
}

public static void armstartstuff(){
  leftshouldMoter.configFactoryDefault();
  leftshouldMoter.configPeakOutputForward(.3);
  leftshouldMoter.configPeakOutputReverse(.3);
  Leftarmencoder.configFactoryDefault();
  Leftarmencoder.configMagnetOffset(Leftarmencoder.getAbsolutePosition());
}



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   
  }
}
