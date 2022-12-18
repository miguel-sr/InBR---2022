package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Axis;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.MotorSpeeds;
import frc.robot.subsystems.Intake;

public class intakeCommand extends CommandBase {
  private final Joystick shooterStick = new Joystick(Joysticks.shooterStick);
  Intake m_IntakeSubsystem;

  public intakeCommand(Intake driveSubsystem) {
    m_IntakeSubsystem = driveSubsystem;

    addRequirements(m_IntakeSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (shooterStick.getRawAxis(Axis.leftTrigger) != 0) {
      m_IntakeSubsystem.setSpeed(shooterStick.getRawAxis(Axis.leftTrigger) * MotorSpeeds.intakeSpeed);
    } else if (shooterStick.getRawAxis(Axis.rightTrigger) != 0) {
      m_IntakeSubsystem.setSpeed(-shooterStick.getRawAxis(Axis.rightTrigger) * MotorSpeeds.intakeSpeed);
    } else {
      m_IntakeSubsystem.setSpeed(0);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
