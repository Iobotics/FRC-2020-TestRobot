/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeArmConstants;
import frc.robot.Constants.RobotMap;

public class IntakeArm extends SubsystemBase {
  /**
   * Creates a new ShooterArm.
   */

  private TalonSRX arm;

  private double potentUp;
  private double potentDown;

  private boolean isUp = false;

  public IntakeArm() {
    arm = new TalonSRX(RobotMap.kIntakeArm);

    arm.config_kP(0, IntakeArmConstants.kP);
    arm.config_kI(0, IntakeArmConstants.kI);
    arm.config_kD(0, IntakeArmConstants.kD);
    arm.config_kF(0, IntakeArmConstants.kF);

    arm.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    arm.configFeedbackNotContinuous(true, 0);

    arm.setNeutralMode(NeutralMode.Brake);

    potentUp = IntakeArmConstants.kPotentUp;
    potentDown = IntakeArmConstants.kPotentDown;
  }
  
  /**
   * NOTE: This function only works when the arm has been moved at least once to a certain position due to Talons not willing to commmunicate encoder telemetry
   * @return True- The arm is in the pre-set up position; False - Pre-set down position 
   */
  public boolean isUp () {
    return isUp;
  }

  /**
   * Puts the Intake Arm in the pre-set down position
   */
  public void setDown() {
    arm.set(ControlMode.Position, potentDown);
    isUp = false;
  }
   /**
   * Manually set the percent output
   * @param percent % output (-1 -> 1)
   */
  public void setManual(double percent) {
    arm.set(ControlMode.PercentOutput, percent);
  }
  /**
   * Puts the Intake Arm in the pre-set up position
   */
  public void setUp() {
    arm.set(ControlMode.Position, potentUp);
    isUp = true;
  }

  /**
   * Checks the location of the arm and moves in between the two positions accordingly (for one button teleop feature)
   */
  public void toggleButton() {
    if (!isUp()) {
      setUp();
    } else {
      setDown();
    }
  }
}
