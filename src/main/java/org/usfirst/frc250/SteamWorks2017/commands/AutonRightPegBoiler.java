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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc250.SteamWorks2017.Robot;

/**
 *
 */
public class AutonRightPegBoiler extends CommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonRightPegBoiler() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
        if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
            System.out.println("Red Alliance");
            addSequential(new TimedDriveField(0, "Auton Red Right Seg1 Speed",
                    "Auton Red Right Seg1 Time"));
            addSequential(new WaitCommand(0.5));
            addSequential(new DriveToAngle(-150)); // Update to Pref
            addSequential(new WaitCommand(0.5));
            addSequential(new TimedDriveRobot("Auton Red Right Seg2 Speed", 0,"Auton Red Right Seg2 Time"));
            addSequential(new WaitCommand(Robot.prefs2.getDouble("Auton Red Right Pre Gear Wait")));
            addSequential(new GearOpen());
            addSequential(new WaitCommand(Robot.prefs2.getDouble("Auton Red Right Post Gear Wait")));
            addSequential(new TimedDriveRobot("Auton Red Right Backward Speed",0,
                    "Auton Red Right Backward Time"));
            addSequential(new GearClose());
            addSequential(new DriveToAngle(-45));
            addSequential(new TimedDriveRobot(0,"Auton Red Boiler Drive Speed","Auton Red Boiler Drive Time"));
            addSequential(new WaitCommand(Robot.prefs2.getDouble("Auton Red Boiler Wait Time")));
            addSequential(new OutputStart());
        }
        else
        {
            System.out.println("Blue Alliance");
            addSequential(new TimedDriveField(0, "Auton Blue Right Seg1 Speed",
                    "Auton Blue Right Seg1 Time"));
            addSequential(new WaitCommand(0.5));
            addSequential(new DriveToAngle(-150)); // Update to Pref
            addSequential(new WaitCommand(0.5));
            addSequential(new TimedDriveRobot("Auton Blue Right Seg2 Speed", 0,"Auton Blue Right Seg2 Time"));
            addSequential(new WaitCommand(Robot.prefs2.getDouble("Auton Blue Right Pre Gear Wait")));
            addSequential(new GearOpen());
            addSequential(new WaitCommand(Robot.prefs2.getDouble("Auton Blue Right Post Gear Wait")));
            addSequential(new TimedDriveRobot("Auton Blue Right Backward Speed",0,
                    "Auton Blue Right Backward Time"));
            addSequential(new GearClose());
            addSequential(new DriveToAngle(0));
        }
    } 
}