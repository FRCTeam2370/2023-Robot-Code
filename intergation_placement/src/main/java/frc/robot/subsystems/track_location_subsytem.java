// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class track_location_subsytem extends SubsystemBase {
  /** Creates a new track_location_subsytem. */
  public track_location_subsytem() {}
public static AHRS gyro = new AHRS();
  public static Acelation_to_place findlocation = new Acelation_to_place(gyro);
  @Override
  public void periodic() {
findlocation.calculatpostion(gyro);
    double xaxis = findlocation.X;
    double yaxis = findlocation.Y;
    double asthecrowwent = Math.sqrt((xaxis)*xaxis+yaxis*yaxis);
    SmartDashboard.putNumber("x location", xaxis);
    SmartDashboard.putNumber("y axis", yaxis);
    SmartDashboard.putNumber("as the crow went", asthecrowwent);
    SmartDashboard.putNumber("distance traveled", findlocation.accelerationtraveled);
    // This method will be called once per scheduler run
  }
}
