/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDStripConstants;
import frc.robot.Constants.RobotMap;

public class LEDStrip extends SubsystemBase {
  /**
   * Creates a new LEDStrip.
   */

  private I2C arduino;

  public enum LEDColor {
    Red, Blue, Green
  }

  public LEDStrip() {
    arduino = new I2C(Port.kMXP, RobotMap.kArduino);
  }

  /**
   * Write a pre-set value to the arduino 
   * @param color The pre-set color
   */
  public void setColor (LEDColor color) {
    if (color == LEDColor.Red) {
      arduino.write(0, LEDStripConstants.kMaxRed);
      arduino.write(0, 0);
      arduino.write(0, 0);
    } else if (color == LEDColor.Blue) {
      arduino.write(0, 0);
      arduino.write(0, LEDStripConstants.kMaxBlue);
      arduino.write(0, 0);
    } else if (color == LEDColor.Green) {
      arduino.write(0, 0);
      arduino.write(0, 0);
      arduino.write(0, LEDStripConstants.kMaxGreen);
    }
  }

  /**
   * Write a custom RGB value to the LED Strip
   * @param r Red value (0-255)
   * @param b Blue value (0-255)
   * @param g Green value (0-255)
   */
  public void setCustomColor (int r, int b, int g) {
      arduino.write(0, r);
      arduino.write(0, b);
      arduino.write(0, g);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
