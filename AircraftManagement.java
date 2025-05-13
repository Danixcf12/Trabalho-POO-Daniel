
package POO;
import java.io.*;
import java.util.*;

/**
 * Classe responsável por gerir uma lista de aeronaves.
 */
public class AircraftManagement {

    /** Array que armazena as aeronaves */
    private Aircraft[] aircrafts;

    /** Quantidade atual de aeronaves adicionadas */
    private static int aircraftCount = 0;

    /**
     * Construtor que define a capacidade máxima do sistema de aeronaves.
     *
     * @param maxAircrafts Capacidade máxima de aeronaves que podem ser armazenadas
     */
    public AircraftManagement(int maxAircrafts) {
        this.aircrafts = new Aircraft[maxAircrafts];
    }

    /**
     * Adiciona uma aeronave ao sistema, se houver espaço e o ID for único.
     *
     * @param a Aeronave a ser adicionada
     * @return true se foi adicionada com sucesso, false caso contrário
     */
    public boolean addAircraft(Aircraft a) {
        if (a == null || aircraftCount >= aircrafts.length || getAircraftById(a.getIdAircraft()) != null) {
            return false;
        }
        aircrafts[aircraftCount++] = a;
        return true;
    }

    /**
     * Retorna um array com as aeronaves atualmente armazenadas (sem posições nulas).
     *
     * @return Cópia do array de aeronaves adicionadas
     */
    public Aircraft[] getAircrafts() {
        Aircraft[] activeAircrafts = new Aircraft[aircraftCount];
        System.arraycopy(aircrafts, 0, activeAircrafts, 0, aircraftCount);
        return activeAircrafts;
    }

    /**
     * Retorna aeronaves incorporadas num determinado ano.
     *
     * @param year Ano de incorporação a filtrar
     * @return Array de aeronaves incorporadas nesse ano
     */
    public Aircraft[] getAircraftByIncorporationYear(int year) {
        Aircraft[] temp = new Aircraft[aircraftCount];
        int count = 0;

        for (int i = 0; i < aircraftCount; i++) {
            Date incDate = aircrafts[i].getIncorporationDate();
            if (incDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(incDate);
                if (cal.get(Calendar.YEAR) == year) {
                    temp[count++] = aircrafts[i];
                }
            }
        }

        Aircraft[] result = new Aircraft[count];
        System.arraycopy(temp, 0, result, 0, count);
        return result;
    }

    /**
     * Retorna aeronaves cuja vida útil (30 anos) termina dentro de um número de anos.
     *
     * @param years Número de anos para verificar
     * @return Array de aeronaves expirando dentro desse período
     */
    public Aircraft[] getAircraftsExpiringInYears(int years) {
        Aircraft[] temp = new Aircraft[aircraftCount];
        int count = 0;
        Date now = new Date();

        for (int i = 0; i < aircraftCount; i++) {
            Date incDate = aircrafts[i].getIncorporationDate();
            if (incDate != null) {
                long diffMillis = now.getTime() - incDate.getTime();
                long yearsInService = diffMillis / (1000L * 60 * 60 * 24 * 365);
                long remainingYears = 30 - yearsInService;

                if (remainingYears <= years) {
                    temp[count++] = aircrafts[i];
                }
            }
        }

        Aircraft[] result = new Aircraft[count];
        System.arraycopy(temp, 0, result, 0, count);
        return result;
    }

