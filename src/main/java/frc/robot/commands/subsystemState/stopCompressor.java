// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.subsystemState;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Comp;

public class stopCompressor extends CommandBase {
  /** Creates a new startShooter. */
  Comp m_compressor;
  
  public stopCompressor(Comp compressor) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_compressor = compressor;
    addRequirements(m_compressor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_compressor.stopCompressor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
