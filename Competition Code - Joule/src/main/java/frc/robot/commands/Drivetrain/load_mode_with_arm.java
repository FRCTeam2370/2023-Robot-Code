// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.Constants.Swerve;
import frc.robot.commands.loading;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_LED;
import frc.robot.subsystems.sub_Shoulder;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class load_mode_with_arm extends SequentialCommandGroup {
  /** Creates a new load_mode_with_arm. */
  public load_mode_with_arm(frc.robot.subsystems.Swerve swerve, sub_Shoulder shoulder, sub_Elbow elbow, sub_LED led) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new loading(elbow,shoulder), new load_mode(swerve,()-> -RobotContainer.driver.getRawAxis(0),()->-RobotContainer.driver.getRawAxis(1), led));
    
  }
}
