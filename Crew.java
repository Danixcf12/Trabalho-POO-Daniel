

public class Crew{
    //---------Variables----------
    protected MissionType missionType;

    private static float counterNumberOfNATIONALMissions = 0;
    private static float counterNumberOfNATOMissions = 0;
    private static float counterNumberOfONUMissions = 0;
    //--------constructors----------

    /** This Constructor return a Crew with a Aircraft, a Pilot and the Mission type
     * @param aircraft The Aircraft that will participate in this mission
     * @param pilot the mission pilot
     * @param missionType The type of mission (NATIONAL, NATO OR ONU)
     */
    public Crew(Aircraft aircraft, Pilot pilot, MissionType missionType){
        this.missionType = missionType;

        aircraft.increaseNumberOfMissionToEach(this.missionType);
        increaseNumberMissionType();
    }
    


    //--------Methods---------

    public MissionType getMissionType(){
        return missionType;
    }

    /**This method will increase in 1 the value the counter of mission that
     * 
     */
    private void increaseNumberMissionType(){
        if(this.missionType == MissionType.NATIONAL){
            counterNumberOfNATIONALMissions++;
        }
        else if(this.missionType == MissionType.NATO){
            counterNumberOfNATOMissions++;
        }
        else if(this.missionType == MissionType.ONU){
            counterNumberOfONUMissions++;
        }
    }

    /**This method will calculate the average number of missions to each type of aircrafts
     *
     */
    public static double averageNumberMissionTransportation(MissionType tempMissionType){
        if(tempMissionType == MissionType.NATIONAL){
            return (counterNumberOfNATIONALMissions / Transportation.getnumberOfTransportationCreated());
        }
        else if(tempMissionType == MissionType.NATO){
            return (counterNumberOfNATOMissions / Transportation.getnumberOfTransportationCreated());
        }
        else if(tempMissionType == MissionType.ONU){
            return (counterNumberOfONUMissions / Transportation.getnumberOfTransportationCreated());
        }
        else{
            return 0;
        }
    }
    
}
