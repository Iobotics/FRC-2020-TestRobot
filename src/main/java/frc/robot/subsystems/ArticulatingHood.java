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
  private double setPoint = ArticulatingHoodConstants.hoodMaximum;
  

  public ArticulatingHood(){
    articulatingHood = new TalonSRX(Constants.RobotMap.kArticulatingHood);
    articulatingHood.config_kP(0, ArticulatingHoodConstants.kP);
    articulatingHood.config_kI(0, ArticulatingHoodConstants.kI);
    articulatingHood.config_kD(0, ArticulatingHoodConstants.kD);
    articulatingHood.config_kF(0, ArticulatingHoodConstants.kF);

    articulatingHood.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    articulatingHood.configFeedbackNotContinuous(true, 0);

    articulatingHood.setNeutralMode(NeutralMode.Brake);
  }
  
  public void setHoodPosition(){
    
    articulatingHood.set(ControlMode.Position, setPoint);
    SmartDashboard.putNumber("Hood pos", getHoodPosition());

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
