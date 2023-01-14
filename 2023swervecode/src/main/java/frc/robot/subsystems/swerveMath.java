// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.RobotState;

/** Add your docs here. */
public class swerveMath {
    private double positionX, positionY;
    private WPI_TalonFX anglemoter;
    public static double falcontickstodegrees = 0.01373;
    public static boolean reversed;
    private boolean fieldOriented = false;
    private AHRS gyro;
    public swerveMath(double positionX, double positionY, WPI_TalonFX anglemoter, AHRS gyro){
        this.positionX=positionX;
        this.positionY=positionY;    
        this.anglemoter=anglemoter;
        this.gyro = gyro;
    }


// tells rotation angle
public double rotationAngle(double x, double y, double z){
    //tell the "real" X and Y for are wheel to turn to
    double Wxi = x+ z*positionY;
    double Wyi = y+ z*positionX;

    //gets us encoder angle by take it from the falcon 
    double encoderAngle = (anglemoter.getSensorCollection().getIntegratedSensorAbsolutePosition()*falcontickstodegrees);
    //make sures encoder angle is between 0 and 360
    double encoderAngle360 = encoderAngle>0? encoderAngle%360:(encoderAngle%360)+360;
    // wanted angle from joystick
    double wantedangle = ((180/Math.PI)*Math.atan2(Wyi, Wxi)+180);
    // the distance between wanted angle and our current angle
    double distancetoangle = (wantedangle - encoderAngle360);

    // finds the shortest path for the wheel
    if(Math.abs(distancetoangle)>180){
        distancetoangle = distancetoangle>0? -360+distancetoangle:distancetoangle+360; 
    }
    //then finds a even short path by making wheel move backwards
    if(Math.abs(distancetoangle)>90){
        distancetoangle = distancetoangle<0? distancetoangle+180%360: distancetoangle-180%360;
        reversed = true;
    }
    else{
        reversed = false;
    }
    double newtarget = distancetoangle+encoderAngle360;

    return newtarget/falcontickstodegrees;
}
public double speedset(double x, double y, double z){
    //tell the "real" X and Y for our wheel speed
    double Wxi = x+ z*positionY;
    double Wyi = y+ z*positionX;
// finds the speed use trig(we "love" trig here)
    double speed = Math.sqrt((Wyi*Wyi)+(Wxi*Wxi));
// if the quicks path for wheel turn is to have drive wheel negative, make the wheel go backwords    
    if(reversed){
        speed=speed*-1;
    }
//return the speed!!!!!!! vrmmmmmmm
    return speed;
    
}
    // gives gyro off set 
//tells robot if to use offset or not
public double getgyroangle(){
    if(RobotState.isTeleop()){
        return (gyro.getAngle()+Drivetrain.gyroOffSet+90)%360;}

    else{ 
        return gyro.getAngle();
    }
}
public void setdtivemode(boolean setfieldoriented){
    fieldOriented=setfieldoriented;
}
public double numbersafterfieldoriented(double x, double y, double z, String whataxis){
    double xFinal;
    double yFinal;
    double zFinal;

    if(fieldOriented){
        double radians = getgyroangle() * Math.PI / 180;
        //current x
        double cos = Math.cos(radians);
        //current y
        double sin = Math.sin(radians);
        double fieldX = (x * cos + y * sin);
        double fieldY = (x * sin + y * cos);
        xFinal=fieldX;
        yFinal=fieldY;
        zFinal=z;
    } else{
        xFinal = x;
        yFinal = y;
        zFinal = z;
    }
    // returns the need axis 
if(whataxis == "x" || whataxis == "X"){
    return xFinal;
}
else if(whataxis == "y" || whataxis == "Y"){
    return yFinal;
}
else{
    return zFinal;
}
}





}
