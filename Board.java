import java.util.ArrayList;
import java.util.Collections;
public class Board {
    private ArrayList <Player> players;
    private ArrayList <Property>property;
    private ArrayList <Chance> chance;
    private ArrayList <CommunityChest> communityChest;

    public Board(){
        players = new ArrayList <Player>();
        property = new ArrayList<Property>();
        chance = new ArrayList<Chance>();
        communityChest = new ArrayList<CommunityChest>();
    }
    public void addPlayer(Player name){
        players.add(name);
    }
    public Player getPlayer(int n){
        return players.get(n);
    }
    public void shufflePlayerNames(){
        Collections.shuffle(players);
        System.out.print("Order of Players: ");
        for(Player p: players) System.out.print(p.getName() + ", ");

        System.out.println();
    }
    public Player findPlayer(String name){
        for(int i =0; i < players.size(); i ++){
            if(name.equals(players.get(i).getName()) && players.get(i)!= null){
                return players.get(i);
            }
        }
        return null;
    }
    public int numPlayers(){
        return players.size();
    }
    public void createChanceCards(){
        Chance card1  = new Chance("Pay poor tax of $15", -15, 0, false);
        Chance card2 = new Chance("Get a get out of jail free card", 0,0, true);
        Chance card3 = new Chance("Go directly to Jail", 0, 30, false);
        Chance card4 = new Chance("You sold your remaining tech stocks, collect $14", 14, 0, false);
        Chance card5 = new Chance("Bank pays you dividend of $50", 50, 0, false);
        Chance card6 = new Chance("Your building and loan matures, collect $150", 150, 0, false);
        Chance card7 = new Chance("Take a walk on the Boardwalk", 0, 39, false);
        Chance card8 = new Chance("Advance to St. Charles Place", 0, 11, false);
        Chance card9 = new Chance("Advance to Illinois Avenue", 0, 24, false);
        Chance card10 = new Chance("Advance to Go", 200, 0, false);
        Chance card11 = new Chance("Take a ride on the Reading Railroad", 0, 5, false);
        Chance card12 = new Chance("You gave been elected chairman of the board of director, pay $50", -50, 0, false);
        Chance card13 = new Chance("Move back three spaces", 0, -3, false);
        chance.add(card1);
        chance.add(card2);
        chance.add(card3);
        chance.add(card4);
        chance.add(card5);
        chance.add(card6);
        chance.add(card7);
        chance.add(card8);
        chance.add(card9);
        chance.add(card10);
        chance.add(card11);
        chance.add(card12);
        chance.add(card13);
    }
    public void createCommunityChest(){
        CommunityChest card1 = new CommunityChest("Life insurance matures. Collect $100", 100, 0);
        CommunityChest card2 = new CommunityChest("Bank error in your favor. Collect $200", 200, 0);
        CommunityChest card3 = new CommunityChest("Advance to Go. Collect $200", 200, 0);
        CommunityChest card4 = new CommunityChest("Income tax refund. Collect $20", 20, 0);
        CommunityChest card5 = new CommunityChest("Receive for Services $25", 25, 0);
        CommunityChest card6 = new CommunityChest("You inherit $100!", 100, 0);
        CommunityChest card7 = new CommunityChest("Pay Doctors fee of $50", -50, 0);
        CommunityChest card8 = new CommunityChest("Pay hospital fee of $100", 100, 0);
        CommunityChest card9 = new CommunityChest("Bank error in your favor. Collect $200", 200, 0);
        CommunityChest card10 = new CommunityChest("You have won second at a beauty contest, collect $10", 10, 0);
        CommunityChest card11 = new CommunityChest("Christmas funds mature, colect $100", 100, 0);
        CommunityChest card12 = new CommunityChest("Go directly to Jail", 0, 30);
                //"It is your birthday. Collect $10 from every player", 10, None, None))
        communityChest.add(card1);
        communityChest.add(card2);
        communityChest.add(card3);
        communityChest.add(card4);
        communityChest.add(card5);
        communityChest.add(card6);
        communityChest.add(card7);
        communityChest.add(card8);
        communityChest.add(card9);
        communityChest.add(card10);
        communityChest.add(card11);
        communityChest.add(card12);

    }
    public void shuffleChance(){
        Collections.shuffle(chance);
    }
    public void shuffleCommunityChest(){
        Collections.shuffle(communityChest);
    }
    public void reorderChance(){
        Chance temp = chance.get(0);
        chance.remove(0);
        int size = chance.size(); //might need to remove (-1)
        chance.add(size, temp);
    }
    public void reoderCommunityChest(){
        CommunityChest temp = communityChest.get(0);
        communityChest.remove(0);
        int size = communityChest.size();
        communityChest.add(size, temp);
    }
    public Chance getCard(){
        return this.chance.get(0);
    }
    public CommunityChest getCommunityCard(){
        return this.communityChest.get(0);
    }
    public void populateBoard(){
        Property Go = new Property("Go", 0, false, 0,false, false, "Go", 40, 0, 0, 0, 0, 0, 0, 0);
        Property property1 = new Property("Mediterranean Avenue", 60, true, 2, false, false, "Brown", 1, 10, 30, 90, 160, 250, 0, 50);
        Property property2 = new Property("Community Chest", 0, false, 0, false, true, "Community Chest",2, 0, 0, 0, 0,0,0, 0);
        Property property3 = new Property("Baltic Avenue", 60, true, 4, false, false, "Brown", 3, 20, 60, 180, 320, 450, 0, 50);
        Property property4 = new Property("Income Tax", 0, false, 200, true,false, "Tax", 4, 0, 0, 0, 0, 0, 0, 0); // or 10%
        Property property5 = new Property("Reading Railroad", 200, true, 25, false, false, "Railroad", 5, 0, 0, 0, 0, 0, 0, 0);
        Property property6 = new Property("Oriental Avenue", 100, true, 6, false, false, "LBlue", 6, 30, 90, 270, 400, 550, 0, 50);
        Property property7 = new Property("Chance", 0, false, 0, false, true, "Chance", 7, 0, 0, 0, 0, 0, 0, 0);
        Property property8 = new Property("Vermont Avenue", 100, true, 6, false, false, "LBlue", 8, 30, 90, 270, 400, 550, 0, 50);
        Property property9 = new Property("Connecticut Avenue", 120, true, 8, false, false, "LBlue", 9, 40, 100, 300, 450, 600, 0, 50);
        Property property10 = new Property("Just Visiting Jail", 0, false, 0, false, false, "Jail", 10, 0, 0, 0, 0, 0, 0, 0);
        Property property11 = new Property("St. Charles Place", 140, true, 10, false, false, "Purple", 11, 50, 150, 450, 625, 750, 0, 100);
        Property property12 = new Property("Electric Company", 150, true, 0, false, false, "Utility", 12, 0, 0, 0, 0, 0, 0, 0);
        Property property13 = new Property("States Avenue", 160, true, 10, false, false, "Purple", 13, 50, 150, 450, 625, 750, 0, 100);
        Property property14 = new Property("Virginia Avenue", 160, true, 12, false, false, "Purple", 14, 60, 180, 500, 700, 900, 0, 100);
        Property property15 = new Property("Pennyslvania Railroad", 200, true, 25, false, false, "Railroad", 15, 0, 0, 0, 0, 0, 0, 0);
        Property property16 = new Property("St. James Place", 180, true, 14, false, false, "Orange", 16, 70, 200, 550, 750, 950, 0, 100);
        Property property17 = new Property("Community Chest", 0, false, 0, false, true, "Community Chest", 17, 0, 0, 0, 0, 0, 0, 0);
        Property property18 = new Property("Tennessee Avenue", 180, true, 14, false, false, "Orange", 18, 70, 200, 550, 750, 950, 0, 100);
        Property property19 = new Property("New York Avenue", 200, true, 16, false, false, "Orange", 19, 80, 220, 600, 800, 1000, 0, 100);
        Property property20 = new Property("Free Parking", 0, false, 0, false, false, "Free Parking", 20, 0, 0, 0, 0, 0, 0, 0);
        Property property21 = new Property("Kentucky Avenue", 220, true, 18, false, false, "Red", 21, 90, 250, 700, 875, 1050, 0, 150);
        Property property22 = new Property("Chance", 0, false, 0, false, true, "Chance", 22, 0, 0, 0, 0, 0, 0, 0);
        Property property23 = new Property("Indiana Avenue", 220, true, 18, false,false, "Red", 23, 90, 250, 700, 875, 1050, 0, 150);
        Property property24 = new Property("Illinois Avenue", 240, true, 20, false,false, "Red", 24, 100, 300, 750, 925, 1100, 0, 150);
        Property property25 = new Property("B&O Railroad", 200, true, 25, false, false, "Railroad", 25, 0, 0, 0, 0, 0, 0, 0);
        Property property26 = new Property("Atlantic Avenue", 260, true, 22, false, false, "Yellow", 26, 110, 330, 800, 975, 1150, 0, 150);
        Property property27 = new Property("Ventnor Avenue", 260, true, 22, false, false, "Yellow", 27, 110, 330, 800, 975, 1150, 0, 150);
        Property property28 = new Property("Water Works", 150, true, 0, false, false,"Utilities", 28, 0, 0, 0, 0, 0, 0, 0);
        Property property29 = new Property("Marvin Gardens", 280, true, 24, false, false, "Yellow", 29, 120, 360, 850, 1025, 1200, 0, 150);
        Property property30 = new Property("Go To Jail", 0, false, 0, false, false, "Jail", 30, 0, 0, 0, 0, 0, 0, 0);
        Property property31 = new Property("Pacific Avenue", 300, true, 26, false, false, "Green", 31, 130, 390, 900, 1100, 1275, 0, 200);
        Property property32 = new Property("North Carolina Avenue", 300, true, 26, false, false, "Green", 32, 130, 390, 900, 1100, 1275, 0, 200);
        Property property33 = new Property("Community Chest", 0, false, 0, false, true, "Community Chest", 33, 0, 0, 0, 0, 0, 0, 0);
        Property property34 = new Property("Pennsylvania Avenue", 320, true, 28, false, false, "Green", 34, 150, 450, 1000, 1200, 1400, 0, 200);
        Property property35 = new Property("Short Line", 200, true, 25, false, false, "Railroad", 35, 0, 0, 0, 0, 0 ,0, 0);
        Property property36 = new Property("Chance", 0, false, 0, false, true, "Chance", 36, 0, 0, 0, 0, 0, 0, 0);
        Property property37 = new Property("Park Place", 350, true, 35, false, false, "Blue", 37, 175, 500, 1100, 1300, 1500, 0, 200);
        Property property38 = new Property("Luxury Tax", 0, false, 75, true,false, "Tax", 38, 0, 0, 0, 0, 0, 0, 0); // or 10%
        Property property39 = new Property("Boardwalk", 400, true, 50, false, false, "Blue", 39, 200, 600, 1400, 1700, 2000, 0, 200);
        Property []combineBoard = {property1, property2, property3, property4, property5, property6,  property7, property8, property9, property10, property11
                ,property12, property13, property14, property15, property16, property17, property18, property19, property20, property21, property22, property23
                ,property24, property25, property26, property27, property28, property29, property30, property31, property32, property33, property34, property35
                ,property36, property37, property38, property39, Go};

        for(int i = 0; i < combineBoard.length; i ++){
            property.add(combineBoard[i]);
        }
    }


    public Property propLand(int m){
        return property.get(m);
    }
    public void testing(){
        System.out.println(this.property.get(3));
    }
}
