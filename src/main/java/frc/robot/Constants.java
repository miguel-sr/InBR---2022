
package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public final class Constants {

    // Motors

    public static final class MotorPorts {
        // Drivetrain
        public static final int leftMotorBack = 1;
        public static final int leftMotorFront = 2;
        public static final int rightMotorFront = 5;
        public static final int rightMotorBack = 6;

        public static final int intake = 4;
        public static final int conveyor = 3;
        
        // Shooter
        public static final int mainLeftShooter = 8;
        public static final int assistLeftShooter = 9;
        public static final int assistRightShooter = 10;
        public static final int mainRightShooter = 11;
    }

    public static final class Joysticks {
        public static final int driverStick = 0;
        public static final int shooterStick = 1;
    }

    public static final class Axis {
        public static final int leftStick_X = 0;
        public static final int leftStick_Y = 1;
        public static final int rightStick_X = 4;
        public static final int rightStick_Y = 5;
        public static final int leftTrigger = 2;
        public static final int rightTrigger = 3;
    }

    public static final class Buttons {
        public static final int button_A = 1;
        public static final int button_B = 2;
        public static final int button_X = 3;
        public static final int button_Y = 4;
        public static final int leftBumper = 5;
        public static final int rightBumper = 6;
        public static final int back = 7;
        public static final int start = 8;
    }

    public static final class MotorSpeeds {
        public static double intakeSpeed = 0.5;
        public static double mainWheelSpeed = 0.3; // 0.4
        public static double assistWheelSpeed = 0.15; // 0.25
    }

    public static final class LimelightConstants {
        public static final double visionTolerance = 3;
        public static final double MaxTurnOutput = 0.6;
        public static final double Vision_KP = 0.2;

        public static final int visionOff = 1;
        public static final int visionOn = 3;
        public static final int visionCurrentPipeline = 0;
    }

    public static final class navx2Constants {
        public static final double MaxTurnOutput = 0.8;
        public static final double tolerance = 4;
        public static final double KP = 0.1;
    }
        
    public static final class Colors {
        public static final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
        public static final Color kRedTarget = new Color(0.561, 0.232, 0.114);
        public static final Color kNadaTarget = new Color(0.279, 0.479, 0.239);
    }

    public static final class stateStrings {
        public static final String off = "Off";
        public static final String on = "On";
        public static final String blue = "blue";
        public static final String red = "red";
    }


}