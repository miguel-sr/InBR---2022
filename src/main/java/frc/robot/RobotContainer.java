package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.stateStrings;
import frc.robot.Auto.*;
import frc.robot.commands.teleop.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  Joystick shooterStick = new Joystick(Joysticks.shooterStick);
  HatchIntake hatchIntake = new HatchIntake();
  Conveyor conveyor = new Conveyor();
  Shooter shooter = new Shooter(1);
  Comp compressor = new Comp();
  Intake intake = new Intake();
  Drive drive = new Drive();
  Led led = new Led();
  
  SendableChooser<Command> m_chooserAuto = new SendableChooser<>();

  public RobotContainer() {
    configureButtonBindings();

      if (DriverStation.getAlliance() == Alliance.Red) {
        shooter.setDefaultCommand(
          new toggleShooter(shooter, led, stateStrings.red, shooterStick)
        );
      } else if (DriverStation.getAlliance() == Alliance.Blue) {
        shooter.setDefaultCommand(
          new toggleShooter(shooter, led, stateStrings.blue, shooterStick)
        );
      }
            
    drive.setDefaultCommand(new driveCommand(drive));
    intake.setDefaultCommand(new intakeCommand(intake));
    conveyor.setDefaultCommand(new conveyorCommand(conveyor));

    m_chooserAuto.setDefaultOption("Auto - Esquerda", 
      new autoEsquerda(hatchIntake, intake, drive, shooter, conveyor, compressor));
    
    m_chooserAuto.addOption("Auto - Meio", 
      new autoMeio(hatchIntake, intake, drive, shooter, conveyor, compressor));

    m_chooserAuto.addOption("Auto - Direita", 
      new autoDireita(hatchIntake, intake, drive, shooter, conveyor, compressor));

    SmartDashboard.putData(m_chooserAuto);
  }

  private void configureButtonBindings() {
  
    new JoystickButton(shooterStick, Buttons.button_Y).whenPressed(
      new toggleSolenoid(hatchIntake));

    new JoystickButton(shooterStick, Buttons.button_X).whenPressed(
      new toggleCompressor(compressor));
      
  }

  public Command getAutonomousCommand() {
    return m_chooserAuto.getSelected();    
  }
}

