package org.usfirst.frc250.SteamWorks2017;
import edu.wpi.first.wpilibj.Preferences;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by Programmers on 3/21/2017.
 */
public class Prefs2 {

    // Doubles Hastable
    private Hashtable<String, Double> doubles = new Hashtable<String, Double>(100);
    private Hashtable<String, Integer> ints = new Hashtable<String, Integer>(100);
    private static Prefs2 _prefs;  // Instance of Self
    private Preferences prefs; // WPILIB Preferences Object

    private void initDefaults()
    {
        // Global
        ints.put("Rotation Offset", 0);

         // Auton Cross Line
        doubles.put("Auton Cross Line Forward Time", 2.0);
        doubles.put("Auton Cross Line Forward Speed", -0.75);

        // Auton Center Peg
        doubles.put("Auton Center Peg Forward Time", 2.0);
        doubles.put("Auton Center Peg Forward Speed", -0.75);
        doubles.put("Auton Center Peg Pre Gear Wait", 0.25);
        doubles.put("Auton Center Peg Post Gear Wait", 0.5);
        doubles.put("Auton Center Peg Backward Time", 0.7);
        doubles.put("Auton Center Peg Backward Speed", 0.75);

        // Auton Red Left Peg
        doubles.put("Auton Red Left Seg1 Time", 1.2);
        doubles.put("Auton Red Left Seg1 Speed", -0.7);
        doubles.put("Auton Red Left Seg2 Time", 0.71);
        doubles.put("Auton Red Left Seg2 Speed", 0.5);
        doubles.put("Auton Red Left Pre Gear Wait", 0.5);
        doubles.put("Auton Red Left Post Gear Wait", 0.5);
        doubles.put("Auton Red Left Backward Time", 0.7);
        doubles.put("Auton Red Left Backward Speed", -0.75);
        doubles.put("Auton Red Left Down Field Speed", -0.7);
        doubles.put("Auton Red Left Down Field Time", 2.1);

        // Auton Red Right Peg
        doubles.put("Auton Red Right Seg1 Time", 1.25);
        doubles.put("Auton Red Right Seg1 Speed", -0.7);
        doubles.put("Auton Red Right Seg2 Time", 1.3);
        doubles.put("Auton Red Right Seg2 Speed", 0.5);
        doubles.put("Auton Red Right Pre Gear Wait", 0.5);
        doubles.put("Auton Red Right Post Gear Wait", 0.5);
        doubles.put("Auton Red Right Backward Time", 0.7);
        doubles.put("Auton Red Right Backward Speed", -0.75);
        doubles.put("Auton Red Right Down Field Speed", -0.7);
        doubles.put("Auton Red Right Down Field Time", 2.1);

        //Auton Red Shoot
        doubles.put("Auton Red Boiler Drive Time", 1.25);
        doubles.put("Auton Red Boiler Drive Speed", 0.7);
        doubles.put("Auton Red Boiler Wait Time", 0.25);

        //Auton Blue Shoot
        doubles.put("Auton Blue Boiler Drive Time", 1.25);
        doubles.put("Auton Blue Boiler Drive Speed", 0.7);
        doubles.put("Auton Blue Boiler Wait Time", 0.25);

        // Auton Blue Left Peg
        doubles.put("Auton Blue Left Seg1 Time", 1.2);
        doubles.put("Auton Blue Left Seg1 Speed", -0.7);
        doubles.put("Auton Blue Left Seg2 Time", 0.71);
        doubles.put("Auton Blue Left Seg2 Speed", 0.5);
        doubles.put("Auton Blue Left Pre Gear Wait", 0.5);
        doubles.put("Auton Blue Left Post Gear Wait", 0.5);
        doubles.put("Auton Blue Left Backward Time", 0.7);
        doubles.put("Auton Blue Left Backward Speed", -0.75);
        doubles.put("Auton Blue Left Down Field Speed", -0.7);
        doubles.put("Auton Blue Left Down Field Time", 2.1);

        // Auton Blue Right Peg
        doubles.put("Auton Blue Right Seg1 Time", 1.25);
        doubles.put("Auton Blue Right Seg1 Speed", -0.7);
        doubles.put("Auton Blue Right Seg2 Time", 1.3);
        doubles.put("Auton Blue Right Seg2 Speed", 0.5);
        doubles.put("Auton Blue Right Pre Gear Wait", 0.5);
        doubles.put("Auton Blue Right Post Gear Wait", 0.5);
        doubles.put("Auton Blue Right Backward Time", 0.7);
        doubles.put("Auton Blue Right Backward Speed", -0.75);
        doubles.put("Auton Blue Right Down Field Speed", -0.7);
        doubles.put("Auton Blue Right Down Field Time", 2.1);

        // Teleop Turn to Angle
        doubles.put("Teleop Turn To Angle", 0.0);

        // Teleop Manip Driving
        doubles.put("Teleop Manip Speed Forward", 0.5);
        doubles.put("Teleop Manip Speed Backward", 0.75);
        doubles.put("Teleop Manip Speed Side", 0.30);
        doubles.put("Teleop Manip Speed Turn", 0.25);
    }

