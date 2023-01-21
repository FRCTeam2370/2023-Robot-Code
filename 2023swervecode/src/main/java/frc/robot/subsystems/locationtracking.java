// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;

import com.kauailabs.navx.frc.AHRS;

/** Add your docs here. */
public class locationtracking {
    private static double xaxis = 0;
    private static double yaxis = 0;
    public static double delta = 0;
    private AHRS ahrs;
    public double xpostion;
    public static double ypostion;
    public locationtracking(AHRS ahrs){
        this.ahrs = ahrs;
    }

public void updatepostion(){
  xpostion = xaxis+(ahrs.getVelocityY()*Math.cos(ahrs.getAngle()))/50;
  xaxis = xpostion;
  ypostion = yaxis+(ahrs.getVelocityY()*Math.sin(ahrs.getAngle()))/50;
  yaxis = ypostion;
}
public double returnxpostion(){
    return xpostion;
}
public double returnypostion(){
    return ypostion;
}
}
