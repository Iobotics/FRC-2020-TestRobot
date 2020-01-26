/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeArmConstants;
import frc.robot.Constants.RobotMap;

public class IntakeArm extends SubsystemBase {
  /**
   * Creates a new ShooterArm.
   */

  private TalonSRX arm;

  public IntakeArm() {
    arm = new TalonSRX(RobotMap.kShooterArm);

    arm.config_kP(0, IntakeArmConstants.kP);
    arm.config_kI(0, IntakeArmConstants.kI);
    arm.config_kD(0, IntakeArmConstants.kD);
    arm.config_kF(0, IntakeArmConstants.kF);

    arm.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    arm.configFeedbackNotContinuous(true, 0);
  }

  //TODO: Add math to convert rotations to analog encoder ticks
  public void setRotations(double rotations) {
    double ticks = rotations;
    arm.set(ControlMode.MotionMagic, ticks);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
