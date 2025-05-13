
// Package declaration
package POO;

import java.util.Date;

// Class that represents a Mission
public class Mission {

    // Private attributes that describe the mission
    private String idMisson;         // Unique identifier for the mission
    private Date startDate;          // Mission start date
    private Date endDate;            // Mission end date
    private String purpose;          // Purpose or goal of the mission
    private String authorizer;       // Name of the person who authorized the mission
    private String rank;             // Rank of the person who authorized the mission
    private MissionType type;        // Type of mission (e.g., RESCUE, PATROL)
    private MissionContext context;  // Context of the mission (e.g., NATIONAL, NATO)
    private Aircraft aircraft;       // Aircraft assigned to the mission
    private CrewMember[] crew;       // Array to store the crew members assigned to this mission
    private int crewCount;           // Counter to keep track of how many crew members are assigned

    // Constructor for creating a mission with all required attributes
    public Mission(String idMission, Date startDate, Date endDate, String purpose, String authorizer, String rank,
                   MissionType type, MissionContext context, Aircraft aircraft, int maxCrew, Pilot pilots) {
        this.idMisson = idMission;
        this.startDate = startDate;
        this.endDate = endDate;
        this.purpose = purpose;
        this.authorizer = authorizer;
        this.rank = rank;
        this.type = type;
        this.context = context;
        this.aircraft = aircraft;
        this.crew = new CrewMember[maxCrew]; // Initialize the crew array with maximum capacity
        this.crewCount = 0;                  // Initialize the count of assigned crew members
    }

    // Getter and Setter methods for the attributes

    public String getIdMission() {
        return idMisson;
    }

    public void setIdMission(String idMission) {
        this.idMisson = idMission;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAuthorizedBy() {
        return authorizer;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizer = authorizedBy;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public MissionType getType() {
        return type;
    }

    public void setType(MissionType type) {
        this.type = type;
    }

    public MissionContext getContext() {
        return context;
    }

    public void setContext(MissionContext context) {
        this.context = context;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public CrewMember[] getCrew() {
        return crew;
    }

    public void setCrew(CrewMember[] crew) {
        this.crew = crew;
        this.crewCount = crew.length;
    }

    // Method to add a crew member to the mission
    public boolean addCrewMember(CrewMember member) {
        if (crewCount < crew.length) {
            crew[crewCount++] = member;
            return true;
        }
        return false;  // Cannot add more crew members if the array is full
    }

    // Method to remove a crew member by name
    public boolean removeCrewMemberByName(String name) {
        for (int i = 0; i < crewCount; i++) {
            if (crew[i].getName().equalsIgnoreCase(name)) {
                // Shift remaining crew to the left to fill the gap
                for (int j = i; j < crewCount - 1; j++) {
                    crew[j] = crew[j + 1];
                }
                crew[--crewCount] = null; // Nullify the last element and decrement count
                return true;
            }
        }
        return false; // Crew member not found
    }

    // Method to return a readable string representation of the mission
    @Override
    public String toString() {
        return "Mission: " + purpose + " (" + type + ") from " + startDate + " to " + endDate +
                ", authorized by " + rank + " " + authorizer;
    }
}
