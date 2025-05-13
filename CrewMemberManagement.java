
package POO;

// Implementação do gerenciamento dos membros da tripulação
public class CrewMemberManagement implements CrewMemberOperations {

    // -------- Atributos --------
    private CrewMember[] crewMembers;    // Array para armazenar membros da tripulação
    private int size;                     // Tamanho atual do array (quantos membros foram adicionados)

    // Capacidade inicial do array
    private static final int INITIAL_CAPACITY = 10;

    // -------- Construtor --------
    public CrewMemberManagement() {
        this.crewMembers = new CrewMember[INITIAL_CAPACITY];  // Inicializa o array
        this.size = 0;  // Nenhum membro adicionado ainda
    }

    // -------- Operações --------

    /**
     * Adiciona um novo membro da tripulação ao array.
     *
     * @param crew O membro da tripulação a ser adicionado
     */
    @Override
    public void addCrewMember(CrewMember crew) {
        if (size == crewMembers.length) {
            // Se o array estiver cheio, dobra a capacidade
            resizeArray(crewMembers.length * 2);
        }
        crewMembers[size++] = crew;  // Adiciona o membro no final e incrementa o tamanho
    }

    /**
     * Remove um membro da tripulação com base no seu ID.
     *
     * @param id O ID do membro a ser removido
     * @return true se o membro foi encontrado e removido, false caso contrário
     */
    @Override
    public boolean removeCrewMember(int id) {
        for (int i = 0; i < size; i++) {
            if (crewMembers[i].getNumber() == id) {
                // Move todos os membros após o removido uma posição para a esquerda
                System.arraycopy(crewMembers, i + 1, crewMembers, i, size - i - 1);
                crewMembers[--size] = null; // Decrementa o tamanho e limpa a última posição
                return true;
            }
        }
        return false;
    }

    /**
     * Recupera um membro da tripulação pelo seu ID.
     *
     * @param id O ID do membro
     * @return O membro da tripulação, ou null se não encontrado
     */
    @Override
    public CrewMember getCrewMemberById(int id) {
        for (int i = 0; i < size; i++) {
            if (crewMembers[i].getNumber() == id) {
                return crewMembers[i];
            }
        }
        return null;
    }

    /**
     * Retorna todos os membros da tripulação registrados.
     *
     * @return Um array contendo todos os membros da tripulação
     */
    @Override
    public CrewMember[] getAllCrewMembers() {
        CrewMember[] result = new CrewMember[size];
        System.arraycopy(crewMembers, 0, result, 0, size);
        return result;
    }

    /**
     * Edita os detalhes de um membro da tripulação existente.
     *
     * @param id    O ID do membro da tripulação a ser editado
     * @param name  O novo nome
     * @param rank  O novo rank
     * @return true se o membro foi encontrado e atualizado, false caso contrário
     */
    public boolean editCrewMember(int id, String name, Rank rank) {
        CrewMember crew = getCrewMemberById(id);
        if (crew != null) {
            // Atualiza o membro da tripulação com os novos dados
            crew = new CrewMember(name, rank);
            // Encontra o índice e atualiza o membro
            for (int i = 0; i < size; i++) {
                if (crewMembers[i].getNumber() == id) {
                    crewMembers[i] = crew;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Redimensiona o array para acomodar mais membros da tripulação.
     *
     * @param newCapacity A nova capacidade do array
     */
    private void resizeArray(int newCapacity) {
        CrewMember[] newArray = new CrewMember[newCapacity];
        System.arraycopy(crewMembers, 0, newArray, 0, size);
        crewMembers = newArray;  // Substitui o array antigo pelo novo com maior capacidade
    }
}
