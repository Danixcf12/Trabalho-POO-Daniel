
package POO;

// Interface defining operations related to mission management
public interface MissionOperations {

    /**
     * Adds a new mission to the system.
     *
     * @param mission The mission object to add
     */
    void addMission(Mission mission);

    /**
     * Removes a mission from the system based on its ID.
     *
     * @param id The unique ID of the mission
     * @return true if removal was successful, false otherwise
     */
    boolean removeMission(String id);

    /**
     * Retrieves a mission using its ID.
     *
     * @param id The unique ID of the mission
     * @return The mission object if found, or null if not found
     */
    Mission getMissionById(String id);

    /**
     * Returns an array of all missions in the system.
     *
     * @return Array containing all registered missions
     */
    Mission[] getAllMissions();

    /**
     * Edits the information of a specific mission.
     *
     * @param id   The ID of the mission to be edited
     * @param newMission The new mission data to set
     * @return true if the edit was successful, false otherwise
     */
    boolean editMission(String id, Mission newMission);
}
