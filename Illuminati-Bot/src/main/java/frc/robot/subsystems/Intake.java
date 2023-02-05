// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  public static DoubleSolenoid Solenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Sol1ForwardChannel, Constants.Sol1ReverseChannel);
  public static PneumaticsControlModule PCM1 = new PneumaticsControlModule(); 

  public static WPI_TalonFX IntakeMotor = new WPI_TalonFX(Constants.IntakeMotor); 
  public Intake() {

  }

  public static void OpenSolenoid(){
    Solenoid1.set(Value.kForward); 
  }

  public static void CloseSolenoid(){
    Solenoid1.set(Value.kReverse); 
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
