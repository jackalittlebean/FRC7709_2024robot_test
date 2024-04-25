// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
  private final CANSparkMax leftClimber;
  private final CANSparkMax rightClimber;

  private final RelativeEncoder leftEncoder;
  private final RelativeEncoder rightEncoder;

  private final DigitalInput leftLimitSwitch;
  private final DigitalInput rightLimitSwitch;

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    leftClimber = new CANSparkMax(ClimberConstants.leftClimberID, MotorType.kBrushless);
    rightClimber = new CANSparkMax(ClimberConstants.rightClimberID, MotorType.kBrushless);
    leftLimitSwitch = new DigitalInput(ClimberConstants.leftLimitSwitchID);
    rightLimitSwitch = new DigitalInput(Constants.ClimberConstants.rightLimitSwitchID); 
    leftEncoder = leftClimber.getEncoder();
    rightEncoder = rightClimber.getEncoder();

    leftClimber.restoreFactoryDefaults();
    rightClimber.restoreFactoryDefaults();
    leftClimber.setInverted(false);
    rightClimber.setInverted(false);
    leftClimber.setIdleMode(IdleMode.kBrake);
    rightClimber.setIdleMode(IdleMode.kBrake);
    leftClimber.burnFlash();
    rightClimber.burnFlash();
  }

  public void moveLeftClimber(double voltage){
    leftClimber.setVoltage(voltage);
  }

  public void moveRightClimber(double voltage){
    rightClimber.setVoltage(voltage);
  }

  public boolean ifLeftCanDrop(){
    return !leftLimitSwitch.get();
  }

  public boolean ifRightCanDrop(){
    return !rightLimitSwitch.get();
  }

  public double getLeftPotition(){
    return leftEncoder.getPosition();
  }

  public double getRightPotition(){
    return rightEncoder.getPosition();
  }

  public void stopLeftClimber(){
    leftClimber.setVoltage(0);
  }

  public void stopRightClimber(){
    rightClimber.setVoltage(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
