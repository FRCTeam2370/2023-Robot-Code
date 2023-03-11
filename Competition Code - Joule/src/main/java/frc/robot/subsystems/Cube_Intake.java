// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Cube_Intake extends SubsystemBase {
  /** Creates a new Cube_Intake. */

  public static WPI_TalonFX IntakeMotor = new WPI_TalonFX(Constants.MotorIntake);
  public static DoubleSolenoid IntakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3);
  public Cube_Intake() {}

  public static void DropIntake(){
    IntakeSolenoid.set(Value.kForward);
  }
  public static void RiseIntake(){
    IntakeSolenoid.set(Value.kReverse);
  }
  public static void ForwardIntake(){
    IntakeMotor.set(ControlMode.PercentOutput,20);
  }
  public static void StopIntake(){
    IntakeMotor.set(ControlMode.PercentOutput, 0);
  }
  public static void ReverseIntake(){
    IntakeMotor.set(ControlMode.PercentOutput, -100);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
