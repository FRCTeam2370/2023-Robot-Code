package frc.robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.lib.util.COTSFalconSwerveConstants.driveGearRatios;
import frc.robot.autos.exampleAuto;
import frc.robot.autos.score_one;
import frc.robot.autos.score_one_and_balance;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.high_goal;
import frc.robot.commands.loading;
import frc.robot.commands.low_pick_up;
import frc.robot.commands.mid_goal;
import frc.robot.commands.stow;
import frc.robot.commands.Drivetrain.align_to_target;
import frc.robot.commands.Drivetrain.align_with_driver_input;
import frc.robot.commands.Drivetrain.auto_balence;
import frc.robot.commands.Drivetrain.limelight_on;
import frc.robot.commands.Elbow.Move_Elbow;
import frc.robot.commands.Gripper.CloseGripper;
import frc.robot.commands.Gripper.OpenGripper;
import frc.robot.commands.Gripper.gripper_toggle;
import frc.robot.commands.Shoulder.Move_Shoulder;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Gripper;
import frc.robot.subsystems.sub_Shoulder;
import frc.robot.subsystems.sub_sensor;

public class RobotContainer {
    /* Controllers */
    public final static GenericHID driver = new GenericHID(0);
    public final static GenericHID operator = new GenericHID(1);
    public final static GenericHID rdriver = new GenericHID(3);
    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    
    public static double Deadband(double value, double Deadband){
       double Deadbandfix = 1-Deadband;
       if(value< - Deadband){
        return (value+Deadband)*Deadbandfix;
       }
       else if(value > Deadband){
       return (value-Deadband)*Deadbandfix;
       }
       else{return 0;}
    }

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    
    public final static JoystickButton driver_A = new JoystickButton(driver, 1);
    public final static JoystickButton driver_B = new JoystickButton(driver, 2);
    public final static JoystickButton driver_X = new JoystickButton(driver, 3);
    public final static JoystickButton driver_Y = new JoystickButton(driver, 4);
    public final static JoystickButton driver_LB = new JoystickButton(driver, 5);
    public final static JoystickButton driver_RB = new JoystickButton(driver, 6);  
    public final static JoystickButton driver_Select = new JoystickButton(driver, 7);
    public final static JoystickButton driver_Start = new JoystickButton(driver, 8);
    public final static JoystickButton LStickButton = new JoystickButton(driver, 9);
    public final static JoystickButton RStickButton = new JoystickButton(driver, 10);

    public final static JoystickButton Rdriver_A = new JoystickButton(rdriver, 1);
    public final static JoystickButton Rdriver_B = new JoystickButton(rdriver, 2);
    public final static JoystickButton Rdriver_X = new JoystickButton(rdriver, 3);
    public final static JoystickButton Rdriver_Y = new JoystickButton(rdriver, 4);
    public final static JoystickButton Rdriver_LB = new JoystickButton(rdriver, 5);
    public final static JoystickButton Rdriver_RB = new JoystickButton(rdriver, 6);  
    public final static JoystickButton Rdriver_Select = new JoystickButton(rdriver, 7);
    public final static JoystickButton Rdriver_Start = new JoystickButton(rdriver, 8);

    public final static JoystickButton operator_A = new JoystickButton(operator, 1);
    public final static JoystickButton operator_B = new JoystickButton(operator, 2);
    public final static JoystickButton operator_X = new JoystickButton(operator, 3);
    public final static JoystickButton operator_Y = new JoystickButton(operator, 4);
    public final static JoystickButton operator_LB = new JoystickButton(operator, 5);
    public final static JoystickButton operator_RB = new JoystickButton(operator, 6);
    public final static JoystickButton operator_Select = new JoystickButton(operator, 7);
    public final static JoystickButton operator_Start = new JoystickButton(operator, 8);


    public final  sub_Elbow m_sub_Elbow = new sub_Elbow();
    public final sub_Shoulder m_sub_Shoulder = new sub_Shoulder();
    public final sub_Gripper m_Sub_Gripper = new sub_Gripper();
    private final Swerve s_Swerve = new Swerve();
    private final sub_sensor m_Sub_sensor = new sub_sensor();
    public static Trigger trigger (GenericHID controller, int axis) {
        return new Trigger(() -> controller.getRawAxis(axis) >= 0.9);
      }

    public static double convertaxis(GenericHID controller ,GenericHID controller2,int axis){
        return controller.getRawAxis(axis)+controller2.getRawAxis(axis);
    }

    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -convertaxis(driver, rdriver, translationAxis),
                () -> -convertaxis(driver, rdriver, strafeAxis), 
                () -> -convertaxis(rdriver, driver, rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );
        m_Sub_sensor.setDefaultCommand(new limelight_on(m_Sub_sensor));

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        driver_Select.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        Rdriver_Select.onTrue(new InstantCommand(()->s_Swerve.zeroGyro()));
        // Add Button Bindings here
        //A.onTrue(new Move_Elbow(m_sub_Elbow, Constants.ElbowConstants.ArmDownPosition));
       // B.onTrue(new Move_Elbow(m_sub_Elbow, Constants.ElbowConstants.ArmUpPosition));
  /*
*/

/*
 * 1 close
 * 2 Open
 * 
 * 5 Shelf     6 High
 * 3 Stowed    4 Mid
 * 
 * 11 Ground
 * 
 * 
 * 
 */

 //from here dowm is the current version!!!!
  //from here dowm is the current version!!!!
  Rdriver_Y.toggleOnTrue(new gripper_toggle(m_Sub_Gripper));
  //Loading pose
  trigger(rdriver, 3).onTrue(new loading(m_sub_Elbow, m_sub_Shoulder));

  //high goal pose
  Rdriver_LB.toggleOnTrue(new high_goal(m_sub_Elbow, m_sub_Shoulder));


  //mid goal pose
   trigger(rdriver, 2).toggleOnTrue(new mid_goal(m_sub_Elbow, m_sub_Shoulder));
   //stow pose
   Rdriver_X.toggleOnTrue(new stow(m_sub_Shoulder, m_sub_Elbow));

   //floor pickup
   Rdriver_RB.toggleOnTrue(new low_pick_up(m_sub_Elbow, m_sub_Shoulder));


  driver_LB.toggleOnTrue(new gripper_toggle(m_Sub_Gripper));


  //Loading pose
  trigger(driver, 3).toggleOnTrue(new loading(m_sub_Elbow, m_sub_Shoulder));

  //high goal pose
  driver_Y.toggleOnTrue(new high_goal(m_sub_Elbow, m_sub_Shoulder));

  //mid goal pose
   driver_X.toggleOnTrue(new mid_goal(m_sub_Elbow, m_sub_Shoulder));

   //stow pose
   driver_RB.toggleOnTrue(new stow(m_sub_Shoulder, m_sub_Elbow));

   //floor pickup
   driver_A.toggleOnTrue(new low_pick_up(m_sub_Elbow, m_sub_Shoulder));



        operator_LB.whileTrue(new align_to_target(s_Swerve, 5, 2, 180));

        operator_B.whileTrue(new align_to_target(s_Swerve, 2, 2, 180));

        operator_A.toggleOnTrue(new align_with_driver_input(s_Swerve, 2,180));
        
        operator_Y.whileTrue(new auto_balence(s_Swerve, 0, 0));
    }

    public Command getAutonomousCommand() {
        return new score_one_and_balance(s_Swerve, m_sub_Elbow,m_sub_Shoulder, m_Sub_Gripper);
    }
}
