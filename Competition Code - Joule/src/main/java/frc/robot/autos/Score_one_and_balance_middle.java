// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.Top_goal;
import frc.robot.commands.UltraStow;
import frc.robot.commands.stow;
import frc.robot.commands.Drivetrain.auto_balence;
import frc.robot.commands.Elbow.Move_Elbow;
import frc.robot.commands.Gripper.CloseGripper;
import frc.robot.commands.Gripper.OpenGripper;
import frc.robot.commands.Intake.ForwardIntake;
import frc.robot.commands.Intake.ReverseIntake;
import frc.robot.commands.Intake.drop_intake;
import frc.robot.commands.Intake.stop_intake;
import frc.robot.commands.Shoulder.Move_Shoulder;
import frc.robot.subsystems.Cube_Intake;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Gripper;
import frc.robot.subsystems.sub_Shoulder;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Score_one_and_balance_middle extends SequentialCommandGroup {
  /** Creates a new score_one. */
  public Score_one_and_balance_middle(frc.robot.subsystems.Swerve s_Swerve, sub_Elbow m_sub_Elbow, sub_Shoulder m_sub_Shoulder, sub_Gripper m_Sub_Gripper, Cube_Intake m_cube ) {
    TrajectoryConfig config =
    new TrajectoryConfig(
            Constants.AutoConstants.kMaxSpeedMetersPerSecond,
            Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
        .setKinematics(Constants.Swerve.swerveKinematics);
        Trajectory exampleTrajectory =
        TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(0, 0, new Rotation2d(0)),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(new Translation2d(.5, 0)),
      // End 3 meters straight ahead of where we started, facing forward
      new Pose2d(1.1, 0, new Rotation2d(0)),
      config);
      TrajectoryConfig config2 =
      new TrajectoryConfig(
              Constants.AutoConstants.kMaxSpeedMetersPerSecond,
              Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
          .setKinematics(Constants.Swerve.swerveKinematics);
          Trajectory exampleTrajectory2 =
          TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(180)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(new Translation2d(-1, 0)),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(-2.5, 0, new Rotation2d(0)),
        config2);
        TrajectoryConfig config3 =
        new TrajectoryConfig(
                Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(Constants.Swerve.swerveKinematics);
            Trajectory exampleTrajectory3 =
            TrajectoryGenerator.generateTrajectory(
          // Start at the origin facing the +X direction
          new Pose2d(-2.5, 0, new Rotation2d(180)),
          // Pass through these two interior waypoints, making an 's' curve path
          List.of(new Translation2d(-1, 0)),
          // End 3 meters straight ahead of where we started, facing forward
          new Pose2d(-1.5, 0, new Rotation2d(0)),
          config2);
var thetaController =
  new ProfiledPIDController(
      Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
thetaController.enableContinuousInput(-Math.PI, Math.PI);

SwerveControllerCommand swerveControllerCommand =
  new SwerveControllerCommand(
      exampleTrajectory,
      s_Swerve::getPose,
      Constants.Swerve.swerveKinematics,
      new PIDController(Constants.AutoConstants.kPXController, 0, 0),
      new PIDController(Constants.AutoConstants.kPYController, 0, 0),
      thetaController,
      s_Swerve::setModuleStates,
      s_Swerve);
      SwerveControllerCommand swerveControllerCommand2 =
      new SwerveControllerCommand(
          exampleTrajectory2,
          s_Swerve::getPose,
          Constants.Swerve.swerveKinematics,
          new PIDController(Constants.AutoConstants.kPXController, 0, 0),
          new PIDController(Constants.AutoConstants.kPYController, 0, 0),
          thetaController,
          s_Swerve::setModuleStates,
          s_Swerve);
          SwerveControllerCommand swerveControllerCommand3 =
          new SwerveControllerCommand(
              exampleTrajectory3,
              s_Swerve::getPose,
              Constants.Swerve.swerveKinematics,
              new PIDController(Constants.AutoConstants.kPXController, 0, 0),
              new PIDController(Constants.AutoConstants.kPYController, 0, 0),
              thetaController,
              s_Swerve::setModuleStates,
              s_Swerve);
addCommands(new CloseGripper(m_Sub_Gripper),new Top_goal(m_sub_Elbow, m_sub_Shoulder),  new InstantCommand(() -> s_Swerve.resetOdometry(exampleTrajectory.getInitialPose())),
  swerveControllerCommand, new OpenGripper(m_Sub_Gripper), new WaitCommand(1), swerveControllerCommand2,new UltraStow(m_sub_Elbow, m_sub_Shoulder), new drop_intake(m_cube), new ForwardIntake(m_cube), new WaitCommand(1), new stop_intake(m_cube),
  
  
  new InstantCommand(()-> Swerve.setoffset(0)), swerveControllerCommand3, new auto_balence(s_Swerve, 0, 0)

  );
}
}