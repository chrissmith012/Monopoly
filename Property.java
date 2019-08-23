public class Property {
    private String name;
    private int price;
    private boolean isBuyable;
    private int rent;
    private boolean tax;
    private boolean chance;
    private String owner;
    private boolean jail;
    private String color;
    private int locationNum;
    private int rentHouse;
    private int rentHouse2;
    private int rentHouse3;
    private int rentHouse4;
    private int rentHotel;
    private int numberOfHouses;
    private int houseCost;
    public Property(String name, int price, boolean isBuyable, int rent, boolean tax, boolean chance, String color, int locationNum,
                    int rentHouse, int rentHouse2, int rentHouse3, int rentHouse4, int rentHotel, int numberOfHouses, int houseCost){
        this.name = name;
        this.price = price;
        this.isBuyable = isBuyable;
        this.rent = rent;
        this.tax = tax;
        this.chance = chance;
        this.color = color;
        this.locationNum = locationNum;
        this.rentHouse = rentHouse;
        this.rentHouse2 = rentHouse2;
        this.rentHouse3 = rentHouse3;
        this.rentHouse4 = rentHouse4;
        this.rentHotel = rentHotel;
        this.numberOfHouses = numberOfHouses;
        this.houseCost = houseCost;
    }
    public String getName(){
        return this.name;
    }
    public boolean getBuyable(){
        return this.isBuyable;
    }
    public void setBuyable(boolean isBuyable, String owner){
         this.isBuyable = isBuyable;
         this.owner = owner;
    }
    public String getOwner(){
        return this.owner;
    }
    public int getRent(){
        return this.rent;
    }
    public int getPrice(){
        return this.price;
    }
    public String getColor(){
        return this.color;
    }
    public boolean getTax(){
        return this.tax;
    }
    public boolean getChance(){
        return this.chance;
    }
    public int getLocationNum(){
        return this.locationNum;
    }
    public void setRent(int rent){
        this.rent = rent;
    }
    public int getRentHouse(){
        return this.rentHouse;
    }
    public int getRentHouse2(){
        return this.rentHouse2;
    }
    public int getRentHouse3(){
        return this.rentHouse3;
    }
    public int getRentHouse4(){
        return this.rentHouse4;
    }
    public int getRentHotel(){
        return this.rentHotel;
    }
    public int getNumberOfHouses(){
        return this.numberOfHouses;
    }
    public int getHouseCost(){
        return this.houseCost;
    }
    public void addNumberOfHouses(int numberOfHouses){
        this.numberOfHouses += numberOfHouses;
    }
    public void setNumberOfHouses(int numberOfHouses){
        this.numberOfHouses = numberOfHouses;
    }

}
