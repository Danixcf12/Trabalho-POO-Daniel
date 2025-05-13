
package POO;

public class CrewMember {

    // -------- Variables --------

    protected int crewMemberId;
    protected String name;
    protected Rank rank;

    protected static int idCounter = 0;

    // -------- Constructors --------

    /**
     * Create a new crew member with the parameters
     *
     * @param name   Crew members name
     * @param rank   Crew members rank
     */
    public CrewMember(String name, Rank rank) {
        this.name = name;
        this.rank = rank;

        idCounter++;
        this.crewMemberId = idCounter;
    }

    // -------- Methods --------

    /**
     * Obtém o número único do membro da tripulação.
     *
     * @return o número do membro da tripulação
     */
    public int getNumber() {
        return crewMemberId;
    }

    /**
     * Obtém o nome do membro da tripulação.
     *
     * @return o nome do membro da tripulação
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém a classificação do membro da tripulação.
     *
     * @return a classificação do membro da tripulação
     */
    public Rank getRank() {
        return rank;
    }
}