    public static synchronized Prefs2 getInstance() {
        if (_prefs == null) {
            _prefs = new Prefs2();
        }
        return _prefs;
    }

    public Prefs2()
    {
        prefs = Preferences.getInstance();
        initDefaults();
    }

    public double getDouble(String preferenceKey)
    {
        if(!doubles.containsKey(preferenceKey) && !prefs.getInstance().containsKey(preferenceKey))
        {
            // No Preference value set on SmartDashboard and no Default value set in this class, return 0
            System.out.println("Prefs - Key '" + preferenceKey + "' does not exist, set in SmartDashboard or " + getClass().getName());
            System.out.println("doubles:" + doubles.containsKey(preferenceKey) + " prefs:" + prefs.getInstance().containsKey(preferenceKey));
            System.out.println("doubles keys:" + doubles);
            System.out.println("prefs keys:" + prefs.getKeys());

            return 0;
        }
        return prefs.getDouble(preferenceKey, doubles.get(preferenceKey));
    }

    public void printPrefs()
    {
        System.out.println("Preferences");
        // Loop thru each default double key and output value that will be returned
        Set<String> doubleKeys = doubles.keySet();
        for(String key: doubleKeys)
        {
            if(! prefs.getInstance().containsKey(key)) {
                System.out.println(" - Double '" + key + "' = " + getDouble(key) +
                        "(Pref Set: " + prefs.getInstance().containsKey(key) + ")");
            }
        }

        // Loop thru each default int key and output value that will be returned
        Set<String> intKeys = ints.keySet();
        for(String key: intKeys) {
        if (!prefs.getInstance().containsKey(key)) {
            System.out.println(" - Int '" + key + "' = " + getInt(key) +
                    "(Pref Set: " + prefs.getInstance().containsKey(key) + ")");
            }
        }
        // Loop thru each default int key and output value that will be returned
        for(String key: intKeys) {
            if (prefs.getInstance().containsKey(key)) {
                System.out.println(" - Int '" + key + "' = " + getInt(key) +
                        "(Pref Set: " + prefs.getInstance().containsKey(key) + ")");
            }
        }

        // Loop thru each default double key and output value that will be returned
        for(String key: doubleKeys)
        {
            if(prefs.getInstance().containsKey(key)) {
                System.out.println(" - Double '" + key + "' = " + getDouble(key) +
                        "(Pref Set: " + prefs.getInstance().containsKey(key) + ")");
            }
        }
    }

    public int getInt(String preferenceKey)
    {
        if(!ints.containsKey(preferenceKey) && !prefs.getInstance().containsKey(preferenceKey))
        {
            // If preference value not set on SmartDashboard and default value not set in this class, return 0
            System.out.println("Prefs - Key '" + preferenceKey + "' does not exist, set in SmartDashboard or " + getClass().getName());
            return 0;
        }
        return prefs.getInt(preferenceKey, ints.get(preferenceKey));
    }
}