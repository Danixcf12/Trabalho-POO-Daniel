
package POO;

/**
 * Interface that defines basic operations for managing authorizers.
 * Any class implementing this must provide these functionalities.
 */
public interface AuthorizerOperations {

    /**
     * Adds a new authorizer to the collection.
     * @param name The name of the authorizer to be added
     */
    void addAuthorizer(String name);

    /**
     * Removes an existing authorizer from the collection.
     * @param name The name of the authorizer to be removed
     * @return true if the authorizer was successfully removed, false if not found
     */
    boolean removeAuthorizer(String name);

    /**
     * Returns an array of all authorizers.
     * @return An array containing the names of all authorizers
     */
    String[] listAuthorizers();

    /**
     * Edits the name of an existing authorizer.
     * @param oldName The current name of the authorizer
     * @param newName The new name to assign
     * @return true if the authorizer was found and renamed, false otherwise
     */
    boolean editAuthorizer(String oldName, String newName);
}
