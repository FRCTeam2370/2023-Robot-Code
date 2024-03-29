// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Elbow.Move_Elbow;
import frc.robot.commands.Shoulder.Move_Shoulder;
import frc.robot.subsystems.sub_Elbow;
import frc.robot.subsystems.sub_Shoulder;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class loading extends SequentialCommandGroup {
  /** Creates a new loading. */
  public loading(sub_Elbow elbow, sub_Shoulder shoulder) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Move_Elbow(elbow, 75300).andThen(new Move_Shoulder(shoulder, 15700).andThen(new WaitCommand(30))));
    
  }
}
