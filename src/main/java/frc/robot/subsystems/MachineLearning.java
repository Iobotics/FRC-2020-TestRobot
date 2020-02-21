/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MachineLearning extends SubsystemBase {
  /**
   * Creates a new Machinelearning.
   */

   private NetworkTable table;
   private NetworkTableInstance inst;
   private NetworkTableEntry boxes;
   private NetworkTableEntry number;
   private NetworkTableEntry classes;
   private double[] defaultValue = new double[] {0,0,0,0,0,0,0,0,0,0,0,0,0};

  public MachineLearning() {
    inst = NetworkTableInstance.getDefault(); //setting up network tables for limelight
    table = inst.getTable("ML");
    boxes = table.getEntry("boxes");
    number = table.getEntry("nb_objects");
    classes = table.getEntry("object_classes");
    inst.startClientTeam(2439);
    inst.startDSClient();

  }

 public boolean isPowerCellDetected()
 {
   if(this.getTargetNumber()> 0)
   {
     return true;
   }
   return false;
 }


 public double getTargetNumber()
 {
   return number.getDouble(0.0);
 }

 public double[] getCoordinate()
 {
   for(int i=0; i<this.getTargetNumber()*4; i++)
   {
    if(this.getTargetNumber()==0){
    return defaultValue;
    }
    defaultValue[i]=boxes.getDoubleArray(defaultValue)[i];
   }

   //defaultValue = boxes.getDoubleArray(defaultValue);

   return defaultValue;
 }




  public void printValues()
 {
   // double[] xbox = this.getCoordinate();
    if(this.getTargetNumber()!=0.0){

  SmartDashboard.putNumber("DetectedNumber", this.getTargetNumber());
  
  SmartDashboard.putNumber("NearestId", this.findNearest());       
 }
}

  public double findNearest()
  {
  
    int nearestPC =0;
    
   if(this.getTargetNumber()==0.0)
   {
     return 0;
   }
   //else if(this.getTargetNumber()==1)
    
      return this.getCoordinate()[0]+this.getCoordinate()[2];
    
  } 
    /*
    else{
    for(int i=0; i<4*this.getTargetNumber(); i+=4){
      nearestPC=this.compareArea(nearestPC, i);
    }
  }
    return this.getCoordinate()[nearestPC]+this.getCoordinate()[nearestPC+2];
  }*/
 /* public int compareArea(double a, double b)
  {
    double[] coordinateTable = this.getCoordinate();
    if(this.calDsitance(coordinateTable[(int)a], coordinateTable[(int)a+1], coordinateTable[(int)a+2], coordinateTable[(int)a+3])>=
    this.calDsitance(coordinateTable[(int)b], coordinateTable[(int)b+1], coordinateTable[(int)b+2], coordinateTable[(int)b+3])){
    return (int)a;
    }
    return (int)b;
  }
  */
  public double targetPW(){
    double coordinate1 = 0.0;
    if(this.getTargetNumber()==1)
    coordinate1 = this.getCoordinate()[0]+this.getCoordinate()[2];

    return coordinate1;
  }
  public double calDsitance(double x1, double y1, double x2, double y2)
  {
    return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
  }
  

  @Override
  public void periodic() {
    
    
    // This method will be called once per scheduler run
  }
}
