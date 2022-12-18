package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.stateStrings;
import frc.robot.subsystems.Comp;

public class toggleCompressor extends CommandBase {
  
  private String compressorMode = stateStrings.off;

  Comp m_compressor;
  
  public toggleCompressor(Comp subsystem) {
    m_compressor = subsystem;

    addRequirements(m_compressor);
  }

  @Override
  public void initialize() {
    switch (compressorMode) {
      case stateStrings.off:
        compressorMode = stateStrings.on;
        break;

      case stateStrings.on:
        compressorMode = stateStrings.off;
        break;
    }

    switch (compressorMode) {
      case stateStrings.off:
        m_compressor.stopCompressor();;
        break;           

      case stateStrings.on:
        m_compressor.startCompressor();;
        break;
    }
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
