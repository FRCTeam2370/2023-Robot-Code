package frc.robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autos.drive_back;
import frc.robot.autos.exampleAuto;
import frc.robot.commands.TeleopSwerve;
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
    
    public final GenericHID driver = new Joystick(0);
    public final GenericHID operator = new Joystick(1);
    public final GenericHID JJ = new Joystick(2);
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
   

    public final JoystickButton operator_A = new JoystickButton(operator, 1);
    public final JoystickButton operator_B = new JoystickButton(operator, 2);
    public final JoystickButton operator_X = new JoystickButton(operator, 3);
    public final JoystickButton operator_Y = new JoystickButton(operator, 4);
    public final JoystickButton operator_LB = new JoystickButton(operator, 5);
    public final JoystickButton operator_RB = new JoystickButton(operator, 6);
    public final JoystickButton operator_Select = new JoystickButton(operator, 7);
    public final JoystickButton operator_Start = new JoystickButton(operator, 8);
   
    public final JoystickButton JJ_A = new JoystickButton(JJ, 1);
    public final JoystickButton JJ_B = new JoystickButton(JJ, 2);
    public final JoystickButton JJ_X = new JoystickButton(operator, 3);
    public final JoystickButton JJ_Y = new JoystickButton(operator, 4);
    public final JoystickButton JJ_LB = new JoystickButton(operator, 5);
    public final JoystickButton JJ_RB = new JoystickButton(operator, 6);
    public final JoystickButton JJ_Select = new JoystickButton(operator, 7);
    public final JoystickButton JJ_Start = new JoystickButton(operator, 8);
   


    public static sub_Elbow m_sub_Elbow = new sub_Elbow();
    public final sub_Shoulder m_sub_Shoulder = new sub_Shoulder();
    public final sub_Gripper m_Sub_Gripper = new sub_Gripper();
    private final Swerve s_Swerve = new Swerve();

    

    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> driver_Start.getAsBoolean()
                
            )
        );

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        driver_Select.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));


        // resets the gyro
        JJ_Select.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));


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
        driver_X.toggleOnTrue(new gripper_toggle(m_Sub_Gripper));

        // shelf pose
        trigger(driver, 3).toggleOnTrue(new Move_Elbow(m_sub_Elbow, 73000).andThen(new Move_Shoulder(m_sub_Shoulder, 5000)));

        //stowed pose
        driver_A.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 10000) .andThen(new WaitCommand(.5).andThen(new Move_Shoulder(m_sub_Shoulder, 4700)) ));

        // high-goal pose
        //X.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 137504).andThen(new Move_Shoulder(m_sub_Shoulder, 40000)));
        driver_LB.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 137504).alongWith(new Move_Shoulder(m_sub_Shoulder, 40000))));

        // mid-goal pose
        //A.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 129200).andThen(new Move_Shoulder(m_sub_Shoulder, 45000)));
        trigger(driver, 2).toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 129200).alongWith(new Move_Shoulder(m_sub_Shoulder, 45000))));

        //Ground Pickup (Joystick)
        driver_RB.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 18038).andThen(new Move_Shoulder(m_sub_Shoulder, 40000)));
   

       // closes and opens the gripper
       JJ_LB.toggleOnTrue(new gripper_toggle(m_Sub_Gripper));

       //Loading pose
       JJ_RB.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 73000).andThen(new Move_Shoulder(m_sub_Shoulder, 5000)));

       //high goal pose
       JJ_Y.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 137504).alongWith(new Move_Shoulder(m_sub_Shoulder, 40000))));

       //mid goal pose
        JJ_X.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 129200).alongWith(new Move_Shoulder(m_sub_Shoulder, 45000))));

        //stow pose
        JJ_B.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 10000) .andThen(new WaitCommand(.5).andThen(new Move_Shoulder(m_sub_Shoulder, 4700)) ));

        //floor pickup
        JJ_A.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 18038).andThen(new Move_Shoulder(m_sub_Shoulder, 40000)));
   
    }

    public Command getAutonomousCommand() {
        return new drive_back(s_Swerve);
    }
}
