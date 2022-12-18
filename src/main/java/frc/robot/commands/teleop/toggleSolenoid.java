package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HatchIntake;

public class toggleSolenoid extends CommandBase {
  private final HatchIntake m_hatchIntake;

  public toggleSolenoid(HatchIntake subsystem) {
    m_hatchIntake = subsystem;
    addRequirements(m_hatchIntake);
  }

  @Override
  public void initialize() {
    m_hatchIntake.toggleSolenoid();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
