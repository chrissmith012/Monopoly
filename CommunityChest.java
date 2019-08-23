public class CommunityChest {
    private String name;
    private int changeMoney;
    private int changeLocation;
    public CommunityChest(String name, int changeMoney, int changeLocation){
        this.name = name;
        this.changeMoney = changeMoney;
        this.changeLocation = changeLocation;
    }
    public String getName(){
        return this.name;
    }
    public int getChangeMoney(){
        return this.changeMoney;
    }
    public int getChangeLocation(){
        return this.changeLocation;
    }
}
