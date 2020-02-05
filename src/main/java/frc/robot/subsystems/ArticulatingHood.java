/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ArticulatingHoodConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class ArticulatingHood extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private final TalonSRX articulatingHood;
  //private final PIDController articulatingHoodController;
  private double setPoint = 370;
  private double lastValue;
  double addedValue = 0;

  public ArticulatingHood(){
    articulatingHood = new TalonSRX(Constants.RobotMap.kArticulatingHood);
    articulatingHood.config_kP(1, ArticulatingHoodConstants.kP);
    articulatingHood.config_kI(1, ArticulatingHoodConstants.kI);
    articulatingHood.config_kD(1, ArticulatingHoodConstants.kD);
    articulatingHood.config_kF(1, ArticulatingHoodConstants.kF);

    articulatingHood.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    articulatingHood.configFeedbackNotContinuous(false, 0);
    articulatingHood.setSensorPhase(false);

    lastValue = articulatingHood.getSelectedSensorPosition();

    articulatingHood.setNeutralMode(NeutralMode.Brake);
    //articulatingHood.setInverted(false);
    articulatingHood.set(ControlMode.PercentOutput, 0);
  }
  
  public void setHoodPosition(){
    double error = articulatingHood.getSelectedSensorPosition() - setPoint;
    if(Math.abs(articulatingHood.getSelectedSensorPosition() - setPoint) > 1){
      if(lastValue == articulatingHood.getSelectedSensorPosition()){
        addedValue += (Math.abs(error)/error * .5 * (Math.pow(Math.abs(error) / (ArticulatingHoodConstants.hoodMaximum - ArticulatingHoodConstants.hoodMinimum), .7))) + addedValue < 1.00 ? .01 : 0;
      } else {
        addedValue -= addedValue > 0 ? .01 : 0;
        lastValue = articulatingHood.getSelectedSensorPosition();
      }
      articulatingHood.set(ControlMode.PercentOutput, (Math.abs(error)/error * .5 * (Math.pow(Math.abs(error) / (ArticulatingHoodConstants.hoodMaximum - ArticulatingHoodConstants.hoodMinimum), .7))) + addedValue);
    }
    else {
      addedValue = 0;
    }
    //articulatingHood.set(ControlMode.Position, setPoint);
    SmartDashboard.putNumber("Hood percent", articulatingHood.getMotorOutputPercent());
    SmartDashboard.putNumber("Hood pos", articulatingHood.getSelectedSensorPosition());

  }

  public double getHoodPosition(){
    return articulatingHood.getSelectedSensorPosition();
  }

  public void setHoodSetPoint(double position){
    setPoint = position;
  }

  public void setPower(double power){
    articulatingHood.set(ControlMode.PercentOutput, power);
    SmartDashboard.putNumber("Hood Position", articulatingHood.getSelectedSensorPosition());
  }

}
