package frc.robot;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autos.drive_back;
import frc.robot.autos.exampleAuto;
import frc.robot.autos.score_one;

import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.high_goal;
import frc.robot.commands.loading;
import frc.robot.commands.low_pick_up;
import frc.robot.commands.mid_goal;
import frc.robot.commands.stow;
import frc.robot.commands.Elbow.Move_Elbow;
import frc.robot.commands.Gripper.CloseGripper;
import frc.robot.commands.Gripper.OpenGripper;
import frc.robot.commands.Gripper.gripper_toggle;
import frc.robot.commands.Shoulder.Move_Shoulder;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Gripper;
import frc.robot.subsystems.sub_Shoulder;

public class RobotContainer {
    /* Controllers */
    
    public final static GenericHID driver = new Joystick(0);
    public final GenericHID operator = new Joystick(1);
    public final static GenericHID Rdriver = new Joystick(3);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    public static Trigger trigger (GenericHID controller, int axis) {
        return new Trigger(() -> controller.getRawAxis(axis) >= 0.9);
      }

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    
    
    public final JoystickButton driver_A = new JoystickButton(driver, 1);
    public final JoystickButton driver_B = new JoystickButton(driver, 2);
    public final JoystickButton driver_X = new JoystickButton(driver, 3);
    public final JoystickButton driver_Y = new JoystickButton(driver, 4);
    public final JoystickButton driver_LB = new JoystickButton(driver, 5);
    public final JoystickButton driver_RB = new JoystickButton(driver, 6);  
    public final JoystickButton driver_Select = new JoystickButton(driver, 7);
    public final JoystickButton driver_Start = new JoystickButton(driver, 8);
   

    public final JoystickButton Rdriver_A = new JoystickButton(Rdriver, 1);
    public final JoystickButton Rdriver_B = new JoystickButton(Rdriver, 2);
    public final JoystickButton Rdriver_X = new JoystickButton(Rdriver, 3);
    public final JoystickButton Rdriver_Y = new JoystickButton(Rdriver, 4);
    public final JoystickButton Rdriver_LB = new JoystickButton(Rdriver, 5);
    public final JoystickButton Rdriver_RB = new JoystickButton(Rdriver, 6);  
    public final JoystickButton Rdriver_Select = new JoystickButton(Rdriver, 7);
    public final JoystickButton Rdriver_Start = new JoystickButton(Rdriver, 8);


    public final JoystickButton align_to_score_Button = new JoystickButton(operator, 2);
    public final JoystickButton stow_Button = new JoystickButton(operator, 3);
    public final JoystickButton Mid_goal_Button = new JoystickButton(operator, 4);
    public final JoystickButton load_Button= new JoystickButton(operator, 5);
    public final JoystickButton high_goal_Button = new JoystickButton(operator, 6);
    public final JoystickButton ask_for_cube_Button = new JoystickButton(operator, 7);
    public final JoystickButton ask_for_cone_Button = new JoystickButton(operator, 8);
    public final JoystickButton Align_to_load_button = new JoystickButton(operator, 9);
    public final JoystickButton Close_gripper_Button = new JoystickButton(operator, 10);
    public final JoystickButton Open_gripper_button = new JoystickButton(operator, 11);
    public final JoystickButton Gournd_pick_up_Button = new JoystickButton(operator, 12);
    
   


