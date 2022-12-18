// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.navx2Constants;
import frc.robot.subsystems.Drive;

public class rotateToAngle extends CommandBase {
  
  PIDController pid = new PIDController(navx2Constants.KP, 0, 0.0);
  Drive m_drive;
  private int m_stable_done;
  double m_angle;

  public rotateToAngle(Drive drive, double angle) {
    m_angle = angle;
    m_drive = drive;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    m_drive.resetGyro();
    m_stable_done = 0;
  }

  @Override
  public void execute() {
    double output = 0;

    pid.setTolerance(navx2Constants.tolerance);
    output = pid.calculate(m_drive.getAngle(), m_angle);
      
    if (output > navx2Constants.MaxTurnOutput) {
      output = navx2Constants.MaxTurnOutput;
    } else if (output < -navx2Constants.MaxTurnOutput) {
      output = -navx2Constants.MaxTurnOutput;
    }

    if (pid.atSetpoint()) {
      output = 0;
      m_stable_done++;
    } else {
      m_stable_done = 0;
      m_drive.curvatureDrive(0, -output, true);
    }

  }

  @Override
  public void end(boolean interrupted) {
    pid.close();
  }

  @Override
  public boolean isFinished() {
    // FINALIZA O COMANDO SE O ROBO ESTIVER ALINHADO TEMPO O SUFICIENTE
    return m_stable_done >= 20;
  }
}
