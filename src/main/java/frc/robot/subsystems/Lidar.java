/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;

public class Lidar extends SubsystemBase {
  /**
   * Creates a new Lidar.
   */
  private I2C lidar;

  private byte[] buffer;

  public Lidar() {
    buffer = new byte[2];
    lidar = new I2C(Port.kOnboard, 98);
  }

  @Override
  public void periodic() {
    lidar.write(0x00, 0x04);
    Timer.delay(.04);
    lidar.read(0x8f, 2, buffer);
    SmartDashboard.putNumber("Lidar", Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]));
    Timer.delay(0.005);
  }
}
