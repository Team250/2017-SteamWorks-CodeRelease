// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc250.SteamWorks2017.commands;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc250.SteamWorks2017.Robot;
import org.usfirst.frc250.SteamWorks2017.RobotMap;
import org.usfirst.frc250.SteamWorks2017.Utilities;

/**
 *
 */
public class Drive extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Drive() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Init Drive");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Get and clean up driver joystick input
        double driverX = Utilities.joystickMapFunction(Utilities.joystickDeadband(Robot.oi.getDriverJoy().getX()));
        double driverY = Utilities.joystickMapFunction(Utilities.joystickDeadband(Robot.oi.getDriverJoy().getY()));
        double driverW = Utilities.joystickMapFunction(Utilities.joystickDeadband(Robot.oi.getDriverJoy().getTwist()));

        // Get and clean up driver gamepad input
        int manipPOV = Robot.oi.getManipJoy().getPOV();
        double manipX = 0, manipY = 0, manipW = 0;
        switch (manipPOV){
            case -1: manipX = 0; manipY = 0; //POV Not Pressed
                break;
            case 0: manipX = 0; manipY = -Robot.prefs2.getDouble("Teleop Manip Speed Forward");
                break;
            case 90: manipX = Robot.prefs2.getDouble("Teleop Manip Speed Side"); manipY = 0;
                break;
            case 180: manipX = 0; manipY = Robot.prefs2.getDouble("Teleop Manip Speed Backward");
                break;
            case 270: manipX = -Robot.prefs2.getDouble("Teleop Manip Speed Side"); manipY = 0;
                break;
        }
        if(Robot.oi.getManipJoy().getRawButton(5))
        {
            manipW = manipW - Robot.prefs2.getDouble("Teleop Manip Speed Turn");
        }
        if(Robot.oi.getManipJoy().getRawButton(6))
        {
            manipW = manipW + Robot.prefs2.getDouble("Teleop Manip Speed Turn");
        }

        // Allow both Driver and Manipulator to "Drive" the robot
        // If the Driver is providing input, they override the Manipulator
        // Driver input ot processed as field-oriented, manipulators is robot-oriented around the side gear mechanism
        if (driverX != 0 || driverY != 0 || driverW != 0) {
             // Some driver input, they controls
            Robot.driveTrain.driveCartesianCorrected(driverX, driverY, driverW);
            if (RobotMap.kDRIVE_DEBUG) {
                System.out.println(String.format("Drive - Driver X:" + driverX + " Y:" + driverY + " W:" + driverW));
            }
        } else {
            // No driver input, Manipulator controls
             Robot.driveTrain.driveCartesian(manipX, manipY, manipW, -90);
            if (RobotMap.kDRIVE_DEBUG) {
                System.out.println(String.format("Drive - Manip POV: " + manipPOV + " X:" + manipX + " Y:" + manipY + " W:" + manipW));
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("End Drive");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("Interupt Drive");
    }
}
