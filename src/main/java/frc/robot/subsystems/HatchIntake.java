// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HatchIntake extends SubsystemBase {
  /** Creates a new HatchIntake. */
  private final DoubleSolenoid solenoid =
      new DoubleSolenoid(7, PneumaticsModuleType.CTREPCM, 7, 6);
  
  public HatchIntake() {
    pullIntake();
  }
  
  public void pullIntake() {
    solenoid.set(Value.kForward);
  }

  public void pushIntake() {
    solenoid.set(Value.kReverse);
  }
  
  public void toggleSolenoid() {
    solenoid.toggle();
  }
}
