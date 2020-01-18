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

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private TalonSRX leftShooter;
  private TalonSRX rightShooter;

  public Shooter() {
    leftShooter = new TalonSRX(Constants.kLeftShooter);
    rightShooter = new TalonSRX(Constants.kRightShooter);
    rightShooter.follow(leftShooter);

    leftShooter.config_kF(0, Constants.kFShooter);
    leftShooter.config_kP(0, Constants.kPShooter);
  }

  public void setRPM (double RPM) {
    leftShooter.set(ControlMode.Velocity, 2000);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
