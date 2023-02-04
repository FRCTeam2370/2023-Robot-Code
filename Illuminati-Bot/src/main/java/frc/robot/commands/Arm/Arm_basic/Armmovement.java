// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm.Arm_basic;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Armmovement extends ParallelCommandGroup {
  /** Creates a new Armmovement. */
  public Armmovement(Arm m_Arm, double eblow_postion, double shoulder_postion) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(
      new eblow_movement(m_Arm, eblow_postion),
      new shoulder_movement(m_Arm, shoulder_postion)
    );
  }
}
