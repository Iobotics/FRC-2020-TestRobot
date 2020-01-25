/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private double dposition = 0.0; // change of position
  private double vposition = 0.1; // constant to set the scale of changing position
  private boolean rightMoving;
 

  public SetLimelightPosition(Limelight limelight, LimelightServo servo) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.servo = servo;
    this.limelight = limelight;
    addRequirements(this.servo, this.limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //position = servo.getServoValue();
    SmartDashboard.putNumber("distance", 0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {//this is looping until completion
    position = servo.getServoValue();
    x = limelight.getTX(); //start by getting the x value from the limelight and saving it to a local variable
    /*isTarget = limelight.isTargetDetected(); //now your getting whether or not you have a target acquired
    if(position == 1.0){
      servo.setLimelight(0.0);
    }else if(position == 0.0){
      servo.setLimelight(1.0);
    }*/
    if(limelight.isTargetDetected()){
      if(x > 0.0){
        position += 0.1;
      }else if(x < 0.0){
        position -= 0.1;
      }
    }else if(!limelight.isTargetDetected()){
      if(servo.getServoValue() >= 0.0 && rightMoving){
        position += 0.1;
      }else{
        position -= 0.1;
      }
    }
    if(servo.getServoValue() == 1.0){
      rightMoving = false;
    }else if(servo.getServoValue() == 0.0){
      rightMoving = true;
    }

    servo.setLimelight(position);
    //servo.setLimelight(position + dposition*vposition); //set the limelight position to the origin position plus the change of the position * scaler 
    SmartDashboard.putNumber("servoPosition", servo.getServoValue());
    SmartDashboard.putBoolean("isTargeted", isTarget);
    Timer.delay(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(limelight.isTargetDetected() && limelight.getTX()==0){ //checks whether or not the limelight is centered
      return true;
    }else{
      return false;
    }
  }
}

