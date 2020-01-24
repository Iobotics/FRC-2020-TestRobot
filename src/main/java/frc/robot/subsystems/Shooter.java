/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

  private final TalonSRX leftShooter;
  private final TalonSRX rightShooter;
  private final CANSparkMax articulatingHood;
  private final CANPIDController articulatingHoodController;
  private final CANEncoder articulatingHoodEncoder;

  public Shooter() {

    leftShooter = new TalonSRX(Constants.RobotMap.kLeftShooter);
    rightShooter = new TalonSRX(Constants.RobotMap.kRightShooter);

    articulatingHood = new CANSparkMax(Constants.RobotMap.kArticulatingHood, MotorType.kBrushless);
    articulatingHoodController = new CANPIDController(articulatingHood);
    articulatingHoodEncoder = new CANEncoder(articulatingHood);

    leftShooter.setInverted(true);

  }

  public int setPower(double power) {
    
    rightShooter.set(ControlMode.PercentOutput, power);
    leftShooter.set(ControlMode.Follower, rightShooter.getDeviceID());
    int sensorVelocity = rightShooter.getSelectedSensorVelocity();
    return sensorVelocity * 600 /8192;
  }

  public int getRPM(){
    return rightShooter.getSelectedSensorVelocity() * 600 / 2048;
  }

  public void setHoodPosition(double position){
    
    articulatingHoodController.setSmartMotionMaxAccel(15, 0);
    articulatingHoodController.setSmartMotionMaxVelocity(60, 0);

    articulatingHoodController.setReference(position, ControlType.kSmartMotion);
  }

  public double getHoodPosition(){
    return articulatingHoodEncoder.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
