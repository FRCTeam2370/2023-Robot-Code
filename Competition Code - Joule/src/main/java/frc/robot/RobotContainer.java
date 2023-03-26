package frc.robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autos.Score_one_and_balance_middle;
import frc.robot.autos.exampleAuto;
import frc.robot.autos.left_score_one_and_balance;
import frc.robot.autos.right_score_one_and_balance;
import frc.robot.autos.score_one;
import frc.robot.autos.score_one_and_balance;
import frc.robot.autos.score_one_and_do_nothing;
import frc.robot.autos.score_two;
import frc.robot.commands.Ground;
import frc.robot.commands.LED_ON;
import frc.robot.commands.Pickup;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.Top_goal;
import frc.robot.commands.UltraStow;
import frc.robot.commands.loading;
import frc.robot.commands.mid_goal;
import frc.robot.commands.stow;
import frc.robot.commands.Drivetrain.Loading_With_Help;
import frc.robot.commands.Drivetrain.align_to_target;
import frc.robot.commands.Drivetrain.align_with_driver_input;
import frc.robot.commands.Drivetrain.auto_balence;
import frc.robot.commands.Drivetrain.limelight_on;
import frc.robot.commands.Gripper.CloseGripper;
import frc.robot.commands.Gripper.OpenGripper;
import frc.robot.commands.Gripper.gripper_toggle;
import frc.robot.commands.Intake.ForwardIntake;
import frc.robot.commands.Intake.IntakeToggle;
import frc.robot.commands.Intake.ReverseIntake;
import frc.robot.subsystems.Cube_Intake;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Gripper;
import frc.robot.subsystems.sub_LED;
import frc.robot.subsystems.sub_Shoulder;
import frc.robot.subsystems.sub_sensor;

public class RobotContainer {
    /* Controllers */
    public final static GenericHID driver = new GenericHID(0);
    public final static GenericHID operator = new GenericHID(1);
    public final static GenericHID rdriver = new GenericHID(3);
    public final static GenericHID operatorBoard = new GenericHID(4);
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
    public final static JoystickButton driver_M1 = new JoystickButton(driver, 9);
    public final static JoystickButton driver_M2 = new JoystickButton(driver, 10);

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

    public final static JoystickButton GND = new JoystickButton(operatorBoard, 1);
    public final static JoystickButton Stow = new JoystickButton(operatorBoard, 2);
    public final static JoystickButton Pickup = new JoystickButton(operatorBoard, 3);
    public final static JoystickButton Mid = new JoystickButton(operatorBoard, 4);
    public final static JoystickButton Load = new JoystickButton(operatorBoard, 5);
    public final static JoystickButton High = new JoystickButton(operatorBoard, 6);
    public final static JoystickButton Open = new JoystickButton(operatorBoard, 7);
    public final static JoystickButton Close = new JoystickButton(operatorBoard, 8);
    public final static JoystickButton AlignLoad = new JoystickButton(operatorBoard, 9);
    public final static JoystickButton AlignScore = new JoystickButton(operatorBoard, 10);
    public final static JoystickButton Cone = new JoystickButton(operatorBoard, 11);
    public final static JoystickButton Cube = new JoystickButton(operatorBoard, 12);

