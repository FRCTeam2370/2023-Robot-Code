// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Driving;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.balnce;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;

import org.opencv.ml.StatModel;

import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
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
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
public static GenericHID driver = new GenericHID(0);
public static GenericHID operater = new GenericHID(1);

      public static double deadband(int axis, double deadband, GenericHID controler){
        double truezone = 1/(1-deadband);
        if(Math.abs(controler.getRawAxis(axis))< deadband){
          return 0;
        } 
        else{
          if(controler.getRawAxis(axis) < 0){
           return truezone*(controler.getRawAxis(axis)+deadband);
          } 
          else{
          return truezone*(controler.getRawAxis(axis)-deadband);}
        }
       }

  public static JoystickButton A_driver = new JoystickButton(driver, 1);
  public static JoystickButton B_driver = new JoystickButton(driver, 2);
  public static JoystickButton X_driver = new JoystickButton(driver, 3);
  public static JoystickButton Y_driver = new JoystickButton(driver, 4);
  public static JoystickButton leftbumper_driver = new JoystickButton(driver, 5);
  public static JoystickButton righbumper_driver = new JoystickButton(driver, 6);
  public static JoystickButton select_driver = new JoystickButton(driver, 7);
  public static JoystickButton start_driver = new JoystickButton(driver, 8);
  public static JoystickButton leftstickbutton_driver = new JoystickButton(driver, 9);
  public static JoystickButton rightstickbutton_driver = new JoystickButton(driver, 10);

  public static JoystickButton A_operater = new JoystickButton(operater, 1);
  public static JoystickButton B_operater = new JoystickButton(operater, 2);
  public static JoystickButton X_operater = new JoystickButton(operater, 3);
  public static JoystickButton Y_operater = new JoystickButton(operater, 4);
  public static JoystickButton leftbumper_operater = new JoystickButton(operater, 5);
  public static JoystickButton righbumper_operater = new JoystickButton(operater, 6);
  public static JoystickButton select_operater = new JoystickButton(operater, 7);
  public static JoystickButton start_operater = new JoystickButton(operater, 8);
  public static JoystickButton leftstickbutton_operater = new JoystickButton(operater, 9);
  public static JoystickButton rightstickbutton_operater = new JoystickButton(operater, 10);

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
        m_Drivetrain.setDefaultCommand(new Driving(m_Drivetrain));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    A_driver.whileTrue(new balnce(m_Drivetrain));
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