    public static sub_Elbow m_sub_Elbow = new sub_Elbow();
    public final sub_Shoulder m_sub_Shoulder = new sub_Shoulder();
    public final sub_Gripper m_Sub_Gripper = new sub_Gripper();
    private final Swerve s_Swerve = new Swerve();
    public static double convertaxis(GenericHID controller ,GenericHID controller2,int axis){
        return controller.getRawAxis(axis)+controller2.getRawAxis(axis);
    }

    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -convertaxis(driver, Rdriver, translationAxis),
                () -> -convertaxis(driver, Rdriver, strafeAxis), 
                () -> -convertaxis(Rdriver, driver, rotationAxis), 
                () -> driver_Start.getAsBoolean()
                
            )
        );

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        driver_Select.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        Rdriver_Select.onTrue(new InstantCommand(()->s_Swerve.zeroGyro()));


        // resets the gyro
        //JJ_Select.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));


        // Add Button Bindings here
        //A.onTrue(new Move_Elbow(m_sub_Elbow, Constants.ElbowConstants.ArmDownPosition));
       // B.onTrue(new Move_Elbow(m_sub_Elbow, Constants.ElbowConstants.ArmUpPosition));
  /*
       Start.onTrue(new OpenGripper(m_Sub_Gripper));
        Select.onTrue(new CloseGripper(m_Sub_Gripper));
        //LB.onTrue(new Move_Shoulder(m_sub_Shoulder, Constants.ShoulderConstants.ShoulderForwardPosition));
        //RB.onTrue(new Move_Shoulder(m_sub_Shoulder, Constants.ShoulderConstants.ShoulderBackPosition));
        
        // shelf pose
        RB.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 73000).andThen(new Move_Shoulder(m_sub_Shoulder, 5000)));

        //stowed pose
        LB.toggleOnTrue(new Move_Shoulder(m_sub_Shoulder, 4700).andThen(new Move_Elbow(m_sub_Elbow, 10000)));

        // high-goal pose
        //X.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 137504).andThen(new Move_Shoulder(m_sub_Shoulder, 40000)));
        X.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 137504).alongWith(new Move_Shoulder(m_sub_Shoulder, 40000))));

        // mid-goal pose
        //A.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 129200).andThen(new Move_Shoulder(m_sub_Shoulder, 45000)));
        A.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 129200).alongWith(new Move_Shoulder(m_sub_Shoulder, 45000))));

        //Ground Pickup (Joystick)
        JS12.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 18038).andThen(new Move_Shoulder(m_sub_Shoulder, 40000)));
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

        // closes and opens the gripper
        //driver_X.toggleOnTrue(new gripper_toggle(m_Sub_Gripper));

        // shelf pose
        //trigger(driver, 3).toggleOnTrue(new Move_Elbow(m_sub_Elbow, 73000).andThen(new Move_Shoulder(m_sub_Shoulder, 5000)));

        //stowed pose
        //driver_A.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 10000) .andThen(new WaitCommand(.5).andThen(new Move_Shoulder(m_sub_Shoulder, 4700)) ));

        // high-goal pose
        //X.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 137504).andThen(new Move_Shoulder(m_sub_Shoulder, 40000)));
       // driver_LB.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 137504).alongWith(new Move_Shoulder(m_sub_Shoulder, 40000))));

        // mid-goal pose
        //A.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 129200).andThen(new Move_Shoulder(m_sub_Shoulder, 45000)));
        //trigger(driver, 2).toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 129200).alongWith(new Move_Shoulder(m_sub_Shoulder, 45000))));

        //Ground Pickup (Joystick)
        //driver_RB.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 18038).andThen(new Move_Shoulder(m_sub_Shoulder, 40000)));

    
       // closes and opens the gripper
    
        Rdriver_Y.toggleOnTrue(new gripper_toggle(m_Sub_Gripper));
       //Loading pose
       trigger(Rdriver, 3).toggleOnTrue(new loading(m_sub_Elbow, m_sub_Shoulder));
       //high goal pose
       Rdriver_LB.toggleOnTrue(new high_goal(m_sub_Elbow, m_sub_Shoulder));
       //mid goal pose
        trigger(Rdriver, 2).toggleOnTrue(new mid_goal(m_sub_Elbow, m_sub_Shoulder));
        //stow pose
        Rdriver_X.toggleOnTrue(new stow(m_sub_Shoulder, m_sub_Elbow));
        //floor pickup
        Rdriver_RB.toggleOnTrue(new low_pick_up(m_sub_Elbow, m_sub_Shoulder));
      
        driver_LB.toggleOnTrue(new gripper_toggle(m_Sub_Gripper));

       //Loading pose
       driver_RB.toggleOnTrue(new loading(m_sub_Elbow, m_sub_Shoulder));
       //high goal pose
       driver_Y.toggleOnTrue(new high_goal(m_sub_Elbow, m_sub_Shoulder));
       //mid goal pose
        driver_X.toggleOnTrue(new mid_goal(m_sub_Elbow, m_sub_Shoulder));
        //stow pose
        driver_RB.toggleOnTrue(new stow(m_sub_Shoulder, m_sub_Elbow));

        //floor pickup
        driver_A.toggleOnTrue(new low_pick_up(m_sub_Elbow, m_sub_Shoulder));


        //floor pickup
        Gournd_pick_up_Button.onTrue(new low_pick_up(m_sub_Elbow, m_sub_Shoulder));
        //stow
        stow_Button.onTrue(new stow(m_sub_Shoulder, m_sub_Elbow));
        //high goal
        high_goal_Button.onTrue(new high_goal(m_sub_Elbow, m_sub_Shoulder));
        //mid goal
        Mid_goal_Button.onTrue(new mid_goal(m_sub_Elbow, m_sub_Shoulder));
        //load
        load_Button.onTrue(new loading(m_sub_Elbow, m_sub_Shoulder));
        //ask for cone
        
        // ask for cube

        // Align load 

        // Align score

        // open gripper
        Open_gripper_button.onTrue(new OpenGripper(m_Sub_Gripper));
        //close gripper
        Close_gripper_Button.onTrue(new CloseGripper(m_Sub_Gripper));
    }
    public Command getAutonomousCommand() {
        return new score_one(s_Swerve, m_sub_Elbow, m_sub_Shoulder, m_Sub_Gripper);
    }
}
