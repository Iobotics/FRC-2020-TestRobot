/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotMap;

public class LimelightServo extends SubsystemBase {
  /**
   * Creates a new LimelightServo.
   */
  private Servo servo;
  
  public LimelightServo() {
    servo = new Servo(RobotMap.kLimelightServo);
    servo.set(1.0);
  }

  public double getServoValue(){
    return servo.get();
  }

  public void toggleServo(){
    if(this.getServoValue() == 0.0){
      servo.set(1.0);
    }else if(this.getServoValue() == 1.0){
      servo.set(0.0);
    }
  }

  public void setLimelight(double position){
    servo.set(position);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
