public class Chance {
    private String name;
    private int changeMoney;
    private int changeLocation;
    private boolean jailCard;
    public Chance(String name, int changeMoney, int changeLocation, boolean jailCard){
        this.name = name;
        this.changeMoney = changeMoney;
        this.changeLocation = changeLocation;
        this.jailCard = jailCard;
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
    public boolean getJailCard(){
        return this.jailCard;
    }



}
