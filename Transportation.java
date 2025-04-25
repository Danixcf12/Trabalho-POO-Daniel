
import java.util.Date;

public class Transportation extends Aircraft{

    //---------Variables---------

    private static int numberOfTransportationCreated = 0;

    //----------Contructors-----------

    public Transportation(String manufacturer, Date incorporationDate, Date lastInspectionDate, Date nextInspectionDate, Date lastMaintenanceDate, Date nextMaintenanceDate, float wingspan, float weight, float maxSpeed) {
        super(manufacturer, incorporationDate, lastInspectionDate, nextInspectionDate, lastMaintenanceDate, nextMaintenanceDate, wingspan, weight, maxSpeed);

        numberOfTransportationCreated++;
    }

    //----------methods----------

    public static int getnumberOfTransportationCreated(){
        return numberOfTransportationCreated;
    }

}
