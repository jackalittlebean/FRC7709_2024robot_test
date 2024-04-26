// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  private final CANSparkMax shaft;
  private final CANSparkMax wheel;
  private final CANcoder shaftEncoder;
  private final PIDController shaftPidController;
  private final CANcoderConfiguration abCaNcoderConfig;

  private double shaftPidOutput;
  private double shaftPidSetpoint = IntakeConstants.intakeBackSetpoint;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    shaft = new CANSparkMax(Constants.IntakeConstants.shaftMotorID, MotorType.kBrushless);
    wheel = new CANSparkMax(Constants.IntakeConstants.wheelMotorID, MotorType.kBrushless);
    shaftEncoder = new CANcoder(0);
    abCaNcoderConfig  = new CANcoderConfiguration();

    abCaNcoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
    abCaNcoderConfig.MagnetSensor.MagnetOffset = Constants.IntakeConstants.intakeCancoderOffset;
    abCaNcoderConfig.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Signed_PlusMinusHalf;
    shaftEncoder.getConfigurator().apply(abCaNcoderConfig);

    shaftPidController = new PIDController(Constants.IntakeConstants.shaftPidKp, Constants.IntakeConstants.shaftPidKi, Constants.IntakeConstants.shaftPidKd);

    shaft.restoreFactoryDefaults();
    wheel.restoreFactoryDefaults();
    shaft.setInverted(false);
    wheel.setInverted(false);
    shaft.setIdleMode(IdleMode.kBrake);
    wheel.setIdleMode(IdleMode.kBrake);
    shaft.burnFlash();
    wheel.burnFlash();
  }

  public void intakeOut(){
    shaftPidSetpoint = IntakeConstants.intakeOutSetpoint;
  }

  public void intakeBack(){
    shaftPidSetpoint = IntakeConstants.intakeBackSetpoint;
  }

  public void intakingNote(){
    wheel.setVoltage(IntakeConstants.intakeNoteWheelVoltage);
  }

  public void ejectNote(){
    wheel.setVoltage(-IntakeConstants.intakeNoteWheelVoltage);
  }

  public void stopWheel(){
    wheel.setVoltage(0);
  }

  public void stopShaft(){
    shaft.setVoltage(0);
  }

  public double getShaftPosition(){
    return shaftEncoder.getPosition().getValue();
  }

  

  @Override
  public void periodic() {
    shaftPidOutput = shaftPidController.calculate(this.getShaftPosition(), shaftPidSetpoint);
    shaft.setVoltage(shaftPidOutput*12);
    // This method will be called once per scheduler run
  }
}
