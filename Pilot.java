
// Package declaration
package POO;

// Pilot class that inherits from the CrewMember class and represents a pilot
public class Pilot extends CrewMember {

    private static Pilot[] pilots;

    private static int pilotsCount = 0;

    /**
     * Constructor for the Pilot class
     *
     * @param name          The name of the pilot
     * @param rank          The rank of the pilot
     */
    public Pilot(String name, Rank rank) {
        super(name, rank); // Call to the constructor of the superclass CrewMember

        pilotsCount++;
    }


    /**
     * Getter method to retrieve the license number of the pilot
     *
     * @return The license number of the pilot
     */
    public int getIdPilot() {return crewMemberId;}

    public boolean addPilot(Pilot a) {
        if (a == null || pilotsCount >= pilots.length || getPilotById(a.getIdPilot()) != null) {
            return false;
        }
        pilots[pilotsCount++] = a;
        return true;
    }

    public static Pilot getPilotById(int id) {
        for (int i = 0; i < pilotsCount; i++) {
            if (pilots[i].getIdPilot() == (id)) {
                return pilots[i];
            }
        }
        return null;
    }

}
