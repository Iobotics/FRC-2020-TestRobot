/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.MotorConstants;

public class Drivetrain extends SubsystemBase {

  private TalonSRX leftMaster;
  private TalonSRX rightMaster;
  private TalonSRX leftSlave;
  private TalonSRX rightSlave;

  public Drivetrain() {
    leftMaster = new TalonSRX(MotorConstants.kLeftMaster);
    rightMaster =  new TalonSRX(MotorConstants.kRightMaster);
    leftSlave = new TalonSRX(MotorConstants.kLeftSlave);
    rightSlave = new TalonSRX(MotorConstants.kRightSlave);
    rightMaster.setInverted(true);
    rightSlave.setInverted(true);
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    leftMaster.config_kP(0, Constants.kP);
    leftMaster.config_kI(0, Constants.kI);
    leftMaster.config_kD(0, Constants.kD);
  }

  public void config () {
    rightMaster.setInverted(true);
    rightSlave.setInverted(true);
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);
  } 
  
  public void setTank(double leftPower, double rightPower){
    if (leftPower <= 0.3 && leftPower >= -0.3) {
      leftPower = 0;
    }
    if (rightPower <= 0.3 && rightPower >= -0.3) {
      rightPower = 0;
    }
    leftMaster.set(ControlMode.PercentOutput, leftPower);
    rightMaster.set(ControlMode.PercentOutput, rightPower);
  }

  public void motionMagic (double distance, double speed) {

    double rotations = distance/(Constants.kGearRatio*Constants.kWheelDiameter*Math.PI);
    double targetPos = rotations*speed*4096;

    rightSlave.follow(leftMaster);
    rightMaster.follow(leftMaster);
    leftMaster.setSelectedSensorPosition(0);
    leftMaster.set(ControlMode.MotionMagic, targetPos);

    while (!leftMaster.isMotionProfileFinished()) {

    }
    config();
  }

  public boolean getIsFinished(){
    return leftMaster.isMotionProfileFinished();
  }

 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
