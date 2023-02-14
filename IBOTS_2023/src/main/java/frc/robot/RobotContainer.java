package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autos.exampleAuto;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.Elbow.Move_Elbow;
import frc.robot.commands.Gripper.CloseGripper;
import frc.robot.commands.Gripper.OpenGripper;
import frc.robot.commands.Shoulder.Move_Shoulder;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Gripper;
import frc.robot.subsystems.sub_Shoulder;

public class RobotContainer {
    /* Controllers */
    public final Joystick driver = new Joystick(0);
    public final Joystick operator = new Joystick(1);
    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;


    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    
    public final JoystickButton A = new JoystickButton(driver, 1);
    public final JoystickButton B = new JoystickButton(driver, 2);
    public final JoystickButton X = new JoystickButton(driver, 3);
    public final JoystickButton Y = new JoystickButton(driver, 4);
    public final JoystickButton LB = new JoystickButton(driver, 5);
    public final JoystickButton RB = new JoystickButton(driver, 6);  
    public final JoystickButton Select = new JoystickButton(driver, 7);
    public final JoystickButton Start = new JoystickButton(driver, 8);
    public final JoystickButton LStickButton = new JoystickButton(driver, 9);
    public final JoystickButton RStickButton = new JoystickButton(driver, 10);

    public final JoystickButton JS1 = new JoystickButton(operator, 1);
    public final JoystickButton JS2 = new JoystickButton(operator, 2);
    public final JoystickButton JS3 = new JoystickButton(operator, 3);
    public final JoystickButton JS4 = new JoystickButton(operator, 4);
    public final JoystickButton JS5 = new JoystickButton(operator, 5);
    public final JoystickButton JS6 = new JoystickButton(operator, 6);
    public final JoystickButton JS7 = new JoystickButton(operator, 7);
    public final JoystickButton JS8 = new JoystickButton(operator, 8);
    public final JoystickButton JS9 = new JoystickButton(operator, 9);
    public final JoystickButton JS10 = new JoystickButton(operator, 10);
    public final JoystickButton JS11 = new JoystickButton(operator, 11);
    public final JoystickButton JS12 = new JoystickButton(operator,12);

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
                () -> robotCentric.getAsBoolean()
            )
        );

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
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


        JS2.onTrue(new OpenGripper(m_Sub_Gripper));
        JS1.onTrue(new CloseGripper(m_Sub_Gripper));

        // shelf pose
        JS5.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 73000).andThen(new Move_Shoulder(m_sub_Shoulder, 5000)));

        //stowed pose
        JS3.toggleOnTrue(new Move_Shoulder(m_sub_Shoulder, 4700).andThen(new Move_Elbow(m_sub_Elbow, 10000)));

        // high-goal pose
        //X.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 137504).andThen(new Move_Shoulder(m_sub_Shoulder, 40000)));
        JS6.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 137504).alongWith(new Move_Shoulder(m_sub_Shoulder, 40000))));

        // mid-goal pose
        //A.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 129200).andThen(new Move_Shoulder(m_sub_Shoulder, 45000)));
        JS4.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 60000).andThen(new Move_Elbow(m_sub_Elbow, 129200).alongWith(new Move_Shoulder(m_sub_Shoulder, 45000))));

        //Ground Pickup (Joystick)
        JS11.toggleOnTrue(new Move_Elbow(m_sub_Elbow, 18038).andThen(new Move_Shoulder(m_sub_Shoulder, 40000)));
    }

    public Command getAutonomousCommand() {
        return new exampleAuto(s_Swerve);
    }
}
