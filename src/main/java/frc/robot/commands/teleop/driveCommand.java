package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Axis;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.stateStrings;
import frc.robot.subsystems.Drive;

public class driveCommand extends CommandBase {
  private final Joystick driverStick = new Joystick(Joysticks.driverStick);
  private String speedMode = stateStrings.off;
  Drive m_DriveSubsystem;

  

  public driveCommand(Drive driveSubsystem) {
    m_DriveSubsystem = driveSubsystem;

    addRequirements(m_DriveSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {  
    if (driverStick.getRawButtonPressed(Buttons.leftBumper)) {
      switch (speedMode) {
        case stateStrings.off:
          speedMode = stateStrings.on;
          break;

        case stateStrings.on:
          speedMode = stateStrings.off;
          break;
      }
    }

    switch (speedMode) {
      case stateStrings.off:
        changeSpeed(driverStick, 2.0, 1.7);
        break;           

      case stateStrings.on:
        changeSpeed(driverStick, 1.0, 1.3);
        break;
    }
  }

  public void changeSpeed(Joystick driverStick, double divider_axis_Y, double divider_axis_X) {
    if (driverStick.getRawAxis(Axis.leftTrigger) > 0) {
      m_DriveSubsystem.curvatureDrive(driverStick.getRawAxis(Axis.leftTrigger) / divider_axis_Y, driverStick.getRawAxis(Axis.leftStick_X) / (divider_axis_X), true);
    } else if (driverStick.getRawAxis(Axis.rightTrigger) > 0) {
      m_DriveSubsystem.curvatureDrive(-driverStick.getRawAxis(Axis.rightTrigger) / divider_axis_Y, driverStick.getRawAxis(Axis.leftStick_X) / (divider_axis_X), true);
    } else {
      m_DriveSubsystem.curvatureDrive(0, driverStick.getRawAxis(Axis.leftStick_X) / (divider_axis_X), true);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_DriveSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
