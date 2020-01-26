/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.RobotMap;

public class ControlWheel extends SubsystemBase {
  /**
   * Creates a new ControlWheel.
   */
  private CANSparkMax controlWheelSpinner = null;

  private double currentVelocity = 0;
  private double targetRotations = 0;
  private double targetTime = 0;

  private Timer timer;
  public ControlWheel() {
    controlWheelSpinner = new CANSparkMax(RobotMap.kControlPanelSpinner, MotorType.kBrushless);
  }

  public void setSpeed(double speed) {
    controlWheelSpinner.set(speed);
  }

  public void stop() {
    controlWheelSpinner.stopMotor();
  }

  public void setRotations(double rotations) {
    controlWheelSpinner.set(0.25);
    currentVelocity = controlWheelSpinner.getEncoder(EncoderType.kHallSensor, 42).getVelocity();
    timer.start();
    targetTime = (rotations/currentVelocity)*60;
  }

  public boolean checkRotations() {
    if (targetTime <= timer.get()) {
      timer.stop();
      timer.reset();
      return true;
    }
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
