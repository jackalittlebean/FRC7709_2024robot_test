// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootSpeaker extends Command {
  private final ShooterSubsystem m_shooterSubsystem;
  private final IndexerSubsystem m_indexerSubsystem;
  /** Creates a new FeedNote. */
  public ShootSpeaker(ShooterSubsystem shooterSubsystem, IndexerSubsystem indexerSubsystem) {
    this.m_shooterSubsystem = shooterSubsystem;
    this.m_indexerSubsystem = indexerSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooterSubsystem, m_indexerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooterSubsystem.useShooter(ShooterConstants.shootSpeakerVoltage, ShooterConstants.shootSpeakerRpmSetpoint);
    if(m_shooterSubsystem.shooterIsReady()){
      m_indexerSubsystem.feedNoteForSpeaker();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexerSubsystem.stopIndexer();
    m_shooterSubsystem.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
