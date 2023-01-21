// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.autobalancepid;
import frc.robot.commands.automove;
import frc.robot.commands.balance;
import frc.robot.commands.driving;
import frc.robot.commands.fullReset;
import frc.robot.commands.pidautobalance;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain m_Drivetrain = new Drivetrain();


  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
public static GenericHID controller = new GenericHID(0);
public static double deadband(int axis, double deadband, GenericHID controler){
 double truezone = 1/(1-deadband);
 if(Math.abs(controler.getRawAxis(axis))< deadband){
   return 0;
 } 
 else{
   if(controller.getRawAxis(axis) < 0){
    return truezone*(controler.getRawAxis(axis)+deadband);
   } 
   else{
   return truezone*(controler.getRawAxis(axis)-deadband);}
 }
}
public static JoystickButton select = new JoystickButton(controller, 7);
public static JoystickButton A = new JoystickButton(controller, 1);
public static JoystickButton B = new JoystickButton(controller, 2);
public static JoystickButton X = new JoystickButton(controller, 3);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    m_Drivetrain.setDefaultCommand(new driving(m_Drivetrain));
    select.whileTrue(new fullReset(m_Drivetrain));
    A.whileTrue(new balance(m_Drivetrain));
    B.whileTrue(new pidautobalance(m_Drivetrain));
    X.whileTrue(new automove(m_Drivetrain));
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
