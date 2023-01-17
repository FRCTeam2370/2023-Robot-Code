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
   //tx and ty are in degrees, the distance from scoring posts
   public static NetworkTableEntry tx = table.getEntry("tx");
   public static NetworkTableEntry ty = table.getEntry("ty");
   //ta is the rough distance from the apriltags
   public static NetworkTableEntry ta = table.getEntry("ta");
   //tid is the apriltag the limelight is currently locked on
   public static NetworkTableEntry tid = table.getEntry("tid");
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
