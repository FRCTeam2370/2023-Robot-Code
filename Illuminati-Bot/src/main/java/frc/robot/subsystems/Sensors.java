// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Sensors extends SubsystemBase {
  /** Creates a new Sensors. */
  public Sensors() {}
    //Limelight Networktable collection
    
   public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
   public static NetworkTableEntry tx = table.getEntry("tx");
   public static NetworkTableEntry ty = table.getEntry("ty");
   public static NetworkTableEntry ta = table.getEntry("ta");
   public static NetworkTableEntry tid = table.getEntry("tid");
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
