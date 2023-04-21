// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class auto_balence extends CommandBase {
  public double X;
  public double Z;
  public Swerve m_Swerve;
  private boolean robotcnetricSup = true;
  public double trueanglemodulo;
  /** Creates a new auto_balence. */
  public auto_balence(frc.robot.subsystems.Swerve m_Swerve, double X, double Z) {
    this.m_Swerve= m_Swerve;
    addRequirements(m_Swerve);
    this.X = X;
   
    this.Z = Z;}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(Swerve.angle < 0) { Z = Z*-1;
    trueanglemodulo = 180;} 
    else{Z = Z*1;
    trueanglemodulo = 180;}
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double Yspeed = frc.robot.subsystems.Swerve.swerve_auto_balance_Controller.calculate(Swerve.pitch, X);
    double Zspeed = frc.robot.subsystems.Swerve.swerve_angle_movenment_PID.calculate(frc.robot.subsystems.Swerve.angle%trueanglemodulo, Z);
    m_Swerve.drive(new Translation2d(-Yspeed, 0).times(Constants.Swerve.maxSpeed), 0 , !robotcnetricSup, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Swerve.swerve_auto_balance_Controller.atSetpoint() == true ? true : false;
  }
}
