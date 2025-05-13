
package POO;
import java.util.Date;

/**
 * Class representing an Authorizer — someone with authority over actions or missions.
 */
public class Authorizer {

    // -------- Attributes --------
    public String id;     // Unique identifier for the authorizer
    public String name;   // Name of the authorizer
    public Rank rank;     // Rank or role of the authorizer (custom enum/class)
    public Date date;     // Date associated with the authorizer (could be assigned date, etc.)

    /**
     * Constructs an Authorizer object with the given attributes.
     *
     * @param id    The authorizer's unique ID
     * @param name  The full name of the authorizer
     * @param rank  The rank or position held by the authorizer
     * @param date  The relevant date (e.g. date of authorization or assignment)
     */
    public Authorizer(String id, String name, Rank rank, Date date) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.date = date;
    }

    // -------- Getters --------

    /**
     * Gets the ID of the authorizer.
     * @return the authorizer's ID
     */
    public String getIdAuthorizer() {
        return id;
    }

    /**
     * Gets the name of the authorizer.
     * @return the authorizer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the rank or position of the authorizer.
     * @return the authorizer's rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Gets the date associated with the authorizer.
     * @return the date (e.g. date of appointment)
     */
    public Date getDate() {
        return date;
    }
}
