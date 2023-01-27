// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// Limelight sample code imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision extends SubsystemBase {
  /** Creates a new Vision. */
  public Vision() {}
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tid = table.getEntry("tid");        
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //tx and ty are targets


    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double num = tid.getInteger(0);



    SmartDashboard.putNumber("TargetX", x);
    SmartDashboard.putNumber("TargetY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("AprilTagNum", num);
    SmartDashboard.putNumber("Target Dist", dist);

  }
}
