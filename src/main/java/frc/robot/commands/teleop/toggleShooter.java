package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.MotorSpeeds;
import frc.robot.Constants.stateStrings;
import frc.robot.subsystems.Led;
import frc.robot.subsystems.Shooter;

public class toggleShooter extends CommandBase {

  Shooter m_shooter;
  Joystick m_shooterStick;
  Led m_led;
  String m_alliance, shooterMode = stateStrings.off;
  
  public toggleShooter(Shooter shooter, Led led, String alliance, Joystick shooterStick) {
    m_shooter = shooter;
    m_shooterStick = shooterStick;
    m_alliance = alliance;
    m_led = led;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {}

  @Override 
  public void execute() {

    if (m_shooterStick.getRawButton(Buttons.leftBumper)) {
      m_shooter.setSpeed(0.05, 0.05);
    } else {
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

    // LED
    if (m_alliance == stateStrings.blue) {
      if (m_shooter.atSpeed()) {
        m_led.setColor(-0.09);
      } else {
        m_led.setColor(-0.41);
      }
    } else if (m_alliance == stateStrings.red) {
      if (m_shooter.atSpeed()) {
        m_led.setColor(-0.11);
      } else {
        m_led.setColor(-0.39); 
      }
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
