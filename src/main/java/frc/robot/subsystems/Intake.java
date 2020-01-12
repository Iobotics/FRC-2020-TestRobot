/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.RobotMap;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
  */
  private final CANSparkMax intake;

  public Intake() {
    intake = new CANSparkMax(RobotMap.kIntake, MotorType.kBrushless);
  }

  public void setIntake(double power){
    intake.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
