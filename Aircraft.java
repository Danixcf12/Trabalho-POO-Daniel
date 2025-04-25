import java.util.Date;
    
    
    //-------Variables--------

public class Aircraft {
    protected int idAircraft;
    protected String manufacturer;
    protected Date incorporationDate;
    protected Date lastInspectionDate;
    protected Date nextInspectionDate;
    protected Date lastMaintenanceDate;
    protected Date nextMaintenanceDate;
    protected float wingspan;
    protected float weight;
    protected float maxSpeed;

    private static int lastId = 0;

    private int countNATIONALMissions = 0;
    private int countNATOMissions = 0;
    private int countONUMissions = 0;

    
        
    //--------Constructors---------
    
    /** Construct tha return a Aircraft with all the parameters bellow, also add a automatic a unique id to each Aircraft
    * @param idAircraft The Aircraft's ID
    * @param manufacturer The aircraft's manufacturer
    * @param incorporationDate The incorporation date
    * @param lastInspectionDate The last inspenction date
    * @param nextInspectionDate The next inspection date
    * @param lastMaintenanceDate The last maintenance date
    * @param nextMaintenanceDate The next maintenance date
    * @param wingspan The aircraft's wingspan
    * @param weight The aircraft's weight
    * @param maxSpeed The aircraft's max speed
    */
    public Aircraft(String manufacturer, Date incorporationDate, Date lastInspectionDate,
            Date nextInspectionDate, Date lastMaintenanceDate, Date nextMaintenanceDate, float wingspan, float weight,
            float maxSpeed) {
        this.manufacturer = manufacturer;
        this.incorporationDate = incorporationDate;
        this.lastInspectionDate = lastInspectionDate;
        this.nextInspectionDate = nextInspectionDate;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.wingspan = wingspan;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        
        lastId++;
        idAircraft = lastId;
        
        }
    
        
    //---------Methods-------

        /** Method to get the aircraft ID number
         * @return the AircraftID
         */
        public int getIdAircraft(){
            return idAircraft;
        }

        /** Method to get the Aircraft incorporation date
         * @return The incorporation date
         */
        public Date getIncorporationDate() {
            return incorporationDate;
        }
        
        /** Method to get the next Aircraft inspection date
         * @return the next inspenction date
         */
        public Date getNextInspectionDate() {
            return nextInspectionDate;
        }
    
        /** Method to get the Aircraft's manufacturer
         * @return the Aircraft's manufacturer
         */
        public String getManufacturer() {
            return manufacturer;
        }

        /** Method to increase the counter of missions of each in 1 value
         * 
         */
        public void increaseNumberOfMissionToEach(MissionType tempMissionType){
            if(tempMissionType == MissionType.NATIONAL){
                countNATIONALMissions++;
            }
            else if(tempMissionType == MissionType.NATO){
                countNATIONALMissions++;
            }
            else if(tempMissionType == MissionType.ONU){
                countONUMissions++;
            }
        }

        public int getNumberOfMissionEachType(MissionType tempMissionType){
            if(tempMissionType == MissionType.NATIONAL){
                return (countNATIONALMissions);
            }
            else if(tempMissionType == MissionType.NATO){
                return (countNATIONALMissions);
            }
            else if(tempMissionType == MissionType.ONU){
                return (countONUMissions);
            }
            else{
                return 0;
            }
        }

    }
    
        
        
