// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

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
public static WPI_TalonFX leftarmMoter = new WPI_TalonFX(Constants.Leftarm);
public static DigitalInput magneticswitch = new DigitalInput(0);
public static CANCoder Leftarmencodr = new CANCoder(0);
public static boolean getmagneticswitch(){
  return magneticswitch.get();
}


public static void setmotersettings(){
  if(arminplace == false){
  if(getmagneticswitch() == false){
    leftarmMoter.set(-.1);
  }
  else{
    leftarmMoter.getSensorCollection().setIntegratedSensorPosition(0, 0);
    leftarmMoter.configFactoryDefault();
  leftarmMoter.configPeakOutputForward(.75);
  leftarmMoter.configPeakOutputReverse(-.075);
  leftarmMoter.configReverseSoftLimitThreshold(100000);
  leftarmMoter.configForwardSoftLimitThreshold(0);
  leftarmMoter.configForwardSoftLimitEnable(true);
  leftarmMoter.configReverseSoftLimitEnable(true);
  leftarmMoter.setNeutralMode(NeutralMode.Brake);
  leftarmMoter.config_kP(0, .05);
  leftarmMoter.config_kI(0, 0);
  leftarmMoter.config_kD(0, 0);
  arminplace = true;
  }
}
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("magnet", getmagneticswitch());
  }
}
