// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.math.estimator.AngleStatistics;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.sub_sensor;

public class align_with_driver_input extends CommandBase {
  /** Creates a new align_with_driver_input. */
  private frc.robot.subsystems.Swerve m_Swerve;
  private double X;
  public double trueanglemodulo;
  private double Z;
  private boolean robotcnetricSup = true;
  public align_with_driver_input(frc.robot.subsystems.Swerve m_Swerve, double X, double Z) {
    this.m_Swerve= m_Swerve;
    addRequirements(m_Swerve);
    this.X = X;
   
    this.Z = Z;
  
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(Swerve.angle < 0) { Z = Z*-1;
    trueanglemodulo = -360;} 
  
    else{Z = Z*1;
    trueanglemodulo = 360;}
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double Xspeed = frc.robot.subsystems.Swerve.swerve_X_movenment_PID.calculate(sub_sensor.X_lime, X);
    double Yspeed = RobotContainer.convertaxis(RobotContainer.driver, RobotContainer.Rdriver, 1);
    double Zspeed = frc.robot.subsystems.Swerve.swerve_angle_movenment_PID.calculate(frc.robot.subsystems.Swerve.angle%trueanglemodulo, Z);
    m_Swerve.drive(new Translation2d(Yspeed, Xspeed).times(Constants.Swerve.maxSpeed), Zspeed , !robotcnetricSup, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
  }
}
