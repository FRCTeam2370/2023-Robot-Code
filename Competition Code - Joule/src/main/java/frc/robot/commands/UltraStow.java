// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Elbow.Move_Elbow;
import frc.robot.commands.Shoulder.Move_Shoulder;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Shoulder;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class UltraStow extends SequentialCommandGroup {
  /** Creates a new UltraStow. */
  public UltraStow(sub_Elbow mElbow, sub_Shoulder msSub_Shoulder) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new Move_Elbow(mElbow, 60000).alongWith(new Move_Shoulder(msSub_Shoulder, 5000)).andThen(new Move_Shoulder(msSub_Shoulder, 4900)).andThen(new Move_Shoulder(msSub_Shoulder, 4800)).andThen(new Move_Shoulder(msSub_Shoulder, 4700)).andThen(new Move_Elbow(mElbow, 1500)));
  }
}
