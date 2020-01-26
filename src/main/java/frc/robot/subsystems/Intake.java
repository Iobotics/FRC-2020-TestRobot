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
  private final TalonSRX intake;


  public Intake() {
    intake = new TalonSRX(RobotMap.kIntake);
    intake.config_kF(0,0,0);
    /*               ^ ^ ^
                     | | | Timeout in Ms
                     | | The value given to the variable after "k"
                     | Slot id */  
		intake.config_kP(0,0,0);
		intake.config_kI(0,0,0);
		intake.config_kD(0,0,0);
  }

  public void setIntake(double power){
    intake.set(ControlMode.PercentOutput, power);
  }

  public int intakeVelocity(){
    return intake.getSelectedSensorVelocity();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
