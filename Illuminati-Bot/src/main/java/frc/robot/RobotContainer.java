// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Autos;
import frc.robot.commands.Blue_LEDs;
import frc.robot.commands.Driving;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Game_Piece_Detector;
import frc.robot.commands.Orange_LEDs;
import frc.robot.commands.Purple_LEDs;
import frc.robot.commands.Yellow_LEDs;
import frc.robot.commands.endgame_LEDs;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Sensors;
import frc.robot.subsystems.sub_LEDs;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final sub_LEDs m_sub_LEDs = new sub_LEDs();
  private final Sensors m_Sensors = new Sensors();
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.kDriverControllerPort);

      public final Drivetrain m_Drivetrain = new Drivetrain();

public static GenericHID Controler = new GenericHID(0);
public static JoystickButton A_Controller = new JoystickButton(Controler, 1);
public static JoystickButton B_Controller = new JoystickButton(Controler, 2);
public static JoystickButton X_Controller = new JoystickButton(Controler, 3);
public static JoystickButton Y_Controller = new JoystickButton(Controler, 4);
public static JoystickButton LB_Controller = new JoystickButton(Controler, 5);
public static JoystickButton RB_Controller = new JoystickButton(Controler, 6);
public static JoystickButton Select_Controller = new JoystickButton(Controler, 7);
public static JoystickButton Start_Controller = new JoystickButton(Controler, 8);

public static GenericHID Operator = new GenericHID(1);
public static JoystickButton A_Operator = new JoystickButton(Operator, 1);
public static JoystickButton B_Operator = new JoystickButton(Operator, 2);
public static JoystickButton X_Operator = new JoystickButton(Operator, 3);
public static JoystickButton Y_Operator = new JoystickButton(Operator, 4);
public static JoystickButton LB_Operator = new JoystickButton(Operator, 5);
public static JoystickButton RB_Operator = new JoystickButton(Operator, 6);
public static JoystickButton Select_Operator = new JoystickButton(Operator, 7);
public static JoystickButton Start_Operator = new JoystickButton(Operator, 8);

public static double Deadband(GenericHID Controler, double Deadband, int Axis){
  if (Controler.getRawAxis(Axis) < Deadband && Controler.getRawAxis(Axis) > -Deadband){
    return 0;
} else {
  return Controler.getRawAxis(Axis);
}
}

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
    new Trigger(m_exampleSubsystem::exampleCondition).onTrue(new ExampleCommand(m_exampleSubsystem));
   
  

    m_sub_LEDs.setDefaultCommand(new Game_Piece_Detector(m_Sensors, m_sub_LEDs));
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
   //m_sub_LEDs.setDefaultCommand(new com_SelectLEDColor(m_sub_LEDs));
   //m_sub_LEDs.setDefaultCommand(new LEDs_Off(m_sub_LEDs));
    RB_Controller.toggleOnTrue(new Purple_LEDs(m_sub_LEDs));
    LB_Controller.toggleOnTrue(new Yellow_LEDs(m_sub_LEDs));
    B_Controller.toggleOnTrue(new endgame_LEDs(m_sub_LEDs));
    Y_Controller.toggleOnTrue(new Orange_LEDs(m_sub_LEDs));
    X_Controller.toggleOnTrue(new Blue_LEDs(m_sub_LEDs));

    m_Drivetrain.setDefaultCommand(new Driving(m_Drivetrain));

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
