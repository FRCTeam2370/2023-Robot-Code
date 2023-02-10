// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.security.PublicKey;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
<<<<<<< Updated upstream
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
=======
import edu.wpi.first.wpilibj.RobotState;
>>>>>>> Stashed changes
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
    leftshouldMoter.configPeakOutputForward(.05);
    leftshouldMoter.configPeakOutputReverse(-.1);
    leftelbowpid.setTolerance(1);    
    leftshouldMoter.setNeutralMode(NeutralMode.Brake);
  }

  public static boolean arminplace = false;
  public static boolean pickupattempted = false;
  public static boolean pickupworked = false;
  public static WPI_TalonFX leftshouldMoter = new WPI_TalonFX(Constants.LShoulderMotor);
  public static DigitalInput leftshouldmagneticsensor = new DigitalInput(Constants.LShoulderMagneticSensor);
  public static CANCoder Leftshouldercoder = new CANCoder(Constants.LShoulderCANCoder);
  public static DoubleSolenoid Solenoid0 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 
  Constants.Sol0FowardChannel, Constants.Sol0ReverseChannel);
  public static PneumaticsControlModule PCM0 = new PneumaticsControlModule();
  /*
   * public static WPI_TalonFX leftelbowMoter = new
   * WPI_TalonFX(Constants.LElebowMotor);
   * public static DigitalInput leftelbowmagneticsensor = new
   * DigitalInput(Constants.LElbowMagneticSensor);
   * public static CANCoder Leftelbowcoder = new
   * CANCoder(Constants.LElbowCANCoder);
   */

  public static PIDController leftshouldpid = new PIDController(Constants.LShoulder_kp, Constants.LShoulder_ki,
      Constants.LShoulder_kd);
  public static PIDController leftelbowpid = new PIDController(Constants.LElbow_kp, Constants.LElbow_ki,
      Constants.LElbow_kd);

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

  public static void movearmslow(WPI_TalonFX motor) {
    motor.set(-.1);
  }


  public static void OpenGripper(){
    Solenoid0.set(Value.kForward);
  }

  public static void CloseGripper(){
    Solenoid0.set(Value.kReverse);
  }
  @Override
  public void periodic() {
  
    SmartDashboard.putNumber("shoulder postion", Leftshouldercoder.getPosition());
    SmartDashboard.putBoolean("magnectic switch", leftshouldmagneticsensor.get());
    SmartDashboard.putNumber("shoulder power usage", leftshouldMoter.getMotorOutputVoltage());
  }
}