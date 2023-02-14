
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class sub_Shoulder extends SubsystemBase {

  public static WPI_TalonFX Shoulder_motor = new WPI_TalonFX(Constants.ShoulderConstants.ShoulderMotorID);
  public static DigitalInput Shoulder_Magnetic_Sensor = new DigitalInput(Constants.ShoulderConstants.Magnetic_Sensor_Pin);
  public static CANCoder Shoulder_CANCoder = new CANCoder(11);
  

  public void Setup_PID() {
  }

  public static void Set_Shoulder_Motor_Up() {
    Shoulder_motor.configFactoryDefault();
    Shoulder_motor.config_kP(0, 0.12);
    Shoulder_motor.config_kI(0, 0);
    Shoulder_motor.config_kD(0, 0);
    Shoulder_motor.setNeutralMode(NeutralMode.Coast);
    Shoulder_motor.setStatusFramePeriod(StatusFrame.Status_1_General, 25);
    Shoulder_motor.configClosedLoopPeakOutput(0, 0.15);
    Shoulder_motor.configClosedloopRamp(0.2);
    Shoulder_motor.setNeutralMode(NeutralMode.Coast); 
    System.out.println("Shoulder set up complete");

  }

  public static void Shoulder_Reset_Encoder() {
    Shoulder_motor.getSensorCollection().setIntegratedSensorPosition(0, 10);
  }

  public sub_Shoulder() {
    Shoulder_Reset_Encoder();
    Set_Shoulder_Motor_Up();
    System.out.println("Set up shoulder");
    sub_Shoulder.Shoulder_motor.set(ControlMode.Position, Constants.ShoulderConstants.ShoulderBackPosition);    
  }
  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Shoulder Motor Encoder Reading", Shoulder_motor.getSensorCollection().getIntegratedSensorPosition());
    SmartDashboard.putNumber("Shoulder CANCoder", Shoulder_CANCoder.getAbsolutePosition());

    if(RobotState.isDisabled() == true)
    {
      Shoulder_motor.setNeutralMode(NeutralMode.Coast);
    }
    else
    {
      Shoulder_motor.setNeutralMode(NeutralMode.Brake);
    }
  }
}
