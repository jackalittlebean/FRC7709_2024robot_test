// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.LeftClimberSubsystem;

public class MoveLeftClimber extends Command {
  private final LeftClimberSubsystem m_climberSubsystem;
  private final DoubleSupplier inputFunc; 
  private final BooleanSupplier startFunc;
  /** Creates a new MoveLeftClimber. */
  public MoveLeftClimber(LeftClimberSubsystem leftClimberSubsystem, DoubleSupplier doubleSupplier, BooleanSupplier booleanSupplier) {
    this.m_climberSubsystem = leftClimberSubsystem;
    this.inputFunc = doubleSupplier;
    this.startFunc = booleanSupplier;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(leftClimberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(startFunc.getAsBoolean()){
      if(m_climberSubsystem.ifclimberCanDrop() && m_climberSubsystem.getPotition() < Constants.RightClimberConstants.climberHighestSetpoint){
        m_climberSubsystem.moveClimber(inputFunc.getAsDouble()*12);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
