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
    public static final int driverControllerPort = 0;
    public static final int mechanismControllerPort = 0;
  }

  public static class LeftClimberConstants{
    public static final int leftClimberID = 0;

    public static final int leftLimitSwitchID = 0;
  }

  public static class RightClimberConstants{
    public static final int rightClimberID = 0;
    public static final int rightLimitSwitchID = 0;

    public static final double climberHighestSetpoint = 0;
    
  }

  public static class IntakeConstants{
    public static final int shaftMotorID = 0;
    public static final int wheelMotorID = 0;

    public static final double shaftPidKp = 0;
    public static final double shaftPidKi = 0;
    public static final double shaftPidKd = 0;

    public static final double intakeCancoderOffset = 0;
    public static final double intakeOutSetpoint = 0;
    public static final double intakeBackSetpoint = 0;
    public static final double intakeNoteWheelVoltage = 0;

  }

  public static class IndexerConstants{
    public static final int indexerMotorID = 0;
    public static final int indexerDigitalInputID = 0;

    public static final double speakerVoltage = 0;
    public static final double ampVoltage = 0;
    public static final double ejectVoltage = 0;
    public static final double intakeNoteVoltage = 0;
  }

  public static class ShooterConstants{
    public static final int shooterMotorID = 0;
    
    public static final double shootSpeakerVoltage = 0;
    public static final double shootAmpVoltage = 0;
    public static final double shooterRatio = 0;
    public static final double shootAMPRpmSetpoint = 0;
    public static final double shootSpeakerRpmSetpoint = 0;
  }

  public static double setMaxOutPut(double outPut, double maxOutPut){
    return Math.min(maxOutPut, Math.max(-maxOutPut, outPut));
  }
}
