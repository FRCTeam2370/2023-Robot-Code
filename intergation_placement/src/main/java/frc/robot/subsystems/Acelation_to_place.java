// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;

import com.kauailabs.navx.frc.AHRS;

/** Add your docs here. */
public class Acelation_to_place {
    public  double xacceleration = 0;
    public  double yacceleration = 0;
    public  double xvelocity = 0;
    public  double yvelocity = 0;
    public double X = 0;
    public double Y = 0;
    public double accelerationtraveled = 0;
    public  double velocitytraveled = 0;
    public  double distancetraveled = 0;
    public AHRS accelormeter;
    public double meterstofeet = 32.1751969;
    public double offset = 0;
    public Acelation_to_place(AHRS accelormeter){
        this.accelormeter = accelormeter;
    } 
public  void findxacceleration( AHRS gyro){
    xacceleration = Math.round(100*gyro.getRawAccelX()*Math.cos(gyro.getAngle()))/100;
}
public  void findyacceleration(AHRS gyro){
    yacceleration = Math.round(100*gyro.getRawAccelY()*Math.sin(gyro.getAngle()))/100;
}
public  void findxvelocity(AHRS gyro){
    xvelocity = (xvelocity + xacceleration*.02);
}
public  void findyvelocity(AHRS gyro){
    yvelocity = (yvelocity+ yacceleration*.02);
}
public  void findx(AHRS gyro){
    X = (xvelocity*.02 + X);
}
public  void findy(AHRS gyro){
    Y = xvelocity*.02 + Y;
}
public  void findaccelerationtraveled(AHRS gyro){
accelerationtraveled = Math.round(100*gyro.getRawAccelY())/100;
}
public  void findvelocitytraveled(AHRS gyro){
    velocitytraveled = (velocitytraveled +accelerationtraveled*.02);
}
public  void finddistancetraveled(AHRS gyro){
    distancetraveled = velocitytraveled*.02 +distancetraveled;
}
 
public void calculatpostion(AHRS gyro){
    findxacceleration(gyro);
    findxvelocity(gyro);
    findx(gyro);
    findyacceleration(gyro);
    findxvelocity(gyro);
    findy(gyro);
    findaccelerationtraveled(gyro);
    findvelocitytraveled(gyro);
    finddistancetraveled(gyro);
}
    }