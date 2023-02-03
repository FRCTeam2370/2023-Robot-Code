// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.security.PublicKey;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
public static DigitalInput leftshouldmagneticsensor = new DigitalInput(0);
public static CANCoder Leftarmencoder = new CANCoder(0);

public static WPI_TalonFX leftelbowMoter = new WPI_TalonFX(Constants.Leftarm);
public static DigitalInput leftelbowmagneticsensor = new DigitalInput(0);
public static CANCoder Leftelbowcoder = new CANCoder(0);
public static boolean getshouldmagneticsonsor(){
 return leftshouldmagneticsensor.get();
}
public static boolean getelbowmagneticsonsor(){
  return leftelbowmagneticsensor.get();
 }
public static void shoulderstartstuff(WPI_TalonFX motor, CANCoder encoder){
  motor.configFactoryDefault();
  motor.configPeakOutputForward(.3);
  motor.configPeakOutputReverse(.3);
  motor.configFactoryDefault();
  
  encoder.configMagnetOffset(encoder.getAbsolutePosition());
}


public static void moveelbowslow(WPI_TalonFX motor){
  motor.set(-.1);
}
public static void movearmmotor(WPI_TalonFX motor, double setpoint){
  motor.set(ControlMode.Position, setpoint);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   
  }
}