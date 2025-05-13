
package POO;

/**
 * Interface that defines basic operations for managing aircrafts.
 */
public interface AircraftOperations {

    /**
     * Adds an aircraft to the collection.
     *
     * @param aircraft The aircraft to be added
     */
    void addAircraft(Aircraft aircraft);

    /**
     * Removes an aircraft using its ID.
     *
     * @param id The unique ID of the aircraft to remove
     * @return true if the aircraft was removed, false if not found
     */
    boolean removeAircraft(String id);

    /**
     * Retrieves an aircraft by its ID.
     *
     * @param id The unique ID of the aircraft to retrieve
     * @return The aircraft with the specified ID, or null if not found
     */
    Aircraft getAircraftById(String id);

    /**
     * Returns all aircraft currently managed.
     *
     * @return An array of all aircrafts
     */
    Aircraft[] getAllAircraft();

    /**
     * Edits the information of an existing aircraft.
     *
     * @param id The ID of the aircraft to edit
     * @param updatedAircraft An Aircraft object with the updated info
     * @return true if the aircraft was successfully edited, false if not found
     */
    boolean editAircraft(String id, Aircraft updatedAircraft);
}
