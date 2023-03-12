package frc.robot.autos;

import frc.robot.Constants;
import frc.robot.commands.high_goal;
import frc.robot.commands.stow;
import frc.robot.commands.Drivetrain.auto_balence;
import frc.robot.commands.Gripper.CloseGripper;
import frc.robot.commands.Gripper.OpenGripper;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Gripper;
import frc.robot.subsystems.sub_Shoulder;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class scoreOneBalance extends SequentialCommandGroup {
    public scoreOneBalance(Swerve s_Swerve, sub_Elbow mElbow, sub_Shoulder mShoulder, sub_Gripper mGripper){
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics);

        // An example trajectory to follow.  All units in meters.
        Trajectory exampleTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(3, 0, new Rotation2d(0)),
                config);

        Trajectory Trajectory1 = 
        TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(new Translation2d(1, 0)),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(.9, 0, new Rotation2d(0)),
            config);

            Trajectory Trajectory2 = 
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(-0.9, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(-0.9, 0)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(0, 0, new Rotation2d(-0.785398 * Math.PI)),
                config);

                Trajectory Trajectory3 = 
                TrajectoryGenerator.generateTrajectory(
                    // Start at the origin facing the +X direction
                    new Pose2d(-1.4, 0, new Rotation2d(0)),
                    // Pass through these two interior waypoints, making an 's' curve path
                    List.of(new Translation2d(-4, 0)),
                    // End 3 meters straight ahead of where we started, facing forward
                    new Pose2d(-8.6, 0, new Rotation2d(0)),
                    config); 

                    Trajectory Trajectory4 = 
                    TrajectoryGenerator.generateTrajectory(
                        // Start at the origin facing the +X direction
                        new Pose2d(0, 0, new Rotation2d(0)),
                        // Pass through these two interior waypoints, making an 's' curve path
                        List.of(new Translation2d(2, 0)),
                        // End 3 meters straight ahead of where we started, facing forward
                        new Pose2d(3, 0, new Rotation2d(0.785398 * Math.PI)),
                        config); 

                        Trajectory Trajectory5 = 
                        TrajectoryGenerator.generateTrajectory(
                            // Start at the origin facing the +X direction
                            new Pose2d(0, 0, new Rotation2d(0)),
                            // Pass through these two interior waypoints, making an 's' curve path
                            List.of(new Translation2d(0.01, 0)),
                            // End 3 meters straight ahead of where we started, facing forward
                            new Pose2d(0, 0, new Rotation2d(45)),
                            config); 

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

                
        SwerveControllerCommand swerveControllerCommand1 =
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

                    SwerveControllerCommand swerveControllerCommand4 =
                    new SwerveControllerCommand(
                        Trajectory4,
                        s_Swerve::getPose,
                        Constants.Swerve.swerveKinematics,
                        new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                        new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                        thetaController,
                        s_Swerve::setModuleStates,
                        s_Swerve);        
                        
                        SwerveControllerCommand swerveControllerCommand5 =
                        new SwerveControllerCommand(
                            Trajectory5,
                            s_Swerve::getPose,
                            Constants.Swerve.swerveKinematics,
                            new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                            new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                            thetaController,
                            s_Swerve::setModuleStates,
                            s_Swerve);        

        addCommands(
            new InstantCommand(() -> s_Swerve.resetOdometry(Trajectory1.getInitialPose())),new CloseGripper(mGripper), new high_goal(mElbow, mShoulder), 
            swerveControllerCommand1, new OpenGripper(mGripper), swerveControllerCommand2, new WaitCommand(1),new stow(mShoulder, mElbow), swerveControllerCommand3, new WaitCommand(.5),
            swerveControllerCommand4,new WaitCommand(2), new auto_balence(s_Swerve, 0, 0)
        );
    }
}