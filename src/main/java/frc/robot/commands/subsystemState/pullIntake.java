// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.subsystemState;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HatchIntake;

public class pullIntake extends CommandBase {
  /** Creates a new pullIntake. */
  private final HatchIntake m_hatchIntake;

  public pullIntake(HatchIntake subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_hatchIntake = subsystem;
    addRequirements(m_hatchIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_hatchIntake.pullIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
