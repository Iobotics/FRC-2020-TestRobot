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

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class ArticulatingHood extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private final CANSparkMax articulatingHood;
  //private final PIDController articulatingHoodController;
  private final AnalogPotentiometer articulatingHoodEncoder;
  private double setPoint = .11;
  private double power = 0;
  

  public ArticulatingHood(){
    articulatingHood = new CANSparkMax(Constants.RobotMap.kArticulatingHood, MotorType.kBrushless);
    /*articulatingHoodController = new PIDController(ArticulatingHoodConstants.kP, ArticulatingHoodConstants.kI, ArticulatingHoodConstants.kD);*/
    articulatingHoodEncoder = new AnalogPotentiometer(2);
    //articulatingHoodController.setSetpoint(.24);
  }
  
  public void setHoodPosition(){
    //articulatingHood.set(1);
    SmartDashboard.putNumber("Hood pos", getHoodPosition());
  }

  public double getHoodPosition(){
    return articulatingHoodEncoder.get();
  }

  public void setHoodSetPoint(double position){
    //articulatingHoodController.setSetpoint(Double.max(Double.min(position, Constants.ArticulatingHoodConstants.hoodMaximum), Constants.ArticulatingHoodConstants.hoodMinimum));
    setPoint = position;
  }

  public void setPower(double power){
    articulatingHood.set(power);
    SmartDashboard.putNumber("Hood Position", articulatingHoodEncoder.get());
  }

}
