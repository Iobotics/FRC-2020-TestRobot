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

  private final TalonSRX leftShooter;
  private final TalonSRX rightShooter;
  public Shooter() {

    leftShooter = new TalonSRX(Constants.RobotMap.kLeftShooter);
    rightShooter = new TalonSRX(Constants.RobotMap.kRightShooter);

  }

  public void setPower(double power) {
    
    leftShooter.set(ControlMode.Velocity, power);
    rightShooter.set(ControlMode.Velocity, power);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
