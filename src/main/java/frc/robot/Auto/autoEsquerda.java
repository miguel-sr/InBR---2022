// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.auto.*;
import frc.robot.commands.subsystemState.*;
import frc.robot.subsystems.*;


public class autoEsquerda extends SequentialCommandGroup {

  public autoEsquerda(HatchIntake hatchIntake, Intake intake, Drive drive, Shooter shooter, Conveyor conveyor, Comp compressor) {
    addCommands(
      new pushIntake(hatchIntake),
        new startIntake(intake),
        new WaitCommand(1),
        new driveWithEncoder(drive, 1.2, 0.3),
        new driveWithEncoder(drive, 1.2, -0.3),
        new rotateToAngle(drive, -175),
        new ParallelCommandGroup(
          new startShooter(shooter),
          new startIntake(intake),
          new driveWithEncoder(drive, 0.9, -0.7)
        ),
        new startConveyor(conveyor),
        new startCompressor(compressor),
        new WaitCommand(2.5),
        new ParallelCommandGroup(
          new stopCompressor(compressor),
          new pullIntake(hatchIntake),
          new stopConveyor(conveyor),
          new stopShooter(shooter),
          new stopIntake(intake)
        )
    );
  }
}
