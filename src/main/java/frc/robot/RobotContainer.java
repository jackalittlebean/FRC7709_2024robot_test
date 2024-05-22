// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.LeftClimberConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.IntakeNote;
import frc.robot.commands.MoveLeftClimber;
import frc.robot.commands.MoveRightClimber;
import frc.robot.commands.ShootAMP;
import frc.robot.commands.ShootSpeaker;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LeftClimberSubsystem;
import frc.robot.subsystems.RightClimberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final IndexerSubsystem indexerSubsystem = new IndexerSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private final LeftClimberSubsystem leftClimberSubsystem = new LeftClimberSubsystem();
  private final RightClimberSubsystem rightClimberSubsystem = new RightClimberSubsystem();

  private final CommandXboxController driveJoystick = new CommandXboxController(OperatorConstants.driverControllerPort);
  private final CommandXboxController mechanismJoystick = new CommandXboxController(OperatorConstants.mechanismControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    mechanismJoystick.a();
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    DoubleSupplier leftClimberInput = () -> mechanismJoystick.getLeftY();
    DoubleSupplier rightClimberInput = () -> mechanismJoystick.getRightY();
    BooleanSupplier startClimber = () -> mechanismJoystick.getHID().getLeftBumper();
    
    leftClimberSubsystem.setDefaultCommand(new MoveLeftClimber(leftClimberSubsystem, leftClimberInput, startClimber));
    rightClimberSubsystem.setDefaultCommand(new MoveRightClimber(rightClimberSubsystem, rightClimberInput, startClimber));

    mechanismJoystick.x().whileTrue(new IntakeNote(intakeSubsystem, indexerSubsystem));
    
    BooleanSupplier shootNote = () -> mechanismJoystick.y().getAsBoolean();
    mechanismJoystick.rightTrigger().whileTrue(new ShootSpeaker(shooterSubsystem, indexerSubsystem, shootNote, false));
    mechanismJoystick.leftTrigger().whileTrue(new ShootAMP(shooterSubsystem, indexerSubsystem, shootNote, false));
    
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
