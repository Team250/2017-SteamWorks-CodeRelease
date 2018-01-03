/**
 * Extends stock WPILib mecanum control with field-oriented control option(Corrected Cartesian)
 */
package org.usfirst.frc250.SteamWorks2017;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;

/**
 * @author Programmers
 *
 */
public class RoboDrive250 extends RobotDrive {

    private double targetHeading = -3000;
    private boolean isAngleCorrectionMode = false;

	/**
	 * @param leftMotorChannel
	 * @param rightMotorChannel
	 */
	public RoboDrive250(int leftMotorChannel, int rightMotorChannel) {
		super(leftMotorChannel, rightMotorChannel);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param leftMotor
	 * @param rightMotor
	 */
	public RoboDrive250(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param frontLeftMotor
	 * @param rearLeftMotor
	 * @param frontRightMotor
	 * @param rearRightMotor
	 */
	public RoboDrive250(int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param frontLeftMotor
	 * @param rearLeftMotor
	 * @param frontRightMotor
	 * @param rearRightMotor
	 */
	public RoboDrive250(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor,
			SpeedController rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		// TODO Auto-generated constructor stub
	}
	
	public void mecanumDrive_Cartesian(double x, double y, double rotation, double gyroAngle) {
		// Disable Angle Correction
        isAngleCorrectionMode = false;

	    if (!kMecanumCartesian_Reported) {
	      HAL.report(tResourceType.kResourceType_RobotDrive, getNumMotors(),
	          tInstances.kRobotDrive_MecanumCartesian);
	      kMecanumCartesian_Reported = true;
	    }
	    @SuppressWarnings("LocalVariableName")
	    double xIn = x;
	    @SuppressWarnings("LocalVariableName")
	    double yIn = y;
	    // Negate y for the joystick.
	    yIn = -yIn;
	    // Compenstate for gyro angle.
	    double[] rotated = rotateVector(xIn, yIn, gyroAngle);
	    xIn = rotated[0];
	    yIn = rotated[1];

	    double[] wheelSpeeds = new double[kMaxNumberOfMotors];
	    wheelSpeeds[MotorType.kFrontLeft.value] = xIn + yIn + rotation;
	    wheelSpeeds[MotorType.kFrontRight.value] = -xIn + yIn - rotation;
	    wheelSpeeds[MotorType.kRearLeft.value] = -xIn + yIn + rotation;
	    wheelSpeeds[MotorType.kRearRight.value] = xIn + yIn - rotation;

	    normalize(wheelSpeeds);
	    m_frontLeftMotor.set(wheelSpeeds[MotorType.kFrontLeft.value] * m_maxOutput);
	    m_frontRightMotor.set(wheelSpeeds[MotorType.kFrontRight.value] * m_maxOutput);
	    m_rearLeftMotor.set(wheelSpeeds[MotorType.kRearLeft.value] * m_maxOutput);
	    m_rearRightMotor.set(wheelSpeeds[MotorType.kRearRight.value] * m_maxOutput);

	    if (m_safetyHelper != null) {
	      m_safetyHelper.feed();
	    }
	  }

    public void mecanumDrive_Cartesian_corrected(double x, double y, double rotation, double gyroAngle) {
		// If transitioning from robot centric to field centric, set target heading
		// to the current heading to avoid uncommanded rotation
		if(isAngleCorrectionMode == false){
            targetHeading = gyroAngle;
            isAngleCorrectionMode = true;
		}
		
		// Rotate robot toward the target heading unless it's stopped
        if(rotation==0.0 && (x!= 0.0 || y!= 0.0) ){
            if (gyroAngle != targetHeading) {
                double deltaAngle = Utilities.angleDifference(targetHeading, gyroAngle);
                if (deltaAngle > 90) {
                    rotation = 1;
                } else if (deltaAngle < -90) {
                    rotation = -1;
                } else {
                    rotation = deltaAngle / 90;
                }
            }
        }

        else {targetHeading = gyroAngle;}
        if (!kMecanumCartesian_Reported) {
            HAL.report(tResourceType.kResourceType_RobotDrive, getNumMotors(),
                    tInstances.kRobotDrive_MecanumCartesian);
            kMecanumCartesian_Reported = true;
        }
        @SuppressWarnings("LocalVariableName")
        double xIn = x;
        @SuppressWarnings("LocalVariableName")
        double yIn = y;
        // Negate y for the joystick.
        yIn = -yIn;
        // Compenstate for gyro angle.
        double[] rotated = rotateVector(xIn, yIn, gyroAngle);
        xIn = rotated[0];
        yIn = rotated[1];

        double[] wheelSpeeds = new double[kMaxNumberOfMotors];
        wheelSpeeds[MotorType.kFrontLeft.value] = xIn + yIn + rotation;
        wheelSpeeds[MotorType.kFrontRight.value] = -xIn + yIn - rotation;
        wheelSpeeds[MotorType.kRearLeft.value] = -xIn + yIn + rotation;
        wheelSpeeds[MotorType.kRearRight.value] = xIn + yIn - rotation;

        normalize(wheelSpeeds);
        m_frontLeftMotor.set(wheelSpeeds[MotorType.kFrontLeft.value] * m_maxOutput);
        m_frontRightMotor.set(wheelSpeeds[MotorType.kFrontRight.value] * m_maxOutput);
        m_rearLeftMotor.set(wheelSpeeds[MotorType.kRearLeft.value] * m_maxOutput);
        m_rearRightMotor.set(wheelSpeeds[MotorType.kRearRight.value] * m_maxOutput);

        if (m_safetyHelper != null) {
            m_safetyHelper.feed();
        }
    }
}
