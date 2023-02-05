// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

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

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    x = 0;
    y = 0;
    autobalancepid.setTolerance(.5);
 
      leftback.setdtivemode(true);
      leftfront.setdtivemode(true);
      rightback.setdtivemode(true);
      rightfront.setdtivemode(true);
      fullreset();
      firststart = true; 
  
  }

  public static double drivespeedmax = .25;
  public static double rotationspeedmax = .30;
  public static double gyroOffSet = 0;
  public static AHRS gyro = new AHRS(SerialPort.Port.kMXP);
  public static double falcontickstodegrees = 0.01373;
  public static double autobalancespeed = 0;
 
  public static boolean firststart = false;
  public static PIDController autobalancepid = new PIDController(.02, .0, 0.00);
  public static PIDController automovePID = new PIDController(.025, 0, 0);
  public static PIDController autoturn = new PIDController(.004, .00, 0.00);
  public static double xyangle;
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
  public static swerveMath leftback = new swerveMath(Constants.BackLeftx, Constants.BackLefty, Backleftturn,gyro);

  //back right swerve 
  // motors for driving
  public static WPI_TalonFX Backrightdrive  = new WPI_TalonFX(Constants.BackRightdrive);
  public static WPI_TalonFX Backrightturn = new WPI_TalonFX(Constants.BackRightturn);
  //encoder
  public static CANCoder Backrightencoder = new CANCoder(Constants.Backrightencoder);
  //math for each side
  public static swerveMath rightback = new swerveMath(Constants.Backrightx, Constants.Backrighty, Backrightturn,gyro);
public static double rloffset = 0;
public static double rroffset = 0;
public static double floffset = 0;
public static double froffset = 0;

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
    xyangle =(Math.atan2(y, x));
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
   swervecontrol(Frontleftdrive, Frontleftturn, leftfront, x*drivespeedmax , y*drivespeedmax, -z*rotationspeedmax);
   swervecontrol(Frontrightdrive, Frontrightturn, rightfront, x*drivespeedmax, y*drivespeedmax, z*rotationspeedmax);
   swervecontrol(Backleftdrive, Backleftturn, leftback, x*drivespeedmax, y*drivespeedmax, -z*rotationspeedmax);
   swervecontrol(Backrightdrive, Backrightturn, rightback, x*drivespeedmax, y*drivespeedmax, z*rotationspeedmax); 
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
public static void setSwerveRotation(WPI_TalonFX motor, CANCoder encoder, double offset, boolean encoderupsidedown){
  motor.configPeakOutputForward(0.5);
  motor.configPeakOutputReverse(-0.5);
  if(encoderupsidedown){motor.getSensorCollection().setIntegratedSensorPosition(-encoder.getAbsolutePosition()/falcontickstodegrees, 0);}
  else{motor.getSensorCollection().setIntegratedSensorPosition(encoder.getAbsolutePosition()/falcontickstodegrees, 0);}
  encoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
  encoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
  encoder.configMagnetOffset(offset);
  encoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
  encoder.setStatusFramePeriod(CANCoderStatusFrame.SensorData, 100);
}
//sets all turn motors to needed settings 
public static void setallswerverotation(){
  setSwerveRotation(Frontleftturn, Frontleftencoder, Constants.Frontleftencoderoffset,false);
  setSwerveRotation(Frontrightturn, frontrightencoder, Constants.FrontRightencoderoffset,false);
  setSwerveRotation(Backleftturn, Backleftencoder, Constants.BackLeftencoderoffset,false);
  setSwerveRotation(Backrightturn, Backrightencoder, Constants.Backrightencoderoffset,false);
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
    gyro.reset();
  } 
  // used in auto vars
public static double frontrightencodervalue;
public static double frontleftencodervalue;
public static double backrightencodervalue;
public static double backleftencodervalue;
public static double distencetravel;
public static double currentavarge;
public static double pastavarge = 0;
public static double x = 0;
public static double y = 0;

  @Override
  public void periodic() {
    SmartDashboard.putNumber("encoder angle", Frontleftturn.getSensorCollection().getIntegratedSensorPosition());
    SmartDashboard.putNumber("encoder angle", Frontleftturn.getSensorCollection().getIntegratedSensorPosition());
     // collects distence
  frontleftencodervalue = Frontleftdrive.getSensorCollection().getIntegratedSensorPosition();
  frontrightencodervalue = Frontrightdrive.getSensorCollection().getIntegratedSensorPosition();
  backrightencodervalue = Backrightdrive.getSensorCollection().getIntegratedSensorPosition();
  backleftencodervalue = Backleftdrive.getSensorCollection().getIntegratedSensorPosition();
  //avarge them
  currentavarge = (frontleftencodervalue+frontrightencodervalue+backleftencodervalue+backrightencodervalue)/4;
 //find how far we went in inches
  distencetravel =Math.abs(currentavarge - pastavarge)/1101;
  pastavarge = currentavarge;
  //finds how far we went on the x and y axis
    x = x+distencetravel*Math.cos(xyangle);
    y = y+distencetravel*Math.sin(xyangle);
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("deseried ang;e", Math.toDegrees(xyangle));
  SmartDashboard.putNumber("y", y);
  SmartDashboard.putNumber("x", x);
    SmartDashboard.putNumber("angle", gyro.getAngle());
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("test", true);
  }
}
