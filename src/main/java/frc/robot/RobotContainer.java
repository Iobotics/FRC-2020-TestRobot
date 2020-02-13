/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.SetLimelightPosition;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.LimelightServo;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.Auto;
import frc.robot.commands.HopperBallDetector;
import frc.robot.subsystems.ArticulatingHood;
import frc.robot.subsystems.ControlWheelSpinner;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.LEDStrip;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.LEDStrip.LEDColor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Limelight limelight = new Limelight();
  private final LimelightServo limelightServo = new LimelightServo();
  private final Drivetrain drivetrain = new Drivetrain();
  private final ControlWheelSpinner controlWheelSpinner = new ControlWheelSpinner();
  private final Intake intake = new Intake();
  private final IntakeArm intakeArm = new IntakeArm();
  private final Shooter shooter = new Shooter();
  private final ArticulatingHood articulatingHood = new ArticulatingHood();
  private final Lift lift = new Lift();
  private final AHRS gyro = new AHRS();
  private final Hopper hopper = new Hopper();
  private final LEDStrip ledStrip = new LEDStrip();

  private final Joystick joystick1 = new Joystick(OIConstants.kJoystick1);
  private final Joystick joystick2 = new Joystick(OIConstants.kJoystick2);

  private final XboxController xboxController = new XboxController(OIConstants.kXboxController);
  public double getGyro(){
    return gyro.getAngle();
  }
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    limelight.setDefaultCommand
    (new RunCommand(() -> limelight.printValues(), limelight));

    //limelightServo.setDefaultCommand(new SetLimelightPosition(limelight, limelightServo));
    
    shooter.setDefaultCommand(
      new RunCommand(() -> SmartDashboard.putNumber("Shooter RPM", shooter.setPower(0)), shooter));
    drivetrain.setDefaultCommand
      (new RunCommand(() -> drivetrain.setTank(-joystick1.getY(), joystick2.getY()), drivetrain));
    intake.setDefaultCommand(new RunCommand(() -> SmartDashboard.putNumber("Intake Velocity", intake.intakeVelocity()), intake));
    articulatingHood.setDefaultCommand(
      new RunCommand(() -> articulatingHood.setHoodPosition(), articulatingHood));
    //controlWheelSpinner.setDefaultCommand(new RunCommand(() -> controlWheelSpinner.spin(joystick1.getX()), controlWheelSpinner));
    hopper.setDefaultCommand(new ParallelCommandGroup(
      new HopperBallDetector(hopper),
      new RunCommand(() -> SmartDashboard.putBoolean("Hopper Full?", hopper.getOuttakeSensorValue()), hopper)
    ));
    ledStrip.setDefaultCommand(
      new RunCommand(() -> ledStrip.setColor(LEDColor.Green), ledStrip));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(joystick1, 1).whenPressed(
      new SetLimelightPosition(limelight, limelightServo));
          
    new JoystickButton(joystick1, OIConstants.kSpinWheel).whileHeld(
      new StartEndCommand(
        () -> controlWheelSpinner.spin(0.5), 
        () -> controlWheelSpinner.spin(0), controlWheelSpinner));
        
    new JoystickButton(joystick1, OIConstants.kRunIntake).whileHeld(
      new StartEndCommand(
        () -> intake.setIntake((joystick1.getZ() + 1)/2),
        () -> intake.setIntake(0), intake));
    new JoystickButton(joystick1, OIConstants.kToggleIntakeArm).whileHeld(
      new RunCommand(
        () -> intakeArm.toggleButton(), intakeArm));

    new JoystickButton(xboxController, OIConstants.kRunShooter).whileHeld(
      new RunCommand(
        () -> SmartDashboard.putNumber("Shooter RPM", shooter.setVelocity(SmartDashboard.getNumber("Shooter Output", 0))), shooter));

    new JoystickButton(joystick2, OIConstants.kSetLift).whileHeld(
      new StartEndCommand(
        () -> lift.setLift(joystick2.getZ()),
        () -> lift.setLift(0), lift));
        
    new JoystickButton(joystick1, OIConstants.kPositionHood)
      .whenPressed(new RunCommand(
      () -> articulatingHood.setHoodSetPoint(SmartDashboard.getNumber("Hood Setpoint", 90)), articulatingHood));

    new JoystickButton(joystick2, OIConstants.kRunHopper).whileHeld(
      new RunCommand(() -> hopper.setFrontPower(.2)));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new Auto(gyro, 90.0, drivetrain);
  } 
}
