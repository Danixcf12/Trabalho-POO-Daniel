public class Main {
    
    public static void main(String[] Args){

    Transportation nave1 = new Transportation("TAP", null, null, null, null, null, 0, 0, 0);
    Transportation nave4 = new Transportation("TAP", null, null, null, null, null, 0, 0, 0);

    // System.out.println(nave1.getIdAircraft());
    // System.out.println(nave2.getIdAircraft());
    // System.out.println(nave3.getIdAircraft());

    Pilot antonio = new Pilot("Antonio", "Piloto");

    // System.out.println(antonio.getRank());

    Crew missão1 = new Crew(nave1, antonio, MissionType.NATO);
    Crew missão2 = new Crew(nave1, antonio, MissionType.NATO);
    Crew missão3 = new Crew(nave4, antonio, MissionType.NATO);
    Crew missão4 = new Crew(nave4, antonio, MissionType.NATO);
    Crew missão5 = new Crew(nave1, antonio, MissionType.NATO);

    System.out.println("O numero de missoes NATO que essa aeronave participou é: " + nave1.getNumberOfMissionEachType(MissionType.NATO));

    System.out.println("O número médio de missões NATO realizadas pelos transportadores é: " + Crew.averageNumberMissionTransportation(MissionType.NATO));

}

}
