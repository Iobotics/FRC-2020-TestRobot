/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.LimelightServo;;



public class ResetServo extends CommandBase {
  private LimelightServo servo;
  private boolean x = false;


public ResetServo(LimelightServo servo) {
    this.servo = servo;
    addRequirements(this.servo);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    servo.setLimelight(1.0);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    if(servo.getServoValue()== 1.0)
      x = true;
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    if(x)
    {
      return true;
    }
      return false;
   
  }

  // Called once after isFinished returns true

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run

 
}
