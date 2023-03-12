// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.UltraStow;
import frc.robot.subsystems.Cube_Intake;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Shoulder;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LowerIntake extends SequentialCommandGroup {
  /** Creates a new LowerIntake. */
  public LowerIntake(sub_Elbow mElbow, sub_Shoulder  msShoulder, Cube_Intake mCube_Intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new UltraStow(mElbow, msShoulder).andThen(new IntakeToggle(mCube_Intake)));
  }
}
