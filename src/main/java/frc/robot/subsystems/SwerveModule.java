// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveModuleConstants;

public class SwerveModule extends SubsystemBase {
  private final CANSparkMax turningMotor;
  private final CANSparkMax driveMotor;

  private final RelativeEncoder driveEncoder;
  private final CANcoder turningCancoder;
  private final CANcoderConfiguration cancoderConfiguration;

  private final PIDController turningPidController;

  /** Creates a new SwerveModule. */
  public SwerveModule(int driveMotorID, int turningMotorID, int cancoderID, double cancoderOffset, boolean turningMotorInverted, boolean driveMotorInverted) {
    this.turningMotor = new CANSparkMax(turningMotorID, MotorType.kBrushless);
    this.driveMotor = new CANSparkMax(driveMotorID, MotorType.kBrushless);

    this.driveEncoder = driveMotor.getEncoder();
    this.turningCancoder = new CANcoder(cancoderID);
    this.cancoderConfiguration = new CANcoderConfiguration();
    cancoderConfiguration.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Signed_PlusMinusHalf;
    cancoderConfiguration.MagnetSensor.MagnetOffset = cancoderOffset;
    cancoderConfiguration.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
    turningCancoder.getConfigurator().apply(cancoderConfiguration);

    this.turningPidController = new PIDController(SwerveModuleConstants.angleKp, SwerveModuleConstants.angleKi, SwerveModuleConstants.angleKd);
    turningMotor.restoreFactoryDefaults();
    driveMotor.restoreFactoryDefaults();
    turningMotor.setInverted(turningMotorInverted);
    driveMotor.setInverted(driveMotorInverted);
    turningMotor.setIdleMode(IdleMode.kBrake);
    driveMotor.setIdleMode(IdleMode.kBrake);
    turningMotor.burnFlash();
    driveMotor.burnFlash();

    turningPidController.enableContinuousInput(-180, 180);
  }

  public double getDrivePosition(){
    return driveEncoder.getPosition();
  }

  public double getTurningPosition(){
    return turningCancoder.getAbsolutePosition().getValue();
  }

  public double getDriveVelocity(){
    return driveEncoder.getVelocity();
  }

  public void resetDrivePosition(){
    driveEncoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
