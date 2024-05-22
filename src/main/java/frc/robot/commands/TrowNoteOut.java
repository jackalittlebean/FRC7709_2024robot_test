// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class TrowNoteOut extends Command {
  private final IndexerSubsystem indexerSubsystem;
  private final IntakeSubsystem intakeSubsystem;

  /** Creates a new TrowNoteOut. */
  public TrowNoteOut(IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.indexerSubsystem = indexerSubsystem;
    this.intakeSubsystem = intakeSubsystem;

    addRequirements(indexerSubsystem, intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intakeSubsystem.intakeSetOut();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    indexerSubsystem.feedNoteForAMP();
    intakeSubsystem.ejectNote();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.intakeSetBack();
    indexerSubsystem.stopIndexer();
    intakeSubsystem.stopWheel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
