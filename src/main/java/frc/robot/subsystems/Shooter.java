// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private final CANSparkMax mainLeftShooter = 
    new CANSparkMax(MotorPorts.mainLeftShooter, MotorType.kBrushless);

  private final CANSparkMax assistLeftShooter = 
    new CANSparkMax(MotorPorts.assistLeftShooter, MotorType.kBrushless);

  private final CANSparkMax assistRightShooter = 
    new CANSparkMax(MotorPorts.assistRightShooter, MotorType.kBrushless);

  private final CANSparkMax mainRightShooter = 
    new CANSparkMax(MotorPorts.mainRightShooter, MotorType.kBrushless);

  public Shooter(double rate) {
    mainLeftShooter.setOpenLoopRampRate(rate);
    assistLeftShooter.setOpenLoopRampRate(rate);
    assistRightShooter.setOpenLoopRampRate(rate);
    mainRightShooter.setOpenLoopRampRate(rate);
  }

  public void setSpeed(double mainWheelSpeed, double assistWheelSpeed) {
    mainLeftShooter.set(mainWheelSpeed);
    assistLeftShooter.set(-assistWheelSpeed);
    assistRightShooter.set(assistWheelSpeed);
    mainRightShooter.set(-mainWheelSpeed);
  }

  public double getRPM() {
    return Math.abs(mainLeftShooter.getEncoder().getVelocity());
  }

  public boolean atSpeed() {
    return Math.abs(mainLeftShooter.getEncoder().getVelocity()) > 1200;
  }

}
