
package POO;

public class Crew {
    //--------- Variáveis ----------
    protected MissionContext missionContext;

    private static float counterNumberOfNATIONALMissions = 0;
    private static float counterNumberOfNATOMissions = 0;
    private static float counterNumberOfONUMissions = 0;

    //-------- Construtores ----------

    /**
     * Este construtor cria uma tripulação com uma aeronave, um piloto e um tipo de missão.
     *
     * @param aircraft       A aeronave que participará da missão
     * @param pilot          O piloto da missão
     * @param missionContext O tipo de missão (NATIONAL, NATO ou ONU)
     */
    public Crew(Aircraft aircraft, Pilot pilot, MissionContext missionContext) {
        this.missionContext = missionContext;
        // Aumenta o número de missões de cada aeronave com base no tipo de missão
        aircraft.increaseNumberOfMissionToEach(this.missionContext);
        increaseNumberMissionType();
    }

    //-------- Métodos ---------

    /**
     * Retorna o tipo de missão.
     *
     * @return O tipo de missão da tripulação
     */
    public MissionContext getMissionType() {
        return missionContext;
    }

    /**
     * Aumenta o contador de missões de acordo com o tipo de missão.
     */
    private void increaseNumberMissionType() {
        // Verifica o tipo de missão e incrementa o contador correspondente
        if (this.missionContext == MissionContext.NATIONAL) {
            counterNumberOfNATIONALMissions++;
        } else if (this.missionContext == MissionContext.NATO) {
            counterNumberOfNATOMissions++;
        } else if (this.missionContext == MissionContext.ONU) {
            counterNumberOfONUMissions++;
        }
    }

    /**
     * Calcula a média de missões para cada tipo de aeronave.
     *
     * @param tempMissionContext O tipo de missão para o qual será calculada a média
     * @return A média de missões
     */
    public static double averageNumberMissionTransportation(MissionContext tempMissionContext) {
        // Número de transportes criados
        int numTransports = Transportation.getnumberOfTransportationCreated();
        if (numTransports == 0) return 0;

        // Calcula a média com base no tipo de missão
        if (tempMissionContext == MissionContext.NATIONAL) {
            return counterNumberOfNATIONALMissions / numTransports;
        } else if (tempMissionContext == MissionContext.NATO) {
            return counterNumberOfNATOMissions / numTransports;
        } else if (tempMissionContext == MissionContext.ONU) {
            return counterNumberOfONUMissions / numTransports;
        } else {
            return 0;
        }
    }

    // -------- Métodos Estáticos para Contagem ---------

    /**
     * Retorna o número total de missões nacionais.
     *
     * @return O número total de missões nacionais
     */
    public static float getNumberOfNATIONALMissions() {
        return counterNumberOfNATIONALMissions;
    }

    /**
     * Retorna o número total de missões NATO.
     *
     * @return O número total de missões NATO
     */
    public static float getNumberOfNATOMissions() {
        return counterNumberOfNATOMissions;
    }

    /**
     * Retorna o número total de missões da ONU.
     *
     * @return O número total de missões da ONU
     */
    public static float getNumberOfONUMissions() {
        return counterNumberOfONUMissions;
    }
}
