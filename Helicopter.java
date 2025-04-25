
import java.util.Date;

public class Helicopter extends Aircraft{

    //--------Variables----------

    private static int numberOfHelicopterCreated = 0;

    //---------Constructors----------

    public Helicopter(String manufacturer, Date incorporationDate, Date lastInspectionDate, Date nextInspectionDate, Date lastMaintenanceDate, Date nextMaintenanceDate, float wingspan, float weight, float maxSpeed) {
        super(manufacturer, incorporationDate, lastInspectionDate, nextInspectionDate, lastMaintenanceDate, nextMaintenanceDate, wingspan, weight, maxSpeed);

        numberOfHelicopterCreated++;
    }

    //------------methods-----------

    public int getnumberOfTransportationCreated(){
        return numberOfHelicopterCreated;
    }

}
