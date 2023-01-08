// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.swervelib;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderStatusFrame;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  public static double drivespeedmax = .5;
  public static double rotationspeedmax = .35;
  public static double gyroOffSet;
  public static AHRS gyro = new AHRS();
  public static double falcontickstodegrees = 0.01373;
//  public static locationfinder location = new locationfinder(gyro);
  //front left swerve 
  // motors for driving
  public static WPI_TalonFX Frontleftdrive = new WPI_TalonFX(Constants.Frontleftdrive);
  public static WPI_TalonFX Frontleftturn = new WPI_TalonFX(Constants.Frontleftturn);
  //encoder 
  public static CANCoder Frontleftencoder = new CANCoder(Constants.Frontleftencoder);
  //math for each side
  public static swerveMath leftfront = new swerveMath(Constants.Frontleftx, Constants.Frontlefty, Frontleftturn, gyro);

  //front right swerve 
  // motors for driving
  public static WPI_TalonFX Frontrightdrive = new WPI_TalonFX(Constants.FrontRightdrive);
  public static WPI_TalonFX Frontrightturn = new WPI_TalonFX(Constants.FrontRightturn);
  //encoder
  public static CANCoder frontrightencoder = new CANCoder(Constants.FrontRightencoder);
  //math for each side
  public static swerveMath rightfront = new swerveMath(Constants.Frontrightx, Constants.Frontrighty, Frontrightturn,gyro);

  //back left swerve
  // motors for driving
  public static WPI_TalonFX Backleftdrive = new WPI_TalonFX(Constants.BackLeftdrive);
  public static WPI_TalonFX Backleftturn = new WPI_TalonFX(Constants.BackLeftturn);
  //encoder
  public static CANCoder Backleftencoder = new CANCoder(Constants.BackLeftencoder);
  //math for each side
  public static swerveMath leftback = new swerveMath(Constants.BackLeftx, Constants.BackLefty, Backleftturn, gyro);

  //back right swerve 
  // motors for driving
  public static WPI_TalonFX Backrightdrive  = new WPI_TalonFX(Constants.BackRightdrive);
  public static WPI_TalonFX Backrightturn = new WPI_TalonFX(Constants.BackRightturn);
  //encoder
  public static CANCoder Backrightencoder = new CANCoder(Constants.Backrightencoder);
  //math for each side
  public static swerveMath rightback = new swerveMath(Constants.Backrightx, Constants.Backrighty, Backrightturn, gyro);


  // set control of swerve modula 
  public static void swervecontrol(WPI_TalonFX drivemoter, WPI_TalonFX turnmoter, swerveMath whatspot, double x, double y, double z){
  // gets field oration 
    double truex = whatspot.numbersafterfieldoriented(x, y, z, "x");
   double truey = whatspot.numbersafterfieldoriented(x, y, z, "y");
   double truez = whatspot.numbersafterfieldoriented(x, y, z, "z");
// sets the valuse 
  turnmoter.set(ControlMode.Position,whatspot.rotationAngle(truex, truey ,truez));
   drivemoter.set(ControlMode.PercentOutput, whatspot.speedset(truex, truey ,truez)); 
  } 
