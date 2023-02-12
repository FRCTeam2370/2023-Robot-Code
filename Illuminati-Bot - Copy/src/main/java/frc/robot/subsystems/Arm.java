// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

<<<<<<< HEAD
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
=======
>>>>>>> 814ca2d3bbbeef9e826ca801cbe2b6ad8d6f8c9c
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/*things left to do
 * 1.tune PID for arm
 *  A. tune shoulder PID
 *  B. tune elbow PID
 *  C. Please ask dan for help if do not know to tune PID
 *  D. Please read this link if do not know what a PID control loop is 
 * https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/introduction-to-pid.html
 * 2. Add Elbow stuff
 * 3. Add gripper stuff
 * 4. create SequentialCommandGroups for arm postions
 * 
 */
public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  public Arm() {
    leftshouldMoter.configFactoryDefault();
    leftshouldMoter.configPeakOutputForward(.1);
    leftshouldMoter.configPeakOutputReverse(-.1);
<<<<<<< HEAD
    leftelbowMoter.configFactoryDefault();
    leftelbowMoter.configClosedLoopPeakOutput(0, .35);
    leftshouldMoter.configClosedLoopPeakOutput(0, .35);
    leftelbowMoter.config_kP(0, .05);
    leftelbowMoter.config_kI(0, 0);
    leftelbowMoter.config_kD(0, 0);
    leftshouldMoter.config_kP(0, .12);
    leftshouldMoter.config_kI(0, 0);
    leftshouldMoter.config_kD(0, 0);
    leftelbowMoter.set(ControlMode.Position, leftelbowMoter.getSensorCollection().getIntegratedSensorPosition());
    leftshouldMoter.set(ControlMode.Position, leftshouldMoter.getSensorCollection().getIntegratedSensorPosition());
=======
>>>>>>> 814ca2d3bbbeef9e826ca801cbe2b6ad8d6f8c9c

  }

  public static boolean arminplace = false;
  public static boolean pickupattempted = false;
  public static boolean pickupworked = false;
  public static WPI_TalonFX leftshouldMoter = new WPI_TalonFX(Constants.Shoulder.LShoulderMotor);
  public static DigitalInput leftshouldmagneticsensor = new DigitalInput(Constants.Shoulder.LShoulderMagneticSensor);
  public static CANCoder Leftshouldercoder = new CANCoder(Constants.Shoulder.LShoulderCANCoder);
  public static WPI_TalonFX leftelbowMoter = new WPI_TalonFX(Constants.Elbow.LElebowMotor); 
  public static CANCoder ElbowCanCoder = new CANCoder(Constants.Elbow.LElbowCANCoder);
  public static DoubleSolenoid Solenoid0 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
  public static Compressor AirCompressor = new Compressor(Constants.PCMCANID, PneumaticsModuleType.REVPH); 
 
  /*
   * public static WPI_TalonFX leftelbowMoter = new
   * WPI_TalonFX(Constants.LElebowMotor);
   * public static DigitalInput leftelbowmagneticsensor = new
   * DigitalInput(Constants.LElbowMagneticSensor);
   * public static CANCoder Leftelbowcoder = new
   * CANCoder(Constants.LElbowCANCoder);
   */

<<<<<<< HEAD


=======
  public static PIDController leftshouldpid = new PIDController(Constants.LShoulder_kp, Constants.LShoulder_ki,
      Constants.LShoulder_kd);
  public static PIDController leftelbowpid = new PIDController(Constants.LElbow_kp, Constants.LElbow_ki,
      Constants.LElbow_kd);
>>>>>>> 814ca2d3bbbeef9e826ca801cbe2b6ad8d6f8c9c

  /*
   * public static boolean getelbowmagneticsonsor(){
   * return true;
   * }
   */
  public static void shoulderstartstuff(WPI_TalonFX motor, CANCoder encoder) {
    motor.configFactoryDefault();
    motor.configPeakOutputForward(.3);
    motor.configPeakOutputReverse(-.3);
    encoder.configFactoryDefault();
    encoder.configMagnetOffset(encoder.getPosition());
  }

  public static void ElbowStartStuff(WPI_TalonFX motor1, CANCoder encoder1){
    motor1.configFactoryDefault();
    motor1.configPeakOutputForward(0.3); 
    motor1.configPeakOutputReverse(-0.3);
<<<<<<< HEAD
=======
    encoder1.configFactoryDefault();
    encoder1.configMagnetOffset(encoder1.getPosition()); 
  }
>>>>>>> 814ca2d3bbbeef9e826ca801cbe2b6ad8d6f8c9c

  }
public static boolean isgripperclosed = false;
  public static void movearmslow(WPI_TalonFX motor) {
    motor.set(-.1);
  }

  public static void EnableCompressor(){
    AirCompressor.enableDigital();
  }

  public static void DissableCompressor(){
    AirCompressor.disable();
  }

  public static void OpenGripper(){
    Solenoid0.set(Value.kForward);
    isgripperclosed = true;
  }

  public static void CloseGripper(){
    Solenoid0.set(Value.kReverse);
    isgripperclosed = false;
  }
  @Override
  public void periodic() {
<<<<<<< HEAD
    if(RobotState.isDisabled() == true){
leftelbowMoter.setNeutralMode(NeutralMode.Coast);
leftshouldMoter.setNeutralMode(NeutralMode.Coast);
    }
    else{
      leftelbowMoter.setNeutralMode(NeutralMode.Brake);
leftshouldMoter.setNeutralMode(NeutralMode.Brake);
    }
    SmartDashboard.putNumber("shoulder postion", leftshouldMoter.getSensorCollection().getIntegratedSensorPosition());

    SmartDashboard.putNumber("Elbow motorpostion", leftelbowMoter.getSensorCollection().getIntegratedSensorPosition());  }
=======
    SmartDashboard.putNumber("Elbow position", ElbowCanCoder.getAbsolutePosition()); 
    SmartDashboard.putNumber("shoulder postion", Leftshouldercoder.getAbsolutePosition());
    SmartDashboard.putBoolean("magnectic switch", leftshouldmagneticsensor.get());
  }
>>>>>>> 814ca2d3bbbeef9e826ca801cbe2b6ad8d6f8c9c
}