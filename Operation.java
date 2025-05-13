
// Package declaration
package POO;

import java.util.List;

// Interface Operation that defines methods to manage operations related to aircraft and missions
public interface Operation {

    // Returns a list of aircraft incorporated in a given year
    // This method is used to retrieve all aircraft that were added to service in a specific year
    List<Aircraft> getAircraftDetailsByYear(Integer year);

    // Returns a list of missions conducted by a specific aircraft (based on its ID)
    // This method retrieves all missions performed by the aircraft identified by its unique ID
    List<Mission> getMissionReportByAircraft(String idAircraft);

    // Returns a list of missions of a specific type, carried out by a specific type of aircraft
    // This method allows filtering missions based on both the type of aircraft and the mission type (e.g., Rescue, Patrol)
    List<Mission> getAverageMissionsByAircraftTypeAndMissionType(String aircraftType, MissionType missionType);

    // Returns a list of missions performed by a specific aircraft within a specific context (NATIONAL, NATO, UN)
    // This method filters missions performed by an aircraft within a defined mission context (e.g., NATO operation, UN peacekeeping)
    List<Mission> getMissionsByContext(String idAircraft, MissionContext context);

    // Returns a list of missions in which a specific crew member (by their number) participated
    // This method retrieves missions where the given crew member (identified by their number) has been involved
    List<Mission> getMissionsByCrewMember(Integer number);

    // Returns the number of aircraft that performed at least a minimum number of missions in a given year
    // This method counts the number of aircraft that have completed a specified number of missions in a particular year
    Integer getAircraftByMinMissionsInYear(Integer year, Integer minMissions);
}
