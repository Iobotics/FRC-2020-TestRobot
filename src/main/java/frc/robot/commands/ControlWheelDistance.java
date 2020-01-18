/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlWheel;

public class ControlWheelDistance extends CommandBase {
  /**
   * Creates a new ControlWheelDistance.
   */
  private ControlWheel controlWheel = null;
  private double distance = 0;
  public ControlWheelDistance(double distance, ControlWheel controlWheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlWheel = controlWheel;
    addRequirements(this.controlWheel);
    this.distance = distance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controlWheel.setRotations(distance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlWheel.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return controlWheel.checkRotations();
  }
}
