// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveModuleConstants;
import frc.robot.Constants.SwerveSubsystemConstants;

public class SwerveSubsystem extends SubsystemBase {
  private final SwerveModule leftFrontModule;
  private final SwerveModule rightFrontModule;
  private final SwerveModule leftBackModule;
  private final SwerveModule rightBackModule;

  private final Pigeon2 gyro;
  private final Pigeon2Configuration gyroConfiguration;

  /** Creates a new SwerveSubsystem. */
  public SwerveSubsystem() {
    this.leftFrontModule = new SwerveModule(
      SwerveModuleConstants.leftFrontDriveMotorID, 
      SwerveModuleConstants.leftFrontTurningMotorID, 
      SwerveModuleConstants.leftFrontCancoderID, 
      SwerveModuleConstants.leftFrontCancoderOffset, 
      SwerveModuleConstants.leftFrontTurningMotorInverted, 
      SwerveModuleConstants.leftFrontDriveMotorInverted);
    this.rightFrontModule = new SwerveModule(
      SwerveModuleConstants.rightFrontDriveMotorID, 
      SwerveModuleConstants.rightFrontTurningMotorID, 
      SwerveModuleConstants.rightFrontCancoderID, 
      SwerveModuleConstants.rightFrontCancoderOffset, 
      SwerveModuleConstants.rightFrontTurningMotorInverted, 
      SwerveModuleConstants.rightFrontDriveMotorInverted);
    this.leftBackModule = new SwerveModule(
      SwerveModuleConstants.leftBackDriveMotorID, 
      SwerveModuleConstants.leftBackTurningMotorID, 
      SwerveModuleConstants.leftBackCancoderID, 
      SwerveModuleConstants.leftBackCancoderOffset, 
      SwerveModuleConstants.leftBackTurningMotorInverted, 
      SwerveModuleConstants.leftBackDriveMotorInverted);  
    this.rightBackModule = new SwerveModule(
      SwerveModuleConstants.rightBackDriveMotorID, 
      SwerveModuleConstants.rightBackTurningMotorID, 
      SwerveModuleConstants.rightBackCancoderID,
      SwerveModuleConstants.rightBackCancoderOffset,
      SwerveModuleConstants.rightBackTurningMotorInverted,
      SwerveModuleConstants.rightBackDriveMotorInverted);

    this.gyro = new Pigeon2(SwerveSubsystemConstants.gyroID);  
    this.gyroConfiguration = new Pigeon2Configuration();
    gyroConfiguration.MountPose.MountPoseYaw = -10;
    gyro.getConfigurator().apply(gyroConfiguration);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
