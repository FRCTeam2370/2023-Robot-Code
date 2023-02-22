package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class sub_Gripper extends SubsystemBase {

  public static DoubleSolenoid Solenoid0 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
  public static Compressor AirCompressor = new Compressor(Constants.Gripper.PCMCanID, PneumaticsModuleType.REVPH);

  /** Creates a new sub_Gripper. */
  public sub_Gripper() {}

  public static void EnableCompressor(){
    AirCompressor.enableDigital();
  }
  public static void DisableCompressor(){
    AirCompressor.disable();
  }
  public static void OpenGripper(){
    Solenoid0.set(Value.kForward);
  }
  public static void CloseGripper(){
    Solenoid0.set(Value.kReverse);
  }

  @Override
  public void periodic() {
  }
}
