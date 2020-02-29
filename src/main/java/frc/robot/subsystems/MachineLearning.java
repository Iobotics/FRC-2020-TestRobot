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
import java.lang.Math;

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
   private double[] coornidates = new double[]{0,0,0,0,0,0,0,0};
   private double error = 0; 
   private double y = 0;   
   private double Sx = 0;


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
    return boxes.getDoubleArray(defaultValue);
}

  


  public void printValues()
 {
   // double[] xbox = this.getCoordinate();
   if(this.getCoordinate().length!=0)
   {
  coornidates=this.getCoordinate();
    }
    int j = this.findNearest();

    double x1 = coornidates[0]-180;
    double y1 = 280-coornidates[1];
    double x2 = coornidates[2]-180;
    double y2 = 280-coornidates[3];
    double x = 0.5*(x1+x2);
    y = 0.5*(y1+y2);
    Sx = (1.248*(y2/260)+1)*x;
   
  SmartDashboard.putNumber("DetectedNumber", this.getTargetNumber());
  SmartDashboard.putNumber("x1", x1); 
  SmartDashboard.putNumber("y1", y1);
  SmartDashboard.putNumber("x2", x2);
  SmartDashboard.putNumber("y2", y2);
  SmartDashboard.putNumber("x", 0.5*(x1+x2)); 
  SmartDashboard.putNumber("y", 0.5*(y1+y2));
  SmartDashboard.putNumber("Scaled X",Sx);



  
 // Timer.delay(0.5);


   }
  public int findNearest()
  {
    int nearestPC=0;
    for(int i=0; i<this.getCoordinate().length/4; i++)
    {
      if(this.findDistance(i)>this.findDistance(nearestPC))
      nearestPC = i;
    }
    return nearestPC;
  }
  public double findDistance(int i)
  {
    if (i==0)
    return -200;
    if(this.getCoordinate().length!=0)
   {
  coornidates=this.getCoordinate();
    }
    double x1 = coornidates[i]-180;
    double y1 = 280-coornidates[i+1];
    double x2 = coornidates[i+2]-180;
    double y2 = 280-coornidates[i+3];
    double x = 0.5*(x1+x2);
    double y = 0.5*(y1+y2);
    double Sx = (1.248*(y2/260)+1)*x;
    return y;
  }


 
 
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
  public double giveError()
  {
    if(this.getTargetNumber()>0)
    error = Math.atan(Sx/y);

    return error;

    
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
