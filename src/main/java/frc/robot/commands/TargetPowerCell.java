/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.MachineLearning;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TargetPowerCell extends PIDCommand {
  /**
   * Creates a new TargetPowerCell.
   */
  public TargetPowerCell(MachineLearning machineLearning, Drivetrain drive) {
    super(
        // The controller that the command will use
        new PIDController(0.027, 0, 0),
        // This should return the measurement
        machineLearning::findNearest,
        // This should return the setpoint (can also be a constant)
        () -> 450,
        // This uses the output
        output -> {
          drive.setTank(output, output);
          // Use the output here
        });

        addRequirements(drive,machineLearning);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
