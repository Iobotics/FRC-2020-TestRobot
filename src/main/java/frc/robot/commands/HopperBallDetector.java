/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

public class HopperBallDetector extends CommandBase {
  /**
   * Creates a new HopperBallDetector.
   */
  private Hopper hopper;
  
  public HopperBallDetector(Hopper hopper) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.hopper = hopper;
    addRequirements(hopper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (hopper.getIntakeSensorValue()) {
      hopper.setFrontPower(0.25);
    } else {
      hopper.setFrontPower(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
