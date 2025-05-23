package POO;

import java.util.Date;
import java.util.Calendar;

// Abstract class representing an aircraft (like a blueprint, can't be used directly)
public abstract class Aircraft {

    // -------- Attributes --------

    protected String manufacture;         // Manufacturer's name
    protected Date incorporationDate;     // Date the aircraft was incorporated into service
    protected Date lastInspectionDate;    // Last inspection date
    protected Date nextInspectionDate;    // Next scheduled inspection date
    protected Date lastMaintenanceDate;   // Last maintenance performed
    protected Date nextMaintenanceDate;   // Next scheduled maintenance
    protected float wingspan;             // Wingspan in meters or whatever unit
    protected float weight;               // Aircraft weight
    protected float maxSpeed;             // Max speed it can fly

    protected MissionContext[] missionContext = new MissionContext[10];  // Fixed-size array for missions

    // Internal counters to track how many missions of each type the aircraft did
    private int countNATIONALMissions = 0;
    private int countNATOMissions = 0;
    private int countONUMissions = 0;

    private String manufacturer;       // Used in edit/getManufacturer
    private int capacity;              // Used in edit/getCapacity
    private int missionCount = 0;      // Counter for number of missions

    private static int idCounter = 99;
    private int intPartAircraftID;
    protected String idAircraft;          // Unique identifier of the aircraft

    // -------- Constructor --------

    /**
     * Constructs an Aircraft with all the parameters below.
     * Also sets the next inspection date automatically to one year after the last.
     *
     * @param manufacture          The manufacturer of the aircraft
     * @param incorporationDate    The date the aircraft entered service
     * @param lastInspectionDate   The date of the last inspection
     * @param lastMaintenanceDate  The date of the last maintenance
     * @param nextMaintenanceDate  The scheduled date for the next maintenance
     * @param wingspan             The aircraft's wingspan
     * @param weight               The aircraft's weight
     * @param maxSpeed             The aircraft's maximum speed
     */
    public Aircraft(String manufacture, Date incorporationDate, Date lastInspectionDate,
                    Date lastMaintenanceDate, Date nextMaintenanceDate, float wingspan, float weight,
                    float maxSpeed) {
        this.manufacture = manufacture;
        this.manufacturer = manufacture; // Initialize both manufacturer attributes
        this.incorporationDate = incorporationDate;
        this.lastInspectionDate = lastInspectionDate;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.wingspan = wingspan;
        this.weight = weight;
        this.maxSpeed = maxSpeed;

        idCounter++;
        this.intPartAircraftID = idCounter;


        // Replace missionContext array if passed
        if (missionContext != null) {
            this.missionContext = missionContext;
            this.missionCount = missionContext.length;
        }

        calculateNextInspectionDate();
    }

    // -------- Methods --------


    public int getIntPartAircraftID() {return intPartAircraftID;}

