// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.swervelib.locationtracking;


import java.util.ArrayList;


import com.kauailabs.navx.frc.AHRS;

/** Add your docs here. */
public class locationfinder {

public ArrayList<Double> xacceleration = new ArrayList<Double>();
public ArrayList<Double> yacceleration = new ArrayList<Double>();
public ArrayList<Double> xvelocity = new ArrayList<Double>();
public ArrayList<Double> yvelocity = new ArrayList<Double>();
public ArrayList<Double> X = new ArrayList<Double>();
public ArrayList<Double> Y = new ArrayList<Double>();
public AHRS accelormeter;
public double meterstofeet = 32.1751969;
public double offset = 0;
public locationfinder(AHRS accelormeter){
    this.accelormeter = accelormeter;
} 

public void addxacceleration(){
if(xacceleration.size() <= 2){
    xacceleration.add(offset);
}
else{
    xacceleration.add(accelormeter.getWorldLinearAccelX()*meterstofeet);}
}
public void addyacceleration(){
    if(yacceleration.size() <= 2){
        yacceleration.add(offset);}
        else{
yacceleration.add(accelormeter.getWorldLinearAccelY()*meterstofeet);}
}
public void findxvelocoty(){
    if(xvelocity.size() <= 2){
        xvelocity.add(offset);
    }
    else{
    xvelocity.add(xvelocity.get(xvelocity.size()-1)+(xacceleration.get(xacceleration.size()-1)));}
}
public void findyvelocoty(){
    if(yvelocity.size() <= 2){
        yvelocity.add(offset);
    }
    else{
    yvelocity.add(yvelocity.get(yvelocity.size()-1)+(yacceleration.get(yacceleration.size()-1)));}
}
public void findxlocation(){
    if(X.size() <= 2){
    X.add(offset);
    }
    else{
    X.add(X.get(X.size()-1)+xvelocity.get(xvelocity.size()-1));}}
 public void findylocation(){
     if(Y.size() <= 2){
         Y.add(offset);
     }
     else{
                Y.add(Y.get(Y.size()-1)+yvelocity.get(yvelocity.size()-1));}

    }
public double Currentxlocation(){ 
    addxacceleration();
    findxvelocoty();
    findxlocation();
if(X.size() <= 2){    
    return X.get(X.size()-1);}
else{
    return 0;
}
}
public double Currentylocation(){
    addyacceleration();
    findyvelocoty();
    findylocation();
    if(Y.size() <= 2){    
        return Y.get(Y.size()-1);}
    else{
        return 0;
}
}
}
