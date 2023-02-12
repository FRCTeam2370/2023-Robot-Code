// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.VisionActions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.sensors;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Sensors;
import frc.robot.subsystems.Vision;

public class DriveToDistanceFromApriLtag extends CommandBase {
  /** Creates a new DriveToDistanceFromApriLtag. */
  public DriveToDistanceFromApriLtag() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Switches to apriltag pipeline
    Sensors.SwitchPipline0();
    //Are there targets visible?
   
      //lined up on ApriltagX
      //Get in retroreflective readout distance
      //if (Sensors.TargetArea > ?) {
        //Get in retroreflective distance
      //}
      Drivetrain.fullswervecontrol(0, 1, 0);
      //Switch to retroreflective pipeline
      Sensors.SwitchPipline1();

    }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
