
// Package declaration
package POO;

import java.util.Date;

// Helicopter class that extends Aircraft
public class Helicopter extends Aircraft {

    // Static counter to keep track of how many Helicopter instances have been created
    private static int numberOfHelicopterCreated = 0;

    // Specific type of helicopter, defined by the HelicopterType enum
    private HelicopterType helicopterType;

    /**
     * Constructor for the Helicopter class
     *
     * @param idAircraft          Unique identifier of the aircraft
     * @param manufacture         Manufacturer of the helicopter
     * @param incorporationDate   Date the helicopter was added to service
     * @param lastInspectionDate  Date of the last inspection
     * @param nextInspectionDate  Date scheduled for the next inspection
     * @param lastMaintenanceDate Date of the last maintenance
     * @param nextMaintenanceDate Date scheduled for the next maintenance
     * @param wingspan            Wingspan of the helicopter
     * @param weight              Weight of the helicopter
     * @param maxSpeed            Maximum speed of the helicopter
     * @param helicopterType      Specific type of helicopter
     */
    public Helicopter(String idAircraft, String manufacture, Date incorporationDate,
                      Date lastInspectionDate, Date nextInspectionDate,
                      Date lastMaintenanceDate, Date nextMaintenanceDate,
                      float wingspan, float weight, float maxSpeed, HelicopterType helicopterType) {
        // Call to the superclass constructor (Aircraft)
        super(idAircraft, manufacture, incorporationDate, lastInspectionDate, lastMaintenanceDate,
                nextMaintenanceDate, wingspan, weight, maxSpeed, null);  // No collection is used for missions
        this.helicopterType = helicopterType;
        numberOfHelicopterCreated++; // Increment the static count when a new helicopter is created
    }

    /**
     * Returns the aircraft number.
     * This is currently a placeholder (can be adapted to extract from ID or use other logic).
     */
    @Override
    public Integer getNumber() {
        // Improved to extract number from ID if possible
        try {
            return Integer.parseInt(idAircraft.replaceAll("[^0-9]", "")); // Extracts numeric part from ID
        } catch (NumberFormatException e) {
            return numberOfHelicopterCreated; // Return static counter if ID parsing fails
        }
    }

    /**
     * Returns the type of this aircraft as a string.
     */
    @Override
    public String getType() {
        return helicopterType != null ? helicopterType.toString() : "HELICOPTER";  // Returns helicopter type
    }

    /**
     * Returns the total number of helicopters created.
     */
    public static int getNumberOfHelicopterCreated() {
        return numberOfHelicopterCreated; // Static method to get the number of helicopters created
    }

    /**
     * Gets the specific helicopter type
     * @return HelicopterType enum value
     */
    public HelicopterType getHelicopterType() {
        return helicopterType;
    }

    /**
     * Sets the specific helicopter type
     * @param helicopterType new type to set
     */
    public void setHelicopterType(HelicopterType helicopterType) {
        this.helicopterType = helicopterType;
    }

    public void setType(String type) {
        try {
            this.helicopterType = HelicopterType.valueOf(type.toUpperCase()); // Parse string type to enum
        } catch (IllegalArgumentException e) {
            System.err.println("Tipo de helicóptero inválido: " + type);
        }
    }

    public void setType(HelicopterType type) {
        this.helicopterType = type;  // Direct assignment if type is passed as enum
    }

    /**
     * Returns a string representation of the helicopter
     * @return formatted string with helicopter details
     */
    @Override
    public String toString() {
        return String.format("Helicopter [ID: %s, Type: %s, Manufacturer: %s, Max Speed: %.2f]",
                idAircraft, getType(), manufacture, maxSpeed);
    }

    /**
     * Gets the aircraft category (overrides parent method)
     * @return always "HELICOPTER" for this subclass
     */
    @Override
    public String getAircraftCategory() {
        return "HELICOPTER";  // Returns the category of the aircraft
    }
}
