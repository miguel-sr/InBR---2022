package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.Joysticks;
import frc.robot.subsystems.Conveyor;

public class conveyorCommand extends CommandBase {
  private final Joystick shooterStick = new Joystick(Joysticks.shooterStick);
  Conveyor m_ConveyorSubsystem;

  public conveyorCommand(Conveyor driveSubsystem) {
    m_ConveyorSubsystem = driveSubsystem;

    addRequirements(m_ConveyorSubsystem);
  }

  @Override
  public void execute() {
    if (shooterStick.getRawButton(Buttons.button_A)) {
      m_ConveyorSubsystem.setSpeed(-1);
    } else if (shooterStick.getRawButton(Buttons.button_B)) {
      m_ConveyorSubsystem.setSpeed(1);
    } else {
      m_ConveyorSubsystem.setSpeed(0);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
