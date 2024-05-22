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

public class LeftClimberSubsystem extends SubsystemBase {
  private final CANSparkMax climberMotor;
  private final RelativeEncoder climberEncoder;
  private final DigitalInput limitSwitch;

  /** Creates a new LeftClimberSubsystem. */
  public LeftClimberSubsystem() {
    climberMotor = new CANSparkMax(Constants.LeftClimberConstants.leftClimberID, MotorType.kBrushless);
    climberEncoder = climberMotor.getEncoder();
    limitSwitch = new DigitalInput(Constants.LeftClimberConstants.leftLimitSwitchID);

    climberMotor.restoreFactoryDefaults();
    climberMotor.setInverted(false);
    climberMotor.setIdleMode(IdleMode.kBrake);
    climberMotor.burnFlash();
  }

  public void moveClimber(double voltage){
    if(this.ifclimberCanDrop() && voltage < 0){
      climberMotor.setVoltage(voltage);
    }else if(this.getPotition() < Constants.RightClimberConstants.climberHighestSetpoint && voltage > 0){
      climberMotor.setVoltage(voltage);
    }else{
      this.stopClimber();
    }
  }

  public boolean ifclimberCanDrop(){
    return !limitSwitch.get();
  }

  public double getPotition(){
    return climberEncoder.getPosition();
  }

  public void stopClimber(){
    climberMotor.setVoltage(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
