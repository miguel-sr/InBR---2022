// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.subsystemState;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class stopConveyor extends CommandBase {
  /** Creates a new startShooter. */
  Conveyor m_conveyor;
  
  public stopConveyor(Conveyor subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_conveyor = subsystem;
    addRequirements(m_conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_conveyor.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
