/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotMap;

public class ControlWheelSpinner extends SubsystemBase {
  /**
   * Creates a new ControlWheelSpinner.
   */

  private CANSparkMax ControlWheelSpinner;

  private I2C.Port leftI2CPort;
  private I2C.Port rightI2CPort;

  private ColorSensorV3 rightSensor;
  private ColorSensorV3 leftSensor;

  public ControlWheelSpinner() {
    ControlWheelSpinner = new CANSparkMax(RobotMap.kControlPanelSpinner, MotorType.kBrushless);

    //Each Color Sensor is mapped to a port
    leftI2CPort = I2C.Port.kOnboard;
    rightI2CPort = I2C.Port.kMXP;

    rightSensor = new ColorSensorV3(rightI2CPort);
    leftSensor = new ColorSensorV3(leftI2CPort);
  }

  public void spin (double speed) {
    ControlWheelSpinner.set(speed);
  }

  /**
   * 
   * @param revolutions is how many turns the spinner makes
   */
  public void spinByEncoder (double revolutions) {
    double start = ControlWheelSpinner.getEncoder().getPosition();
    while ((ControlWheelSpinner.getEncoder().getPosition() - start) < revolutions) {
      ControlWheelSpinner.set(0.4);
    }
    ControlWheelSpinner.stopMotor();
  }

  /**
   * 
   * @return returns an array of the red green and blue values from the right sensor
   */
  public int[] getColor(){
    int[] colors = {rightSensor.getBlue(), leftSensor.getBlue()};
    return colors;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
