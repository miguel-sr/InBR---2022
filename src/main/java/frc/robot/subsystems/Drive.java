// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;

public class Drive extends SubsystemBase {

  private final WPI_VictorSPX leftMotorBack = 
    new WPI_VictorSPX(MotorPorts.leftMotorBack);

  private final WPI_VictorSPX leftMotorFront = 
    new WPI_VictorSPX(MotorPorts.leftMotorFront);

  private final WPI_VictorSPX rightMotorFront = 
    new WPI_VictorSPX(MotorPorts.rightMotorFront);

  private final WPI_VictorSPX rightMotorBack = 
    new WPI_VictorSPX(MotorPorts.rightMotorBack);
    
  private final MotorControllerGroup leftMotors = 
    new MotorControllerGroup(leftMotorBack, leftMotorFront);

  private final MotorControllerGroup rightMotors = 
    new MotorControllerGroup(rightMotorBack, rightMotorFront);

  private final DifferentialDrive drive = 
    new DifferentialDrive(leftMotors, rightMotors);

  private final Encoder leftEncoder = 
    new Encoder(1, 2, false, EncodingType.k4X);

  private final Encoder rightEncoder = 
    new Encoder(7, 8, true, EncodingType.k4X);

  private final AHRS gyro =
    new AHRS(SPI.Port.kMXP);

  // Odometria

  private final DifferentialDriveOdometry mOdometry;

  static double wheelDiameterMeters = 0.1524;
  static double pulsesPerRevolution = 2048;
  public final static double distancePerPulse = (wheelDiameterMeters * Math.PI) / pulsesPerRevolution;

  public Drive() {
    leftMotorBack.setNeutralMode(NeutralMode.Brake);
    leftMotorFront.setNeutralMode(NeutralMode.Brake);
    rightMotorFront.setNeutralMode(NeutralMode.Brake);
    rightMotorBack.setNeutralMode(NeutralMode.Brake);

    leftMotors.setInverted(false);
    rightMotors.setInverted(true);

    leftEncoder.setDistancePerPulse(distancePerPulse);
    rightEncoder.setDistancePerPulse(distancePerPulse);

    resetEncoders();
    resetGyro();

    mOdometry = new DifferentialDriveOdometry(gyro.getRotation2d());
    resetOdometry(getPose());
  }

  @Override
  public void periodic() {
    mOdometry.update(
      gyro.getRotation2d(), 
      leftEncoder.getDistance(), 
      rightEncoder.getDistance());

    SmartDashboard.putString("Odometria", mOdometry.getPoseMeters().toString());
  }

  public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    drive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
  }

  public double getEncodersDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
  }
  
  public double getLeftEncoder() {
    return leftEncoder.getDistance();
  }

  public double getRightEncoder() {
    return rightEncoder.getDistance();
  }

  public Pose2d getPose() {
    return mOdometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    mOdometry.resetPosition(pose, gyro.getRotation2d());
  }
  
  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getAverageEncoderDistance() {
    return Math.abs((leftEncoder.getDistance() + rightEncoder.getDistance()) / 2);
  }

  public Rotation2d getHeading() {
    return Rotation2d.fromDegrees(-gyro.getAngle());
  }

  public double getAngle() {
    return -gyro.getAngle();
  }

  public double getTurnRate() {
    return -gyro.getRate();
  }

  public void resetGyro() {
    gyro.reset();
    gyro.calibrate();
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMotors.setVoltage(leftVolts);
    rightMotors.setVoltage(rightVolts);
    drive.feed();
  }

  public void stop() {
    drive.stopMotor();
  }
}