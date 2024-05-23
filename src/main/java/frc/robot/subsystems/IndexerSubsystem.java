// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;

public class IndexerSubsystem extends SubsystemBase {
  private final TalonFX indexerMotor;
  private final DigitalInput iDontKownHowToName;
  /** Creates a new IndexerSubsystem. */
  public IndexerSubsystem() {
    indexerMotor = new TalonFX(IndexerConstants.indexerMotorID);
    iDontKownHowToName = new DigitalInput(IndexerConstants.indexerDigitalInputID);

    indexerMotor.setInverted(false);
  }

  public void intakeNote(){
    if(!this.ifNoteArrive()){
      indexerMotor.setVoltage(IndexerConstants.intakeNoteVoltage);
    }else{
      this.stopIndexer();
    }
  }

  public void feedNoteForSpeaker(){
    indexerMotor.setVoltage(IndexerConstants.speakerVoltage);
  }

  public void feedNoteForAMP(){
    indexerMotor.setVoltage(IndexerConstants.ampVoltage);
  }

  public void ejectNote(){
    indexerMotor.setVoltage(IndexerConstants.ejectVoltage);
  }

  public void stopIndexer(){
    indexerMotor.setVoltage(0);
  }

  public boolean ifNoteArrive(){
    return iDontKownHowToName.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
