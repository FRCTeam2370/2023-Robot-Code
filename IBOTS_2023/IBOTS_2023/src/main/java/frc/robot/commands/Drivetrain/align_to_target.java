// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.Swerve;
import frc.robot.subsystems.sub_sensor;

public class align_to_target extends CommandBase {
  /** Creates a new align_to_target. */
  private frc.robot.subsystems.Swerve m_Swerve;
  private double X;
  private double y;
  private double Z;
  public double trueanglemodulo;
  private boolean robotcnetricSup = true;
  public align_to_target(frc.robot.subsystems.Swerve m_Swerve, double X, double Y, double Z) {
    this.m_Swerve= m_Swerve;
    addRequirements(m_Swerve);
    this.X = X;
    this.y = Y;
    this.Z = Z;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(frc.robot.subsystems.Swerve.angle < 0) { Z = Z*-1;
    trueanglemodulo = -360;} 
    else{Z = Z*1;
    trueanglemodulo = 360;}
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override

  public void execute() {
  
    double Xspeed = frc.robot.subsystems.Swerve.swerve_X_movenment_PID.calculate(sub_sensor.X_lime, X);
    double Yspeed = -frc.robot.subsystems.Swerve.swerve_Y_movenment_PID.calculate(sub_sensor.Distence, y);
    double Zspeed = frc.robot.subsystems.Swerve.swerve_angle_movenment_PID.calculate(frc.robot.subsystems.Swerve.angle%trueanglemodulo, Z);
    m_Swerve.drive(new Translation2d(Yspeed, Xspeed).times(Constants.Swerve.maxSpeed), Zspeed , !robotcnetricSup, false);
    SmartDashboard.putNumber("y pid", Yspeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(frc.robot.subsystems.Swerve.swerve_X_movenment_PID.atSetpoint() && frc.robot.subsystems.Swerve.swerve_Y_movenment_PID.atSetpoint() && frc.robot.subsystems.Swerve.swerve_angle_movenment_PID.atSetpoint()){
      return true;
    }
    else{
    return false;} 
  }
}
