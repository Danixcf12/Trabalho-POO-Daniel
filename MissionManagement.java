
// Package declaration
package POO;

import java.text.SimpleDateFormat;
import java.util.*;

// Class that manages missions, including adding, removing, and querying based on different criteria
public class MissionManagement {

    // Array to store the missions
    private Mission[] missions;
    // Counter for the number of missions added
    private int missionCount = 0;

    /**
     * Constructor for the MissionManager class
     * @param maxMissions Maximum size of the missions array
     */
    public MissionManagement(int maxMissions) {
        missions = new Mission[maxMissions];
    }

    /**
     * Gets a mission by its ID
     * @param id The ID of the mission to find
     * @return The mission with the specified ID, or null if not found
     */
    public Mission getMissionById(String id) {
        for (int i = 0; i < missionCount; i++) {
            if (missions[i] != null && missions[i].getIdMission().equals(id)) {
                return missions[i];
            }
        }
        return null;
    }

    /**
     * Adds a mission to the manager
     * @param mission The mission to be added
     */
    public void addMission(Mission mission) {
        if (missionCount < missions.length) {
            missions[missionCount++] = mission;
        }
    }

    /**
     * Returns all the stored missions
     * @return Array of missions
     */
    public Mission[] getAllMissions() {
        Mission[] result = new Mission[missionCount];
        System.arraycopy(missions, 0, result, 0, missionCount);
        return result;
    }

    /**
     * a) Returns all missions associated with a specific aircraft
     * @param aircraft The aircraft whose missions are to be returned
     * @return Array of missions associated with the aircraft
     */
    public Mission[] getMissionsByAircraft(Aircraft aircraft) {
        int count = 0;
        for (int i = 0; i < missionCount; i++) {
            if (missions[i] != null && missions[i].getAircraft() != null && missions[i].getAircraft().equals(aircraft)) {
                count++;
            }
        }
        Mission[] result = new Mission[count];
        int index = 0;
        for (int i = 0; i < missionCount; i++) {
            if (missions[i] != null && missions[i].getAircraft() != null && missions[i].getAircraft().equals(aircraft)) {
                result[index++] = missions[i];
            }
        }
        return result;
    }

    /**
     * b) Returns all missions of a specific type
     * @param type The type of the mission (e.g., RESCUE, PATROL)
     * @return Array of missions with the specified type
     */
    public Mission[] getMissionsByType(MissionType type) {
        int count = 0;
        for (int i = 0; i < missionCount; i++) {
            if (missions[i] != null && missions[i].getType() == type) {
                count++;
            }
        }
        Mission[] result = new Mission[count];
        int index = 0;
        for (int i = 0; i < missionCount; i++) {
            if (missions[i] != null && missions[i].getType() == type) {
                result[index++] = missions[i];
            }
        }
        return result;
    }

