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
<<<<<<< HEAD
  private double position;
  private double dposition = 0.0; // change of position
  private double vposition = 0.1; // constant to set the scale of changing position
  private boolean LeftReached;
  private boolean RightReached;
  private boolean initialized;
=======
  private double dposition = 0.01; // change of position
  private double vposition = 0.5; // constant to set the scale of changing position
  private int detectingRange = 5; //deadzone
>>>>>>> dfc89360ac19123e329c8be352788f6aec40f49b
 

  public SetLimelightPosition(Limelight limelight, LimelightServo servo) {
    this.servo = servo;
    this.limelight = limelight;
    addRequirements(this.servo, this.limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
<<<<<<< HEAD
    //position = servo.getServoValue();
    SmartDashboard.putNumber("distance", 0.0);
    servo.setLimelight(1.0);
    Timer.delay(1.0);
=======
    servo.setLimelight(1.0);
    Timer.delay(0.8);
>>>>>>> dfc89360ac19123e329c8be352788f6aec40f49b
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {//this is looping until completion
    isTarget = limelight.isTargetDetected(); //now your getting whether or not you have a target acquired
<<<<<<< HEAD
    /*if(position == 1.0){
      servo.setLimelight(0.0);
    }else if(position == 0.0){
      servo.setLimelight(1.0);
    }*/
   
    if(!isTarget)
      servo.setLimelight(servo.getServoValue()-dposition);
    if(x>0)
      servo.setLimelight(servo.getServoValue()-dposition);
    else if(x<0)
      servo.setLimelight(servo.getServoValue()+0.5*dposition);
    

    
    
    
    //servo.setLimelight(position + dposition*vposition); //set the limelight position to the origin position plus the change of the position * scaler 
=======

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
    
>>>>>>> dfc89360ac19123e329c8be352788f6aec40f49b
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
<<<<<<< HEAD
    if(isTarget&& x<3 && x>-3) //checks whether or not the limelight is centered
      return true;
    
    
    //else if(servo.getServoValue()< 1.1*vposition || servo.getServoValue()> 8.9*vposition) { // check whether or not the servo reach its range of turning
=======
    if(isTarget&& limelight.getTX()< detectingRange && limelight.getTX() >-detectingRange ) //checks whether or not the limelight is centered
      return true;
    
>>>>>>> dfc89360ac19123e329c8be352788f6aec40f49b
      return false;
    
  }
}

}