//full control of all swerves 
  public static void fullswervecontrol(double x, double y, double z){
    if(x== 0 && y== 0 && z== 0 )
  {
   leftfront.rotationAngle(1, 1, 0);
   rightfront.rotationAngle(-1, 1, 0);
   leftback.rotationAngle(1, -1, 0);
   rightback.rotationAngle(-1, -1, 0);
   Frontleftdrive.stopMotor();
   Frontrightdrive.stopMotor();
   Backleftdrive.stopMotor();
   Backrightdrive.stopMotor(); 
  }else{
    swervecontrol(Frontleftdrive, Frontleftturn, leftfront, x , y, z);
   swervecontrol(Frontrightdrive, Frontrightturn, rightfront, x, y, -z);
   swervecontrol(Backleftdrive, Backleftturn, leftback, x, y, z);
   swervecontrol(Backrightdrive, Backrightturn, rightback, x, y, z); 
  }
}
// resets gyro
public static void resetgyro(){
  gyro.reset();
}
// resets one falcons 
public static void resetfalcons(WPI_TalonFX motor){
  motor.getSensorCollection().setIntegratedSensorPosition(0, 0);
}
//resets all falcons 
public static void resetallfalcons(){
  resetfalcons(Frontleftdrive);
  resetfalcons(Frontleftturn);
  resetfalcons(Frontrightdrive);
  resetfalcons(Frontrightturn);
  resetfalcons(Backleftturn);
  resetfalcons(Backrightdrive);
  resetfalcons(Backleftdrive);
  resetfalcons(Backrightturn);
}
//set motors to defult state 
public static void motordefultstate(WPI_TalonFX motor){
  motor.configFactoryDefault();
  motor.config_kP(0, .1);
  motor.config_kI(0, 0.0003);
  motor.config_kD(0, 0.02);
  motor.config_kF(0, 0);
  motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,0);
  motor.configAllowableClosedloopError(0, 0, 0);
  motor.configFeedbackNotContinuous(true, 0);
  motor.setNeutralMode(NeutralMode.Brake);
  motor.setInverted(true);
}
//set all motors to defult 
public static void setallmotorsdefultstate(){
  motordefultstate(Frontleftdrive);
  motordefultstate(Frontleftturn);
  motordefultstate(Frontrightturn);
  motordefultstate(Frontrightdrive);
  motordefultstate(Backleftturn);
  motordefultstate(Backleftdrive);
  motordefultstate(Backrightdrive);
  motordefultstate(Backrightturn);
}
//sets turn motors to needed settings 
public static void setSwerveRotation(WPI_TalonFX motor, CANCoder encoder, double offset){
  motor.configPeakOutputForward(0.5);
  motor.configPeakOutputReverse(-0.5);
  motor.getSensorCollection().setIntegratedSensorPosition(encoder.getAbsolutePosition()/falcontickstodegrees, 0);
  encoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
  encoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
  encoder.configMagnetOffset(offset);
  encoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
  encoder.setStatusFramePeriod(CANCoderStatusFrame.SensorData, 100);
}
//sets all turn motors to needed settings 
public static void setallswerverotation(){
  setSwerveRotation(Frontleftturn, Frontleftencoder, Constants.Frontleftencoderoffset);
  setSwerveRotation(Frontrightturn, frontrightencoder, Constants.FrontRightencoderoffset);
  setSwerveRotation(Backleftturn, Backleftencoder, Constants.BackLeftencoderoffset);
  setSwerveRotation(Backrightturn, Backrightencoder, Constants.Backrightencoderoffset);
}
// set up drive motor
public static void drivemotersetup(WPI_TalonFX motor){
  motor.configOpenloopRamp(0.1);
  motor.setStatusFramePeriod(StatusFrame.Status_1_General, 20);}
  // set up all drive motors 
  public static void setupalldrivemotors(){
    drivemotersetup(Frontleftdrive);
    drivemotersetup(Frontrightdrive);
    drivemotersetup(Backrightdrive);
    drivemotersetup(Backleftdrive);
  }
  // reset everything back to default
  public static void fullreset(){
    setallmotorsdefultstate();
    setallswerverotation();
    setupalldrivemotors();
  } 
 
  public Drivetrain() {}

  @Override
  public void periodic(
  ) {
    SmartDashboard.putNumber("Left front encoder", Frontleftencoder.getAbsolutePosition());
    SmartDashboard.putNumber("Left back encoder", Backleftencoder.getAbsolutePosition());
    SmartDashboard.putNumber("Right Front encoder", frontrightencoder.getAbsolutePosition());
    SmartDashboard.putNumber("Right Back encoder", Backrightencoder.getAbsolutePosition());
    SmartDashboard.putNumber("y axis", RobotContainer.deadband(1, .05, RobotContainer.controller));
    SmartDashboard.putNumber("x axis", RobotContainer.deadband(0, .05, RobotContainer.controller));
    SmartDashboard.putNumber("z axis", RobotContainer.deadband(4, .05, RobotContainer.controller));
   // SmartDashboard.putNumber("x location", location.Currentxlocation());
   // SmartDashboard.putNumber("y location", location.Currentylocation());
    SmartDashboard.putNumber("angle", gyro.getAngle());
    // This method will be called once per scheduler run
  }
}
