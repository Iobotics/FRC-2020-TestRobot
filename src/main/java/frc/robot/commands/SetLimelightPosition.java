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
  private boolean isTarget;
  private double dposition = 0.01; // change of position
  private double vposition = 0.5; // constant to set the scale of changing position
  private int detectingRange = 5; //deadzone
 

  public SetLimelightPosition(Limelight limelight, LimelightServo servo) {
    this.servo = servo;
    this.limelight = limelight;
    addRequirements(this.servo, this.limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    servo.setLimelight(1.0);
    Timer.delay(0.8);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {//this is looping until completion
    isTarget = limelight.isTargetDetected(); //now your getting whether or not you have a target acquired

    if(!isTarget)
      {
        servo.setLimelight(servo.getServoValue()- dposition);//subtracts the a set value from the current servo
      } 
    if(isTarget && limelight.getTX()>0)
      {
        servo.setLimelight(servo.getServoValue()+ dposition);//adds a set value to the current servo value
      } 
    else if(limelight.getTX()<0)
      {
        servo.setLimelight(servo.getServoValue()- vposition*dposition); //moves to the right in case of overshooting
      } 
    
    SmartDashboard.putNumber("servoPosition", servo.getServoValue());
    SmartDashboard.putBoolean("isTargeted", isTarget);
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(isTarget&& limelight.getTX()< detectingRange && limelight.getTX() >-detectingRange ) //checks whether or not the limelight is centered
      return true;
    
      return false;
    
  }

}
