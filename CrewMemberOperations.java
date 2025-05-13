
package POO;

// Interface definindo as operações relacionadas ao gerenciamento de membros da tripulação
public interface CrewMemberOperations {

    /**
     * Adiciona um novo membro da tripulação ao sistema.
     *
     * @param crew O objeto membro da tripulação a ser adicionado
     */
    void addCrewMember(CrewMember crew);

    /**
     * Remove um membro da tripulação do sistema com base no seu ID.
     *
     * @param id O ID único do membro da tripulação
     * @return true se a remoção for bem-sucedida, false caso contrário
     */
    boolean removeCrewMember(int id);

    /**
     * Recupera um membro da tripulação usando seu ID.
     *
     * @param id O ID único do membro da tripulação
     * @return O objeto membro da tripulação se encontrado, ou null se não encontrado
     */
    CrewMember getCrewMemberById(int id);

    /**
     * Retorna todos os membros da tripulação registrados no sistema.
     *
     * @return Um array contendo todos os membros da tripulação registrados
     */
    CrewMember[] getAllCrewMembers();

    /**
     * Edita as informações de um membro específico da tripulação.
     *
     * @param id   O ID do membro da tripulação a ser editado
     * @param name O novo nome a ser atribuído
     * @param rank O novo rank a ser atribuído
     * @return true se a edição for bem-sucedida, false caso contrário
     */
    boolean editCrewMember(int id, String name, Rank rank);
}