    /**
     * f) Returns all missions that include a specific crew member
     * @param member Crew member
     * @return Array of missions that involve the provided crew member
     */
    public Mission[] getMissionsByCrewMember(CrewMember member) {
        int count = 0;
        for (int i = 0; i < missionCount; i++) {
            if (missions[i] != null) {
                for (CrewMember cm : missions[i].getCrew()) {
                    if (cm.equals(member)) {
                        count++;
                        break;
                    }
                }
            }
        }
        Mission[] result = new Mission[count];
        int index = 0;
        for (int i = 0; i < missionCount; i++) {
            if (missions[i] != null) {
                for (CrewMember cm : missions[i].getCrew()) {
                    if (cm.equals(member)) {
                        result[index++] = missions[i];
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * c) Returns the number of missions performed by an aircraft in a specific year
     * @param a The aircraft to check missions for
     * @param year The year to check for
     * @return Number of missions performed by the aircraft in the specified year
     */
    public int getAircraftMissionCountByYear(Aircraft a, int year) {
        int count = 0;
        Calendar cal = Calendar.getInstance();
        for (Mission m : getMissionsByAircraft(a)) {
            if (m.getStartDate() != null) {
                cal.setTime(m.getStartDate());
                if (cal.get(Calendar.YEAR) == year) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Returns all missions that match both a specific aircraft type and mission type
     * @param aircraftType The type of aircraft (e.g., "Helicopter", "Fighter")
     * @param missionType The type of mission (e.g., MissionType.RESCUE)
     * @return Array of missions matching both criteria
     */
    public Mission[] getMissionsByAircraftTypeAndMissionType(String aircraftType, MissionType missionType) {
        List<Mission> result = new ArrayList<>();

        for (int i = 0; i < missionCount; i++) {
            if (missions[i] != null &&
                    missions[i].getType() == missionType &&
                    missions[i].getAircraft() != null &&
                    missions[i].getAircraft().getClass().getSimpleName().equalsIgnoreCase(aircraftType)) {
                result.add(missions[i]);
            }
        }

        return result.toArray(new Mission[0]);
    }

    /**
     * d) Returns the average number of missions performed by aircraft of a specific type and a specific mission type
     * @param aircraftType The type of the aircraft (e.g., "Fighter", "Helicopter")
     * @param missionType The type of the mission (e.g., "RESCUE", "PATROL")
     * @return The average number of missions performed
     */
    public double getAverageMissionsByType(String aircraftType, MissionType missionType) {
        int total = 0;
        int aircraftCount = 0;
        String[] aircraftIds = new String[missionCount];
        int aircraftIndex = 0;

        for (Mission m : getAllMissions()) {
            if (m.getType() == missionType && m.getAircraft() != null &&
                    m.getAircraft().getClass().getSimpleName().equals(aircraftType)) {
                total++;
                String aircraftId = m.getAircraft().getIdAircraft();
                boolean found = false;
                for (int i = 0; i < aircraftIndex; i++) {
                    if (aircraftIds[i].equals(aircraftId)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    aircraftIds[aircraftIndex++] = aircraftId;
                    aircraftCount++;
                }
            }
        }

        return aircraftCount == 0 ? 0 : (double) total / aircraftCount;
    }

    /**
     * Method to print a report of missions for a given aircraft
     * @param aircraft The aircraft whose missions will be reported
     */
    public void printAircraftMissionReport(Aircraft aircraft) {
        Mission[] missions = getMissionsByAircraft(aircraft);
        for (Mission m : missions) {
            String name = m.getAuthorizedBy();
            String rank = m.getRank();

            System.out.println("Date: " + m.getStartDate() +
                    ", Purpose: " + m.getPurpose() +
                    ", Authorized by: " + name + " (" + rank + ")");
        }
    }

    /**
     * Removes a mission by its ID
     * @param id ID of the mission to be removed
     * @return true if the mission was successfully removed, false otherwise
     */
    public boolean removeMission(String id) {
        for (int i = 0; i < missions.length; i++) {
            if (missions[i] != null && missions[i].getIdMission().equals(id)) {
                missions[i] = null; // Remove the mission (set it to null)
                return true;
            }
        }
        return false; // Return false if the mission was not found
    }

    /**
     * Generates a detailed report of missions for a specific aircraft
     * @param aircraft The aircraft to generate the report for
     * @return Formatted string with the mission report
     */
    public String generateAircraftMissionReport(Aircraft aircraft) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Mission[] missions = getMissionsByAircraft(aircraft);
        StringBuilder report = new StringBuilder();

        // Header
        report.append("\n=== RELATÓRIO DE MISSÕES DA AERONAVE ").append(aircraft.getIdAircraft()).append(" ===\n");
        report.append("Tipo: ").append(aircraft.getClass().getSimpleName()).append("\n");
        report.append("Fabricante: ").append(aircraft.getManufacturer()).append("\n");

        // Verificação nula para incorporationDate
        if (aircraft.getIncorporationDate() != null) {
            report.append("Data de Incorporação: ").append(sdf.format(aircraft.getIncorporationDate())).append("\n");
        } else {
            report.append("Data de Incorporação: N/A\n");
        }

        report.append("==================================================\n");

        // Missions details
        if (missions.length == 0) {
            report.append("Nenhuma missão registrada para esta aeronave.\n");
        } else {
            Arrays.sort(missions, (m1, m2) -> {
                if (m1.getStartDate() == null) return 1;
                if (m2.getStartDate() == null) return -1;
                return m2.getStartDate().compareTo(m1.getStartDate());
            });

            for (Mission mission : missions) {
                report.append("ID Missão: ").append(mission.getIdMission()).append("\n");

                // Formatação segura de datas
                report.append("Período: ")
                        .append(mission.getStartDate() != null ? sdf.format(mission.getStartDate()) : "N/A")
                        .append(" a ")
                        .append(mission.getEndDate() != null ? sdf.format(mission.getEndDate()) : "N/A")
                        .append("\n");

                report.append("Propósito: ").append(mission.getPurpose() != null ? mission.getPurpose() : "N/A").append("\n");

                String authName = mission.getAuthorizedBy() != null ? mission.getAuthorizedBy() : "N/A";
                String authRank = mission.getRank() != null ? mission.getRank().toString() : "N/A";
                report.append("Autorizada por: ").append(authName)
                        .append(" (").append(authRank).append(")\n");

                report.append("Tipo: ").append(mission.getType() != null ? mission.getType() : "N/A").append("\n");
                report.append("Contexto: ").append(mission.getContext() != null ? mission.getContext() : "N/A").append("\n");
                report.append("Tripulação: ").append(mission.getCrew() != null ? mission.getCrew().length : 0).append(" membros\n");
                report.append("--------------------------------------------------\n");
            }
            report.append("Total de missões: ").append(missions.length).append("\n");
        }

        return report.toString();
    }
    /**
     * Returns missions associated with an aircraft and a specific context (e.g., "NATIONAL", "NATO")
     * @param aircraft The aircraft whose missions are to be returned
     * @param context The context of the mission (e.g., "NATIONAL")
     * @return Array of missions with the specified context
     */
    public Mission[] getMissionsByContext(Aircraft aircraft, String context) {
        List<Mission> result = new ArrayList<>();
        for (Mission m : getMissionsByAircraft(aircraft)) {
            // Check if the mission's context matches the specified one
            if (m.getContext() != null && m.getContext().toString().equalsIgnoreCase(context)) {
                result.add(m);
            }
        }
        return result.toArray(new Mission[0]);
    }
    /**
     * Returns the average number of missions per aircraft type for a specific mission type
     * @param missionType The type of mission to calculate averages for
     * @param aircraftTypes Array of aircraft types to consider
     * @return Array of averages corresponding to each aircraft type
     */
    public double[] getAverageMissionsPerAircraftType(MissionType missionType, String[] aircraftTypes) {
        double[] averages = new double[aircraftTypes.length];

        for (int i = 0; i < aircraftTypes.length; i++) {
            String currentType = aircraftTypes[i];
            int missionCount = 0;
            Set<String> uniqueAircraftIds = new HashSet<>();

            for (Mission m : getAllMissions()) {
                if (m.getType() == missionType &&
                        m.getAircraft() != null &&
                        m.getAircraft().getClass().getSimpleName().equals(currentType)) {

                    missionCount++;
                    uniqueAircraftIds.add(m.getAircraft().getIdAircraft());
                }
            }

            int aircraftCount = uniqueAircraftIds.size();
            averages[i] = aircraftCount == 0 ? 0 : (double) missionCount / aircraftCount;
        }

        return averages;
    }
}


