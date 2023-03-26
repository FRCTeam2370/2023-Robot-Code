// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.loading;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Gripper;
import frc.robot.subsystems.sub_LED;
import frc.robot.subsystems.sub_Shoulder;
import frc.robot.subsystems.sub_sensor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Loading_With_Help extends SequentialCommandGroup {
  /** Creates a new Loading_With_Help. */
  public Loading_With_Help(sub_Elbow mElbow, sub_Shoulder msShoulder, sub_sensor msSensor, sub_LED mLed, sub_Gripper mGripper) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new loading(mElbow, msShoulder).alongWith(new LED_load(msSensor, mLed, mGripper)));
  }
}
