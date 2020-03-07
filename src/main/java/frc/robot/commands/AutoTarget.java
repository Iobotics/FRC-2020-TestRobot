/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.MachineLearning;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoTarget extends PIDCommand {
  /**
   * Creates a new AutoTarget.
   */
  static PIDController PID;
  Timer time = new Timer();
  AHRS gyro;
  MachineLearning machineLearning = new MachineLearning();
  double angleError = machineLearning.giveError();
  public static int counter =0;
  
  

  public AutoTarget(AHRS gyro, double angle, double initialAngle, Drivetrain drive, MachineLearning machineLearning) {
    super(
        // The controller that the command will use
        PID = new PIDController(0.0135 * 0.8, 0, 0.0135*1.4/10),
        //(0.0135 * 0.6, 1.2 * (0.0135 / 1.4), (0.0135 * 1.4 * 3)/40),
        
        //(0.0135 * 0.45, 0.54*0.0135 / 1.4, 0),
        // This should return the measurement
        ()-> gyro.getAngle(),
        // This should return the setpoint (can also be a constant)
        ()-> RobotContainer.angleErrors,
        // This uses the output
        output -> {;
          drive.setTank(output+output/Math.abs(output)*0.124,output+output/Math.abs(output)*0.124);
          // Math.abs(RobotContainer.angleErrors)/RobotContainer.angleErrors*0.1255,output+RobotContainer.angleErrors/Math.abs(RobotContainer.angleErrors)*0.1255
          // Use the output here
        });
        this.gyro = gyro;
        addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() { //return true if PW is at the center
   

   counter++;
    SmartDashboard.putNumber("pidError", angleError);
    SmartDashboard.putNumber("pidErrorVelocity", angleError);

/*Math.abs(PID.getPositionError()) < 5*/ 
    if (((Math.abs(PID.getPositionError()) <1 ) && (Math.abs(PID.getVelocityError())< 0.1))){ 
      return true;
      
    }//else if((Math.abs(PID.getVelocityError())< 0.5) &&(machineLearning.getCoordinate().length/4==0)) {
     // (int)(2/5*Math.abs(RobotContainer.angleErrors)+18)
    //}
    return false;
  }
}

