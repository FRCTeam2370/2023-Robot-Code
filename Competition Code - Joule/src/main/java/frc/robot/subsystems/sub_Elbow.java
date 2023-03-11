package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class sub_Elbow extends SubsystemBase {

  public static WPI_TalonFX Elbow_motor = new WPI_TalonFX(12);
  //public static CANCoder Elbow_CANCoder = new CANCoder(13);

  private static void Reset_Elbow_Motor(WPI_TalonFX motor) {
    motor.configFactoryDefault();
    Elbow_Reset_Encoder();
    Send_Elbow_PID_Variables();
    //motor.setStatusFramePeriod(StatusFrame.Status_1_General, 25);
    motor.configClosedLoopPeakOutput(0, 0.70);
    motor.setNeutralMode(NeutralMode.Coast); 
  }

  public static void Send_Elbow_PID_Variables() {
    Elbow_motor.config_kP(0, 0.05);
    Elbow_motor.config_kI(0, 0);
    Elbow_motor.config_kD(0, 0.1);
    Elbow_motor.configClosedLoopPeakOutput(0, .70);
    Elbow_motor.configClosedloopRamp(0.3);
  }

  public static void Elbow_Reset_Encoder() {
    Elbow_motor.getSensorCollection().setIntegratedSensorPosition(0, 10);
  }

  /** Creates a new sub__Elbow. */
  public sub_Elbow() {
    Reset_Elbow_Motor(Elbow_motor);
  }

  @Override
  public void periodic() {
    
    SmartDashboard.putNumber("Elbow Motor Encoder Reading", Elbow_motor.getSensorCollection().getIntegratedSensorPosition());
    //SmartDashboard.putNumber("Elbow CANCoder", Elbow_CANCoder.getAbsolutePosition());
    if(RobotState.isDisabled()==true)
    {
      // When the robot is disabled, allow the arm to move
      Elbow_motor.setNeutralMode(NeutralMode.Coast);
    }
    else
    {
      // When the robot is enabled, put the shoulder into brake mode
      Elbow_motor.setNeutralMode(NeutralMode.Brake);
    }
  }
}
