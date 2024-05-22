// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  private final CANSparkMax shooterMotor;
  private final RelativeEncoder shooterEncoder;

  private double rpmSetpoint;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    shooterMotor = new CANSparkMax(ShooterConstants.shooterMotorID, MotorType.kBrushless);
    shooterEncoder = shooterMotor.getEncoder();

    shooterMotor.restoreFactoryDefaults();
    shooterMotor.setInverted(false);
    shooterMotor.setIdleMode(IdleMode.kBrake);
    shooterMotor.burnFlash();

    rpmSetpoint = 0;
  }

  public void shootSpeaker(){
    shooterMotor.setVoltage(ShooterConstants.shootSpeakerVoltage);
  }

  public void shootAMP(){
    shooterMotor.setVoltage(ShooterConstants.shootAmpVoltage);
  }

  public void useShooter(double shooterVoltage, double rpmSetpoint){
    this.rpmSetpoint = rpmSetpoint;
    shooterMotor.setVoltage(shooterVoltage);
  }

  public void stopShooter(){
    shooterMotor.setVoltage(0);
  }

  public double getShooterSpeed(){
    return shooterEncoder.getVelocity();
  }

  public boolean shooterIsReady(){
    return this.getShooterSpeed() >= rpmSetpoint;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
