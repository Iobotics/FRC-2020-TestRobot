/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class RobotMap{
        
        public static final int kLeftMaster = 1;
        public static final int kRightMaster = 4;
        public static final int kLeftSlave = 2;
        public static final int kRightSlave = 3;

        public static final int kControlPanelSpinner = 6;
        public static final int kIntake = 9;
        public static final int kLeftShooter = 7;
        public static final int kRightShooter = 8;
        public static final int kArticulatingHood = 5;
        public static final int kLift = 10;

    }

    public static final class SensorMap{
        public static final int kColorWheelLeft = 0;
        public static final int kColorWheelRight = 1;
    }

    public static final class OIConstants{
        //Joysticks
        public static final int kJoystick1 = 0;
        public static final int kJoystick2 = 1;
        public static final int kXboxController = 2;

        //Joystick 1
        public static final int kRunIntake = 1;
        public static final int kSpinWheel = 2;
        public static final int kHoodPosition = 9;

        //Joystick 2
        public static final int kSetLift = 1;

        //Xbox controller
        public static final int kRunShooter = 6;
    }

    public static final class ShooterConstants{
        public static final int kMaxVel = 36000;
        public static final double kFF = (0.75 * 1023) / kMaxVel;
    }
    public static final double kGearRatio = 10.71;
    //in meters
    public static final double kWheelDiameter = 0.2032;

    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;
}
