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
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.Drivetrain.align_to_target;
import frc.robot.commands.Drivetrain.auto_balence;
import frc.robot.commands.Elbow.Move_Elbow;
import frc.robot.commands.Gripper.CloseGripper;
import frc.robot.commands.Gripper.OpenGripper;
import frc.robot.commands.Shoulder.Move_Shoulder;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Gripper;
import frc.robot.subsystems.sub_Shoulder;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class score_one_and_balance extends SequentialCommandGroup {
  /** Creates a new score_one. */
  public score_one_and_balance(frc.robot.subsystems.Swerve s_Swerve, sub_Elbow m_sub_Elbow, sub_Shoulder m_sub_Shoulder, sub_Gripper m_Sub_Gripper) {
    TrajectoryConfig config =
    new TrajectoryConfig(
            Constants.AutoConstants.kMaxSpeedMetersPerSecond,
            Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
        .setKinematics(Constants.Swerve.swerveKinematics);
        Trajectory Trajectory1 =
        TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(0, 0, new Rotation2d(0)),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(new Translation2d(.1, 0)),
      // End 3 meters straight ahead of where we started, facing forward
      new Pose2d(.16, 0, new Rotation2d(0)),
      config);
      TrajectoryConfig config2 =
      new TrajectoryConfig(
              Constants.AutoConstants.kMaxSpeedMetersPerSecond,
              Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
          .setKinematics(Constants.Swerve.swerveKinematics);
          Trajectory Trajectory2 =
          TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(.16, 0, new Rotation2d(180)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(new Translation2d(-4, 0)),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(-8.6, 0, new Rotation2d(0)),
        config2);
        TrajectoryConfig config3 =
        new TrajectoryConfig(
                Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(Constants.Swerve.swerveKinematics);
            Trajectory Trajectory3 =
            TrajectoryGenerator.generateTrajectory(
          // Start at the origin facing the +X direction
          new Pose2d(0, 0, new Rotation2d(0)),
          // Pass through these two interior waypoints, making an 's' curve path
          List.of(new Translation2d(2, 0)),
          // End 3 meters straight ahead of where we started, facing forward
          new Pose2d(3, 0, new Rotation2d(0)),
          config3);
var thetaController =
  new ProfiledPIDController(
      Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
thetaController.enableContinuousInput(-Math.PI, Math.PI);

SwerveControllerCommand swerveControllerCommand =
  new SwerveControllerCommand(
      Trajectory1,
      s_Swerve::getPose,
      Constants.Swerve.swerveKinematics,
      new PIDController(Constants.AutoConstants.kPXController, 0, 0),
      new PIDController(Constants.AutoConstants.kPYController, 0, 0),
      thetaController,
      s_Swerve::setModuleStates,
      s_Swerve);
      SwerveControllerCommand swerveControllerCommand2 =
      new SwerveControllerCommand(
          Trajectory2,
          s_Swerve::getPose,
          Constants.Swerve.swerveKinematics,
          new PIDController(Constants.AutoConstants.kPXController, 0, 0),
          new PIDController(Constants.AutoConstants.kPYController, 0, 0),
          thetaController,
          s_Swerve::setModuleStates,
          s_Swerve);
          SwerveControllerCommand swerveControllerCommand3 =
          new SwerveControllerCommand(
              Trajectory3,
              s_Swerve::getPose,
              Constants.Swerve.swerveKinematics,
              new PIDController(Constants.AutoConstants.kPXController, 0, 0),
              new PIDController(Constants.AutoConstants.kPYController, 0, 0),
              thetaController,
              s_Swerve::setModuleStates,
              s_Swerve);

addCommands(new CloseGripper(m_Sub_Gripper),new align_to_target(s_Swerve, 2, 0, 0), new Move_Elbow(m_sub_Elbow, 15000).andThen(new Move_Elbow(m_sub_Elbow, 144115).alongWith(new Move_Shoulder(m_sub_Shoulder, 48863))),new WaitCommand(1),
new InstantCommand(() -> s_Swerve.resetOdometry(Trajectory1.getInitialPose())),swerveControllerCommand,new WaitCommand(.25), new OpenGripper(m_Sub_Gripper), new WaitCommand(.5), 
new ParallelCommandGroup(swerveControllerCommand2, (new WaitCommand(1).andThen(new Move_Elbow(m_sub_Elbow, 60000).alongWith(new Move_Shoulder(m_sub_Shoulder, 4700)).andThen(new Move_Elbow(m_sub_Elbow, 10000))))), new  InstantCommand(() -> s_Swerve.zeroGyro()) ,
swerveControllerCommand3, new auto_balence(s_Swerve, 0, 0));}
}