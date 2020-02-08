/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotMap;

/**
 * Add your docs here.
 */
public class Hopper extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final TalonSRX hopperMotorFront;
  private final TalonSRX hopperMotorBack;

  DigitalInput proximitySensorIntake;
  DigitalInput proximitySensorOuttake;

  public Hopper(){
    hopperMotorFront = new TalonSRX(RobotMap.kHopperFront);
    hopperMotorBack = new TalonSRX(RobotMap.kHopperBack);
    proximitySensorIntake = new DigitalInput(RobotMap.kHopperIntakeProximitySensor);
    proximitySensorOuttake = new DigitalInput(RobotMap.kHopperOuttakeProximitySensor);

    hopperMotorBack.setInverted(true);
    hopperMotorFront.setInverted(false);
  }

  public void setFrontPower(double power){
    hopperMotorFront.set(ControlMode.PercentOutput, power);
  }

  public boolean getIntakeSensorValue() {
    return proximitySensorIntake.get();
  }

  public boolean getOuttakeSensorValue() {
    return proximitySensorOuttake.get();
  }
}
