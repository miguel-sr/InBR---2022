// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.MotorSpeeds;
import frc.robot.Constants.stateStrings;
import frc.robot.subsystems.Shooter;

public class toggleShooterBackup extends CommandBase {
  /** Creates a new startShooter. */
  private String shooterMode = stateStrings.off;
  Shooter m_shooter;
  Joystick m_shooterStick;
  
  public toggleShooterBackup(Shooter shooter, Joystick shooterStick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
    m_shooterStick = shooterStick;
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override 
  public void execute() {

    if (m_shooterStick.getRawButtonPressed(Buttons.rightBumper)) {
      switch (shooterMode) {
        case stateStrings.off:
          shooterMode = stateStrings.on;
          break;
  
        case stateStrings.on:
          shooterMode = stateStrings.off;
          break;
      }
    }

    switch (shooterMode) {
      case stateStrings.off:
        m_shooter.setSpeed(0, 0);
        break;           

      case stateStrings.on:
        m_shooter.setSpeed(MotorSpeeds.mainWheelSpeed, MotorSpeeds.assistWheelSpeed);
        break;
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
