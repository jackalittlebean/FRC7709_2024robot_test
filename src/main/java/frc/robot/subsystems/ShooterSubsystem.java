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

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    shooterMotor = new CANSparkMax(ShooterConstants.shooterMotorID, MotorType.kBrushless);
    shooterEncoder = shooterMotor.getEncoder();

    shooterMotor.restoreFactoryDefaults();
    shooterMotor.setInverted(false);
    shooterMotor.setIdleMode(IdleMode.kBrake);
    shooterMotor.burnFlash();
  }

  public void shootSpeaker(){
    shooterMotor.setVoltage(ShooterConstants.shootSpeakerVoltage);
  }

  public void shootAMP(){
    shooterMotor.setVoltage(ShooterConstants.shootAmpVoltage);
  }

  public void stopShooter(){
    shooterMotor.setVoltage(0);
  }

  public double getShooterSpeed(){
    return shooterEncoder.getVelocity();
  }

  public boolean isSpeakerReady(){
    return this.getShooterSpeed() >= Constants.ShooterConstants.shootSpeakerVoltage*Constants.ShooterConstants.shooterRatio;
  }

  public boolean isAMPReady(){
    return this.getShooterSpeed() >= Constants.ShooterConstants.shootAmpVoltage*Constants.ShooterConstants.shooterRatio;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