    /**
     * Calculates next maintenance date based on last maintenance date
     */
    private void calculateNextMaintenanceDate() {
        if (lastMaintenanceDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lastMaintenanceDate);
            calendar.add(Calendar.MONTH, 6); // Maintenance every 6 months
            nextMaintenanceDate = calendar.getTime();
        }
    }

    // Private method that calculates next inspection date = last inspection + 1 year
    private void calculateNextInspectionDate() {
        if (lastInspectionDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lastInspectionDate);
            calendar.add(Calendar.YEAR, 1);
            nextInspectionDate = calendar.getTime();
        }
    }

    /**
     * Must be implemented by subclasses to return aircraft type (e.g., fighter, cargo).
     *
     * @return a string representing the aircraft type
     */
    public abstract String getType();

    /**
     * Gets the aircraft incorporation date.
     *
     * @return the incorporation date
     */
    public Date getIncorporationDate() {return incorporationDate;}

    /**
     * Sets the incorporation date
     * @param incorporationDate new incorporation date
     */
    public void setIncorporationDate(Date incorporationDate) {this.incorporationDate = incorporationDate;}

    /**
     * Sets the last inspection date and recalculates the next one.
     *
     * @param lastInspectionDate the date of the last inspection
     */
    public void setLastInspectionDate(Date lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
        calculateNextInspectionDate();
    }

    /**
     * Sets the next inspection date manually (kinda overrides the auto one).
     *
     * @param nextInspectionDate the date for the next inspection
     */
    public void setNextInspectionDate(Date nextInspectionDate) {this.nextInspectionDate = nextInspectionDate;}

    /**
     * Gets the next scheduled inspection date.
     *
     * @return the next inspection date
     */
    public Date getNextInspectionDate() {return nextInspectionDate;}

    /**
     * Gets the date of the last inspection.
     *
     * @return the last inspection date
     */
    public Date getLastInspectionDate() {return lastInspectionDate;}

    /**
     * Gets the last maintenance date.
     *
     * @return the last maintenance date
     */
    public Date getLastMaintenanceDate() {return lastMaintenanceDate;}

    /**
     * Sets the last maintenance date and calculates next one
     * @param lastMaintenanceDate new maintenance date
     */
    public void setLastMaintenanceDate(Date lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
        calculateNextMaintenanceDate();
    }

    /**
     * Gets the next maintenance date
     * @return next maintenance date
     */
    public Date getNextMaintenanceDate() {return nextMaintenanceDate;}

    /**
     * Sets the next maintenance date
     * @param nextMaintenanceDate new next maintenance date
     */
    public void setNextMaintenanceDate(Date nextMaintenanceDate) {this.nextMaintenanceDate = nextMaintenanceDate;}

    /**
     * Gets the aircraft's manufacturer (original attribute).
     *
     * @return the manufacturer
     */
    public String getManufacture() {return manufacture;}

    /**
     * Sets the manufacture name
     * @param manufacture new manufacturer name
     */
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
        this.manufacturer = manufacture; // Keep both in sync
    }

    /**
     * Gets the aircraft's manufacturer (used in editable fields).
     *
     * @return the manufacturer
     */
    public String getManufacturer() {return manufacturer;}

    /**
     * Sets the aircraft's manufacturer (editable).
     *
     * @param manufacturer the new manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        this.manufacture = manufacturer; // Keep both in sync
    }

    /**
     * Gets the aircraft capacity (editable field).
     *
     * @return the capacity
     */
    public int getCapacity() {return capacity;}

    /**
     * Sets the aircraft capacity (editable).
     *
     * @param capacity the new capacity
     */
    public void setCapacity(int capacity) {this.capacity = capacity;}

    /**
     * Gets the wingspan
     * @return wingspan value
     */
    public float getWingspan() {return wingspan;}

    /**
     * Sets the wingspan
     * @param wingspan new wingspan value
     */
    public void setWingspan(float wingspan) {this.wingspan = wingspan;}

    /**
     * Gets the weight
     * @return weight value
     */
    public float getWeight() {return weight;}

    /**
     * Sets the weight
     * @param weight new weight value
     */
    public void setWeight(float weight) {this.weight = weight;}

    /**
     * Gets the max speed
     * @return max speed value
     */
    public float getMaxSpeed() {return maxSpeed;}

    /**
     * Sets the max speed
     * @param maxSpeed new max speed value
     */
    public void setMaxSpeed(float maxSpeed) {this.maxSpeed = maxSpeed;}

    /**
     * Adds a mission context to the aircraft's mission list.
     *
     * @param mission the mission to add
     */
    public void addMission(MissionContext mission) {
        if (mission != null && missionCount < missionContext.length) {
            missionContext[missionCount++] = mission; // Adds mission and increments counter
        }
    }

    /**
     * Removes a mission context from the aircraft's mission list
     * @param mission mission to remove
     */
    public void removeMission(MissionContext mission) {
        for (int i = 0; i < missionCount; i++) {
            if (missionContext[i] == mission) {
                // Shift remaining missions
                for (int j = i; j < missionCount - 1; j++) {
                    missionContext[j] = missionContext[j + 1];
                }
                missionContext[--missionCount] = null; // Decrease missionCount
                break;
            }
        }
    }

    /**
     * Gets the list of all mission contexts for this aircraft.
     *
     * @return the mission context array
     */
    public MissionContext[] getContext() {
        return missionContext; // Return the array directly
    }

    /**
     * Gets the ID of the aircraft.
     *
     * @return the aircraft ID
     */
    public String getIdAircraft() {return idAircraft;}


    /**
     * Increases mission count based on the mission context passed in.
     *
     * @param tempMissionContext the type of mission to count
     */
    public void increaseNumberOfMissionToEach(MissionContext tempMissionContext){
        if(tempMissionContext == MissionContext.NATIONAL){
            countNATIONALMissions++;
        }
        else if(tempMissionContext == MissionContext.NATO){
            countNATOMissions++;
        }
        else if(tempMissionContext == MissionContext.ONU){
            countONUMissions++;
        }
    }


    /**
     * Returns string representation of the aircraft
     * @return string with basic aircraft info
     */
    @Override
    public String toString() {
        return String.format("Aircraft ID: %s, Type: %s, Manufacturer: %s",
                idAircraft, getType(), manufacture);
    }

    public String getAircraftCategory() {
        return "GENERIC_AIRCRAFT";
    }
}
