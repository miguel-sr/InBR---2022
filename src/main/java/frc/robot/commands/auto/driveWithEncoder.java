// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class driveWithEncoder extends CommandBase {
  /** Creates a new drive. */
  Drive m_DriveSubsystem;
  double m_distance, m_speed;

  public driveWithEncoder(Drive driveSubsystem, double distance, double speed) {
    m_DriveSubsystem = driveSubsystem;
    m_distance = distance;
    m_speed = speed;
    addRequirements(m_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveSubsystem.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {    
    m_DriveSubsystem.curvatureDrive(-m_speed, 0, true);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_DriveSubsystem.getAverageEncoderDistance() > m_distance;
  }
}