    /**
     * Grava num ficheiro os dados das aeronaves (apenas fabricante e tipo).
     *
     * @param filename Nome do ficheiro para guardar
     * @throws IOException Erro de escrita no ficheiro
     */
    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < aircraftCount; i++) {
                writer.write(aircrafts[i].getManufacture() + "," + aircrafts[i].getClass().getSimpleName());
                writer.newLine();
            }
        }
    }

    /**
     * Carrega aeronaves de um ficheiro, criando instâncias com valores padrão.
     *
     * @param filename Nome do ficheiro a ler
     * @throws IOException Erro de leitura no ficheiro
     */
    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String manu = parts[0];
                    String type = parts[1];
                    Aircraft newA = null;

                    switch (type) {
                        case "Fighter":
                            newA = new Fighter(type, manu, null, null, null, null, aircraftCount, aircraftCount, aircraftCount, null);
                            break;
                        case "Transport":
                            newA = new Transportation(type, manu, null, null, null, null, aircraftCount, aircraftCount, aircraftCount, null);
                            break;
                        case "Helicopter":
                            newA = new Helicopter(type, manu, null, null, null, null, null, aircraftCount, aircraftCount, aircraftCount, null);
                            break;
                        default:
                            System.err.println("Tipo desconhecido: " + type);
                    }

                    if (newA != null) {
                        addAircraft(newA);
                    }
                }
            }
        }
    }

    /**
     * Conta quantas aeronaves fizeram pelo menos um número mínimo de missões num ano.
     *
     * @param minMissions Número mínimo de missões
     * @param year Ano a verificar
     * @param missionManagement Instância de MissionManagement para aceder aos dados
     * @return Número de aeronaves que cumprem os critérios
     */
    public int getAircraftCountWithMinMissions(int minMissions, int year, MissionManagement missionManagement) {
        int count = 0;
        for (int i = 0; i < aircraftCount; i++) {
            int missionCount = missionManagement.getAircraftMissionCountByYear(aircrafts[i], year);
            if (missionCount >= minMissions) {
                count++;
            }
        }
        return count;
    }

    /**
     * Versão alternativa para contar aeronaves com missões mínimas num ano.
     *
     * @param year Ano a verificar
     * @param minMissions Número mínimo de missões
     * @param missionManagement Instância do gerenciador de missões
     * @return Número de aeronaves que cumprem os critérios
     */
    public int getAircraftsWithMinMissionsInYear(int year, int minMissions, MissionManagement missionManagement) {
        int count = 0;
        for (int i = 0; i < aircraftCount; i++) {
            int missionCount = missionManagement.getAircraftMissionCountByYear(aircrafts[i], year);
            if (missionCount >= minMissions) {
                count++;
            }
        }
        return count;
    }

    /**
     * Remove uma aeronave com base no seu ID.
     *
     * @param id ID da aeronave a remover
     * @return true se for removida, false se não encontrada
     */
    public boolean removeAircraft(String id) {
        for (int i = 0; i < aircraftCount; i++) {
            if (aircrafts[i].getIdAircraft().equals(id)) {
                for (int j = i; j < aircraftCount - 1; j++) {
                    aircrafts[j] = aircrafts[j + 1];
                }
                aircrafts[--aircraftCount] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna uma aeronave pelo seu ID.
     *
     * @param id ID da aeronave a procurar
     * @return Aeronave encontrada ou null
     */
    public Aircraft getAircraftById(String id) {
        for (int i = 0; i < aircraftCount; i++) {
            if (aircrafts[i].getIdAircraft().equals(id)) {
                return aircrafts[i];
            }
        }
        return null;
    }


    /**
     * Returns an array of all aircraft types present in the system (unique types)
     * @return String array containing the simple class names of all aircraft types
     */
    public String[] getAllAircrafts() {
        Set<String> types = new HashSet<>();

        for (int i = 0; i < aircraftCount; i++) {
            if (aircrafts[i] != null) {
                types.add(aircrafts[i].getClass().getSimpleName());
            }
        }

        return types.toArray(new String[0]);
    }


    /**
     * Método de depuração para imprimir a lista atual de aeronaves.
     */
    public void debugListAircrafts() {
        System.out.println("\n=== DEBUG: Aircraft List State ===");
        System.out.println("Total aircrafts: " + aircraftCount);
        for (int i = 0; i < aircraftCount; i++) {
            System.out.println(aircrafts[i].toString());
        }
    }


}
