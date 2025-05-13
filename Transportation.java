
// Package declaration
package POO;

import java.util.Date;
import java.util.Arrays;

// Fighter class that inherits from Aircraft
public class Transportation extends Aircraft {
    // Static counter to track the number of Fighter aircraft created
    private static int numberOfTransportationCreated = 0;

    // Specific attribute for this subclass: the type of fighter
    private TransportationType transportationType;

    // A simple array to store missions instead of using ArrayList
    private Mission[] missions;

    /**
     * Constructor for Fighter class
     *
     * @param idAircraft           Unique ID of the aircraft
     * @param manufacture          Manufacturer of the aircraft
     * @param incorporationDate    Date the aircraft was incorporated into service
     * @param lastInspectionDate   Date of the last inspection
     * @param lastMaintenanceDate  Date of the last maintenance
     * @param nextMaintenanceDate  Date of the next scheduled maintenance
     * @param wingspan             Wingspan of the aircraft
     * @param weight               Weight of the aircraft
     * @param maxSpeed             Maximum speed the aircraft can reach
     * @param transportationType          Type of the fighter (e.g., AIR_SUPERIORITY, MULTIROLE, etc.)
     */
    public Transportation(String idAircraft, String manufacture, Date incorporationDate,
                          Date lastInspectionDate, Date lastMaintenanceDate, Date nextMaintenanceDate,
                          float wingspan, float weight, float maxSpeed, TransportationType transportationType) {

        // Calls the constructor of the superclass Aircraft
        super(idAircraft, manufacture, incorporationDate, lastInspectionDate, lastMaintenanceDate,
                nextMaintenanceDate, wingspan, weight, maxSpeed, null);
        this.transportationType = transportationType; // Assigns fighter type
        this.missions = new Mission[0]; // Initializes mission array
        numberOfTransportationCreated++;
    }

    /**
     * Getter method to obtain the type of the fighter
     *
     * @return Transportation enum indicating the fighter category
     */
    public TransportationType getTransportationType() {
        return transportationType;
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
            this.transportationType = TransportationType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid fighter type: " + type +
                    ". Valid types are: " + Arrays.toString(TransportationType.values()));
        }
    }

    public void setType(TransportationType type) {
        this.transportationType = type;
    }

    /**
     * Overridden method to extract and return the number from the aircraft ID
     * Assumes ID is in format like "F001", "F045", etc.
     *
     * @return Integer part of the ID or 0 if conversion fails
     */
    @Override
    public Integer getNumber() {
        try {
            return Integer.parseInt(idAircraft.substring(1)); // Extracts numeric part of ID
        } catch (NumberFormatException e) {
            System.err.println("Error extracting number from aircraft ID: " + idAircraft);
            return 0;
        }
    }

    /**
     * Static method to get the total number of Fighter instances created
     *
     * @return Number of fighter aircrafts created
     */
    public static int getnumberOfTransportationCreated(){
        return numberOfTransportationCreated;
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
