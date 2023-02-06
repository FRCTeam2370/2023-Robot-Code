// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LEDS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.sub_LEDs;

public class LED extends CommandBase {
  /** Creates a new LED. */
  public LED(sub_LEDs mSub_LEDs) {
    addRequirements(mSub_LEDs);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Arm.pickupattempted) {
      if (Arm.pickupworked == true) {
        sub_LEDs.LEDs_controller(151, 215, 0);
      } else {
        sub_LEDs.LEDs_controller(232, 118, 19);
      }
    } else if (RobotContainer.leftbumper_operater.getAsBoolean() == true) {
      sub_LEDs.LEDs_controller(212, 245, 2);
    } else if (RobotContainer.righbumper_operater.getAsBoolean() == true) {
      sub_LEDs.LEDs_controller(69, 5, 115);
    } else if (DriverStation.getMatchTime() <= 30) {
      if (DriverStation.getAlliance() == Alliance.Red) {
        if (DriverStation.getMatchTime() % 2 == 0) {
          sub_LEDs.LEDs_controller(207, 12, 12);
        } else {
          sub_LEDs.LEDs_controller(0, 0, 0);
        }
      } else if (DriverStation.getAlliance() == Alliance.Blue) {
        if (DriverStation.getMatchTime() % 2 == 0) {
          sub_LEDs.LEDs_controller(12, 25, 207);
        } else {
          sub_LEDs.LEDs_controller(0, 0, 0);
        }
      } else {
        if (DriverStation.getMatchTime() % 2 == 0) {
          sub_LEDs.LEDs_controller(2, 3, 3);
        } else {
          sub_LEDs.LEDs_controller(0, 0, 0);
        }
      }
    } else if (DriverStation.isTeleop()) {
      if (DriverStation.getAlliance() == Alliance.Red) {
        sub_LEDs.LEDs_controller(207, 12, 12);
      } else if (DriverStation.getAlliance() == Alliance.Blue) {
        sub_LEDs.LEDs_controller(12, 25, 207);
      } else {
        sub_LEDs.LEDs_controller(2, 3, 3);
      }

    } else {
      sub_LEDs.LEDs_controller(0, 0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
