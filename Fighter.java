
// Package declaration
package POO;

import java.util.Date;
import java.util.Arrays;

// Fighter class that inherits from Aircraft
public class Fighter extends Aircraft {
    // Static counter to track the number of Fighter aircraft created
    private static int numberOfFighterCreated = 0;

    // Specific attribute for this subclass: the type of fighter
    private FighterType fighterType;

    // A simple array to store missions instead of using ArrayList
    private Mission[] missions;

    /**
     * Constructor for Fighter class
     *
     * @param manufacture          Manufacturer of the aircraft
     * @param incorporationDate    Date the aircraft was incorporated into service
     * @param lastInspectionDate   Date of the last inspection
     * @param lastMaintenanceDate  Date of the last maintenance
     * @param nextMaintenanceDate  Date of the next scheduled maintenance
     * @param wingspan             Wingspan of the aircraft
     * @param weight               Weight of the aircraft
     * @param maxSpeed             Maximum speed the aircraft can reach
     * @param fighterType          Type of the fighter (e.g., AIR_SUPERIORITY, MULTIROLE, etc.)
     */
    public Fighter(String manufacture, Date incorporationDate,
                   Date lastInspectionDate, Date lastMaintenanceDate, Date nextMaintenanceDate,
                   float wingspan, float weight, float maxSpeed, FighterType fighterType) {

        // Calls the constructor of the superclass Aircraft
        super(manufacture, incorporationDate, lastInspectionDate, lastMaintenanceDate,
                nextMaintenanceDate, wingspan, weight, maxSpeed);
        this.fighterType = fighterType; // Assigns fighter type
        this.missions = new Mission[0]; // Initializes mission array

        numberOfFighterCreated++;

        generateFighterID();
    }

    private void generateFighterID(){
        idAircraft = "F" + getIntPartAircraftID();
    }

    /**
     * Getter method to obtain the type of the fighter
     *
     * @return FighterType enum indicating the fighter category
     */
    public FighterType getFighterType() {
        return fighterType;
    }

    /**
     * Overridden method from Aircraft class
     * Returns the type of aircraft as a string
     *
     * @return "FIGHTER"
     */
    @Override
    public String getType() {
        return "FIGHTER";
    }

    public void setType(String type) {
        try {
            this.fighterType = FighterType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid fighter type: " + type +
                    ". Valid types are: " + Arrays.toString(FighterType.values()));
        }
    }

    public void setType(FighterType type) {
        this.fighterType = type;
    }


    /**
     * Static method to get the total number of Fighter instances created
     *
     * @return Number of fighter aircrafts created
     */
    public int getnumberOfFighterCreated() {
        return numberOfFighterCreated;
    }

    /**
     * Adds a mission to the fighter's mission array (instead of using ArrayList)
     *
     * @param mission The mission to be added
     */
    public void addMission(Mission mission) {
        // Create a new array with one more slot
        Mission[] newMissions = new Mission[missions.length + 1];
        // Copy existing missions to the new array
        System.arraycopy(missions, 0, newMissions, 0, missions.length);
        // Add the new mission
        newMissions[missions.length] = mission;
        // Replace the old array with the new one
        missions = newMissions;
    }

    /**
     * Returns all missions of the fighter aircraft.
     *
     * @return Array of missions
     */
    public Mission[] getMissions() {
        return missions;
    }
}
