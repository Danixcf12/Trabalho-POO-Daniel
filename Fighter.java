
import java.util.Date;

public class Fighter extends Aircraft {

    //--------Variables---------

    private static int numberOfFighterCreated = 0;

    //----------Constructors----------

    public Fighter(String manufacturer, Date incorporationDate, Date lastInspectionDate, Date nextInspectionDate, Date lastMaintenanceDate, Date nextMaintenanceDate, float wingspan, float weight, float maxSpeed) {
        super(manufacturer, incorporationDate, lastInspectionDate, nextInspectionDate, lastMaintenanceDate, nextMaintenanceDate, wingspan, weight, maxSpeed);

        numberOfFighterCreated++;
    }

    //---------Methods---------

    public int getnumberOfFighterCreated(){
        return numberOfFighterCreated;
    }

}
