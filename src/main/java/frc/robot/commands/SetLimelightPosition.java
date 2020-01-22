/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.LimelightServo;

public class SetLimelightPosition extends CommandBase {
  /**
   * Creates a new SetLimelightPosition.
   */
  private LimelightServo servo;
  private Limelight limelight;
  private double x;
  private boolean isTarget;
  private double position;

  public SetLimelightPosition(Limelight limelight, LimelightServo servo) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.servo = servo;
    this.limelight = limelight;
    addRequirements(this.servo, this.limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    position = servo.getServoValue();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {//this is looping until completion
    x = limelight.getTX(); //start by getting the x value from the limelight and saving it to a local variable
    isTarget = limelight.isTargetDetected(); //now your getting whether or not you have a target acquired

    if(isTarget && x > 0){ //checking if there is a target and then if the X value is greater than 0
      position -= 0.1; //set the current position one less
    }else if(isTarget && x < 0){ //if its on target and is less than 0
      position += 0.1; //set the position one more
    }else if(!isTarget){ //if its not on target at all
      if(position == 1.0){ //if the position is already at max
        position -= 0.1; //move it left
      }else{ //this is going to cause a problem of the servo moving back and forth, I may have to add a boolean
        position += 0.1; //move it right
      }
    }
    servo.setLimelight(position); //set the limelight position. 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(isTarget && x==0){ //checks whether or not the limelight is centered
      return true;
    }else{
      return false;
    }
  }
}
