// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int driverControllerPort = 1;
    public static final int mechanismControllerPort = 0;
  }

  public static class SwerveSubsystemConstants{
    public static final int gyroID = 23;
  }

  public static class SwerveModuleConstants{
    public static final int leftFrontDriveMotorID = 7;
    public static final int leftFrontTurningMotorID = 11;
    public static final int leftFrontCancoderID = 20;
    public static final int rightFrontDriveMotorID = 8;
    public static final int rightFrontTurningMotorID = 12;
    public static final int rightFrontCancoderID = 17;
    public static final int leftBackDriveMotorID = 9;
    public static final int leftBackTurningMotorID = 13;
    public static final int leftBackCancoderID = 18;
    public static final int rightBackDriveMotorID = 10;
    public static final int rightBackTurningMotorID = 14;
    public static final int rightBackCancoderID = 31;

    public static final double leftFrontCancoderOffset = 0.83;
    public static final double rightFrontCancoderOffset = 0.4;
    public static final double leftBackCancoderOffset = 0.78;
    public static final double rightBackCancoderOffset = 0;

    public static final boolean leftFrontTurningMotorInverted = true;
    public static final boolean leftFrontDriveMotorInverted = true;
    public static final boolean rightFrontTurningMotorInverted = true;
    public static final boolean rightFrontDriveMotorInverted = true;
    public static final boolean leftBackTurningMotorInverted = true;
    public static final boolean leftBackDriveMotorInverted = true;
    public static final boolean rightBackTurningMotorInverted = true;
    public static final boolean rightBackDriveMotorInverted = true;

    public static final double angleKp = 0.623;
    public static final double angleKi = 0.41;
    public static final double angleKd = 0.752;

  }

  public static class LeftClimberConstants{
    public static final int leftClimberID = 2;

    public static final int leftLimitSwitchID = 1;
  }

  public static class RightClimberConstants{
    public static final int rightClimberID = 5;
    public static final int rightLimitSwitchID = 3;

    public static final double climberHighestSetpoint = 1.5;
    
  }

  public static class IntakeConstants{
    public static final int shaftMotorID = 0;
    public static final int wheelMotorID = 0;
    public static final int intakeCancoderID = 0;

    public static final double shaftPidKp = 0.52;
    public static final double shaftPidKi = 0.367;
    public static final double shaftPidKd = 0.718;

    public static final double intakeCancoderOffset = 0.8;
    public static final double intakeOutSetpoint = 0.4;
    public static final double intakeBackSetpoint = 1;
    public static final double intakeNoteWheelVoltage = 4;

  }

  public static class IndexerConstants{
    public static final int indexerMotorID = 4;
    public static final int indexerDigitalInputID = 16;

    public static final double speakerVoltage = 9;
    public static final double ampVoltage = 7;
    public static final double ejectVoltage = 7;
    public static final double intakeNoteVoltage = 7;
  }

  public static class ShooterConstants{
    public static final int shooterMotorID = 19;
    
    public static final double shootSpeakerVoltage = 10;
    public static final double shootAmpVoltage = 9;
    public static final double shooterRatio = 0;
    public static final double shootAMPRpmSetpoint = 0;
    public static final double shootSpeakerRpmSetpoint = 0;
  }

  public static double setMaxOutPut(double outPut, double maxOutPut){
    return Math.min(maxOutPut, Math.max(-maxOutPut, outPut));
  }
}