    public final  sub_Elbow m_sub_Elbow = new sub_Elbow();
    public final sub_Shoulder m_sub_Shoulder = new sub_Shoulder();
    public final sub_Gripper m_Sub_Gripper = new sub_Gripper();
    private final Swerve s_Swerve = new Swerve();
    private final sub_sensor m_Sub_sensor = new sub_sensor();
    private final Cube_Intake m_Cube_Intake = new Cube_Intake();
    private final sub_LED m_Sub_Led = new sub_LED();
    SendableChooser<Command> chooser = new SendableChooser<>();
    private final score_one m_score_one =  new score_one(s_Swerve, m_sub_Elbow,m_sub_Shoulder, m_Sub_Gripper, m_Cube_Intake);
    private final right_score_one_and_balance mScore_one_and_balance = new right_score_one_and_balance(s_Swerve, m_sub_Elbow, m_sub_Shoulder, m_Sub_Gripper, m_Cube_Intake);
    private final left_score_one_and_balance mLeft_score_one_and_balance = new left_score_one_and_balance(s_Swerve, m_sub_Elbow, m_sub_Shoulder, m_Sub_Gripper, m_Cube_Intake);
    private final exampleAuto mExampleAuto = new exampleAuto(s_Swerve);
    private final Score_one_and_balance_middle m_Balance_middle = new Score_one_and_balance_middle(s_Swerve, m_sub_Elbow, m_sub_Shoulder, m_Sub_Gripper, m_Cube_Intake);
    private final score_one_and_do_nothing mAnd_do_nothing = new score_one_and_do_nothing(s_Swerve, m_sub_Elbow, m_sub_Shoulder, m_Sub_Gripper, m_Cube_Intake);
    private final score_two mScore_two = new score_two(s_Swerve, m_sub_Elbow, m_sub_Shoulder, m_Sub_Gripper);
    public void addAutoOptions(){
        chooser.addOption("score one",m_score_one);
        chooser.addOption("right score and balance", mScore_one_and_balance);
        chooser.addOption("left score one and balance", mLeft_score_one_and_balance);
        chooser.addOption("do nothing", mExampleAuto);
        chooser.addOption("score one and do nothing", mAnd_do_nothing);
        chooser.addOption("score one and balance middle =", m_Balance_middle);
        chooser.addOption("score two", mScore_two);
        SmartDashboard.putData(chooser);}
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
        m_Sub_Led.setDefaultCommand(new LED_ON(m_Sub_Led));
        configureButtonBindings();
    } 

    private void configureButtonBindings() {
        Cube.onTrue(new InstantCommand(() -> s_Swerve.reverseGyro()));
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

  //Top goal pose
  Rdriver_LB.toggleOnTrue(new Top_goal(m_sub_Elbow, m_sub_Shoulder));


  //mid goal pose
   trigger(rdriver, 2).toggleOnTrue(new mid_goal(m_sub_Elbow, m_sub_Shoulder));
   //stow pose
   Rdriver_X.toggleOnTrue(new stow(m_sub_Shoulder, m_sub_Elbow, m_Sub_Gripper));

   //floor pickup
   Rdriver_RB.toggleOnTrue(new Ground(m_sub_Elbow, m_sub_Shoulder));


  driver_LB.toggleOnTrue(new gripper_toggle(m_Sub_Gripper));


  //Loading pose
  //driver_B.toggleOnTrue(new load_mode_with_arm(s_Swerve, m_Sub_Led, m_sub_Elbow, m_sub_Shoulder, m_Sub_Gripper));
  driver_B.toggleOnTrue(new Loading_With_Help(m_sub_Elbow, m_sub_Shoulder, m_Sub_sensor, m_Sub_Led, m_Sub_Gripper));

  //Run intake forward
  //trigger(driver, 3).whileTrue(new ForwardIntake(m_Cube_Intake));

  //Run intake backward
 // trigger(driver, 2).whileTrue(new ReverseIntake(m_Cube_Intake));

  //Top goal pose
  driver_M1.toggleOnTrue(new Top_goal(m_sub_Elbow, m_sub_Shoulder));

  //mid goal pose
   driver_M2.toggleOnTrue(new mid_goal(m_sub_Elbow, m_sub_Shoulder));

   //stow pose
   driver_RB.toggleOnTrue(new stow(m_sub_Shoulder, m_sub_Elbow, m_Sub_Gripper));

   //floor pickup
   driver_A.toggleOnTrue(new Ground(m_sub_Elbow, m_sub_Shoulder));

   //pickup pose
   driver_X.toggleOnTrue(new Pickup(m_sub_Shoulder, m_sub_Elbow, m_Sub_Gripper));

   //ultra stow pose
   driver_Start.toggleOnTrue(new UltraStow(m_sub_Elbow, m_sub_Shoulder));

   //Intake toggle
  driver_Y.toggleOnTrue(new IntakeToggle(m_Cube_Intake));


        //Ground 
        GND.toggleOnTrue(new Ground(m_sub_Elbow, m_sub_Shoulder));

        //Stow
        Stow.toggleOnTrue(new stow(m_sub_Shoulder, m_sub_Elbow, m_Sub_Gripper));

        //Pickup 
        Pickup.toggleOnTrue(new Pickup(m_sub_Shoulder, m_sub_Elbow, m_Sub_Gripper));

        //Mid Goal
        Mid.toggleOnTrue(new mid_goal(m_sub_Elbow, m_sub_Shoulder));

        //Load
       //Load.toggleOnTrue(new load_mode_with_arm(s_Swerve, m_Sub_Led, m_sub_Elbow, m_sub_Shoulder, m_Sub_Gripper));
        Load.toggleOnTrue(new Loading_With_Help(m_sub_Elbow, m_sub_Shoulder, m_Sub_sensor, m_Sub_Led, m_Sub_Gripper));

        High.toggleOnTrue(new Top_goal(m_sub_Elbow, m_sub_Shoulder));

        //Open gripper
        Open.toggleOnTrue(new OpenGripper(m_Sub_Gripper));

        //Close gripper
        Close.toggleOnTrue(new CloseGripper(m_Sub_Gripper));

        //toggle Intake
        Cone.toggleOnTrue(new IntakeToggle(m_Cube_Intake));

        //Ultra stow
        AlignLoad.toggleOnTrue(new UltraStow(m_sub_Elbow, m_sub_Shoulder));

     

        operator_B.whileTrue(new align_to_target(s_Swerve, 2, 2, 180));

        operator_A.toggleOnTrue(new align_with_driver_input(s_Swerve, 2,180));
        
        operator_Y.whileTrue(new auto_balence(s_Swerve, 0, 0));

        operator_X.toggleOnTrue(new IntakeToggle(m_Cube_Intake));

        operator_LB.whileTrue(new ReverseIntake(m_Cube_Intake));

        operator_RB.whileTrue(new ForwardIntake(m_Cube_Intake));
    }

    public Command getAutonomousCommand() {
        return chooser.getSelected();
    }
}
