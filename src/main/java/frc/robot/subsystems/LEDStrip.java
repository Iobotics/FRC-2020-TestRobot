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

  private boolean enable = true;

  public enum LEDColor {
    Red, Blue, Green, Off
  }

  public LEDStrip() {
    arduino = new I2C(Port.kMXP, RobotMap.kArduino);
  }

  public void setEnable (boolean enableVal) {
    enable = enableVal;
  }

  /**
   * Write a pre-set value to the arduino 
   * @param color The pre-set color
   */
  public void setColor (LEDColor color) {
    if (color == LEDColor.Red) {
      setCustomColor(LEDStripConstants.kMaxRed, 0, 0);
    } else if (color == LEDColor.Blue) {
      setCustomColor(0, LEDStripConstants.kMaxBlue, 0);
    } else if (color == LEDColor.Green) {
      setCustomColor(0, 0, LEDStripConstants.kMaxGreen);
    } else if (color == LEDColor.Off) {
      setCustomColor(0, 0, 0);
    }
  }

  /**
   * Write a custom RGB value to the LED Strip
   * @param r Red value (0-255)
   * @param b Blue value (0-255)
   * @param g Green value (0-255)
   * @return If the command was executed
   */
  public boolean setCustomColor (int r, int b, int g) {
    if (enable) {
      arduino.write(0, r);
      arduino.write(0, b);
      arduino.write(0, g);
      return true;
    }
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
