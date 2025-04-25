public class CrewMember {
    //---------Variables----------
    protected int memberId;
    protected String name;
    protected String rank;

    private static int lastId = 0;

    //--------constructors----------

    /** Contructor that return a crew member with all the paramethers bellow, in addition add a automatic and unique Id to each crew member
     * @param Name The crew member name
     * @param rank the crew member rank
     */
    public CrewMember(String Name, String rank){
        this.name = name;
        this.rank = rank;

        lastId++;
        memberId = lastId;
    }
    


    //--------Methods---------
    public String getRank(){
        return rank;
    }


}
