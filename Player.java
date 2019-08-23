import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Player {
    private String name;
    private int money;
    private int worth;
    private int numberOfDoubles;
    private boolean inGame;
    private int location;
    private int timesAroundBoard;
    private boolean isHuman;
    private ArrayList<Property> propertiesOwned;
    private int jailTurns;
    private boolean jailCard;
    private Random random = new Random();
    private Scanner sc = new Scanner(System.in);

    public Player(String name, boolean isHuman) {
        this.name = name;
        this.money = 1500;
        this.worth = 1500;
        this.location = 0;
        this.numberOfDoubles = 0;
        this.timesAroundBoard = 0;
        this.inGame = true;
        this.isHuman = isHuman;
        this.propertiesOwned = new ArrayList<Property>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    public void addMoney(int num) {
        this.money += num;
    }

    public void decreaseMoney(int num) {
        this.money -= num;
    }

    public int getWorth() {
        return this.worth;
    }

    public void addWorth(int num) {
        this.worth += num;
    }

    public void decreaseWorth(int num) {
        this.worth -= num;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    public int getNumberOfDoubles() {
        return this.numberOfDoubles;
    }

    public void setNumberOfDoubles(int numberOfDoubles) {
        this.numberOfDoubles = numberOfDoubles;
    }

    public boolean getInGame() {
        return this.inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public int getLocation() {
        return this.location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getTimesAroundBoard() {
        return this.timesAroundBoard;
    }

    public void setTimesAroundBoard(int timesAroundBoard) {
        this.timesAroundBoard = timesAroundBoard;
    }

    public boolean getIsHuman() {
        return this.isHuman;
    }

    public void playerMenu(Board board) throws InterruptedException {
        int menuChoice = 0;
        boolean hasRolled = false;
        if(this.location == 30)jail(board);
        if(!inGame){
            System.out.println(this.name + " is out of game");
            Thread.sleep(2000);
            return;
        }
        if(!isHuman){
            diceRoll(board);
            return;
        }
        while (menuChoice <=11){
            System.out.println(this.name + " 's turn");
            System.out.println("1) Roll Dice");
            System.out.println("2) Trade(Only With Human Players)");
            System.out.println("3) Print Owned Properties");
            System.out.println("4) Print Worth");
            System.out.println("5) Mortgage Property");
            System.out.println("6) UnMortgage Property");
            System.out.println("7) Add House or Hotel");
            System.out.println("8) Remove House");
            System.out.println("9) Remove Hotel");
            System.out.println("10) Print Available Properties");
            System.out.println("11) End Turn");
            System.out.println("Select Choice:");
            menuChoice = sc.nextInt();

            if(menuChoice == 1 && !hasRolled){
                hasRolled = true;
                try {
                    diceRoll(board);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(menuChoice == 2) trade();
            if(menuChoice == 3) printProperties();
            if(menuChoice == 4) printWorth();
            if(menuChoice == 5) mortgageProperty(board);
            if(menuChoice == 6) unMortgage(board);
            if(menuChoice == 7) addHouseOrHotel(board);
            if(menuChoice == 8) removeHouse(board);
            if(menuChoice == 9) removeHotel(board);
            if(menuChoice == 10) printAvailableProperties(board);
            if(menuChoice == 11){
                if (hasRolled)break;
                else System.out.println("Cannot end turn, did not roll yet");
            }

        }
    }
    private void printAvailableProperties(Board board){
        System.out.println();
        for(int counter = 0; counter < 40; counter++){
            Property p = board.propLand(counter);
            if(p.getBuyable()){
                System.out.print(p.getName() + ",   ");
            }
            if(counter%8 == 0) System.out.println(); //so it doesn't print out in one straight line
        }
        System.out.println();
    }
    private void printWorth(){
        calculateWorth();
        System.out.println("Worth: " + this.worth);
    }
    private void diceRoll(Board board) throws InterruptedException {

        calculateWorth();//calculates players worth before rolling
        bankcrucpy(); //checks if bankrupt

        Thread.sleep(3000);

        int diceOne = random.nextInt(6) + 1;
        int diceTwo = random.nextInt(6) + 1;

        System.out.println("It's " + this.name + " turn to roll");
        System.out.println(this.name + " rolled a : " + (diceOne + diceTwo) + "  (" + diceOne + " + " + diceTwo + ")");
        Thread.sleep(2000);
        if (this.location == 30) {
            System.out.println(this.name + " is now in jail");
            jail(board);
            return; //end turn
        }
        if (diceOne == diceTwo) {
            this.numberOfDoubles++;
        } else {
            this.numberOfDoubles = 0;
        }
        if (this.numberOfDoubles == 3) {
            System.out.println("Three doubles, you must be cheating!");
            this.location = 30;
            jail(board);
        }
        if (this.location + (diceOne + diceTwo) >= 40) {
            int placeHolder = 40 - this.location; //distance between current location and GO
            this.location = diceOne + diceTwo - placeHolder;
            this.money += 200;
            this.timesAroundBoard ++;
        } else {
            this.location += (diceOne + diceTwo);
        }

        Property location = board.propLand(this.location - 1);
        System.out.println(this.name + " landed on: " + location.getName());
        Thread.sleep(2000);

        if (location.getBuyable() && this.money>= board.propLand(this.location-1).getPrice() && location.getPrice() != 0) {
            int input = 1;
            if(isHuman) {
                System.out.println(location.getName() + " is not owned, would you like to purchase it? (1 or 2)");
                input = sc.nextInt();
            }else{
                input = random.nextInt(2) + 1;
            }
            if (input == 1) {
                board.propLand(this.location - 1).setBuyable(false, this.name);
                System.out.println(this.name + " has purchased " + location.getName());
                this.money -= board.propLand(this.location - 1).getPrice();
                addProperty(location);
            } else {
                System.out.println(this.name + " did not purchase " + location.getName());
            }
        } else if(location.getPrice() != 0){ //if the property landed on can have rent
            this.money -= payRent(board, (diceOne + diceTwo));
        }

        if(board.propLand(this.location - 1).getTax()){
            System.out.println(this.name + " lost " + board.propLand(this.location - 1).getRent());
            this.money -= board.propLand(this.location - 1).getRent();

        }
        if(board.propLand(this.location - 1).getChance()){
            if(this.location -1 == 7 || this.location - 1 == 22|| this.location-1 == 35){
                Chance card = board.getCard();
                System.out.println(this.name + " got " + card.getName());
                this.location += card.getChangeLocation();
                this.money += card.getChangeMoney();
                //deal with jail card later
                board.reorderChance();
            }else{
                CommunityChest card = board.getCommunityCard();
                System.out.println(this.name + " got " + card.getName());
                this.location += card.getChangeLocation();
                this.money += card.getChangeMoney();
                board.reoderCommunityChest();
            }

        }
        Thread.sleep(1000);
        System.out.println(this.name + " has " + this.money);
        if(timesAroundBoard > 4) { //5 times around the board before houses can be purchased
            if (!isHuman) {
                addHouseComputer(board);
            }
        }
        if (this.numberOfDoubles > 0) {
            this.diceRoll(board);
        }

        Thread.sleep(4000);
    }

    private void addProperty(Property property) {
        propertiesOwned.add(property);
    }
    private void printProperties(){
        for(int i = 0; i < propertiesOwned.size(); i ++){
            System.out.println(propertiesOwned.get(i).getName());
        }
    }
    private void jail(Board board){
        this.jailTurns ++;
        System.out.println("There are three options: pail bail, roll a double, or use a get out of jail free card(1,2,3)");
        int choice = 1;
        if(isHuman){
            choice = sc.nextInt();
        }
        if(!isHuman){ //computer player randomly picks a choice
            choice = random.nextInt(3);
            if(choice == 0){
                choice ++;
            }
        }
        if(jailTurns == 3){
            System.out.println("Third turn in jail, have to pay bail");
            choice = 1;
        }
        if(choice == 1){
            this.money-=50;
            this.location = 10;
            System.out.println("Payed $50");
            this.jailTurns = 0;
        }else if(choice == 2){
            int diceOne = random.nextInt(6);
            int diceTwo = random.nextInt(6);
            System.out.println(this.name + " rolled a " + (diceOne + diceTwo));
            if(diceOne == diceTwo){
                System.out.println(this.name + " rolled a double");
                this.location = 10;
                this.jailTurns = 0;
            }else{
                System.out.println("Failed to roll a double, still in jail");
            }
        }else if(choice == 3){
            if(this.jailCard){
                System.out.println("Used a Get out of Jail Free Card");
                this.jailCard = false;
                this.location = 10;
                this.jailTurns = 0;
            }else{
                System.out.println("Cannot do this action, still in jail");
            }
        }
    }
    private int payRent(Board board, int diceRoll) throws InterruptedException {
        int rent = 0;
        if(this.location == 12 || this.location == 28){//land on utilities
            if(board.propLand(12).getOwner().equals(board.propLand(28).getOwner())){
                rent = diceRoll * 10; //monopoly
            }else {
                rent = diceRoll * 4;
            }
            return rent; //returns rent of utility
        }
        String owner = board.propLand(this.location - 1).getOwner();
        String colorLandedOn = board.propLand(this.location -1).getColor();

        boolean monopoly = true;
        ArrayList <Property> sameColor = new ArrayList<Property>();
        //Create arraylist of all properties of the same color as the property landed on
        //compare the owners of all of them, if they are all equal, monopoly becomes true
        for(int i = 0; i < 40; i ++){
            if(board.propLand(i).getColor().equals(colorLandedOn)) {
                Property addProp = board.propLand(i);
                sameColor.add(addProp);
            }
        }
        for(int j = 0; j < sameColor.size(); j ++){
            if(sameColor.get(j).getOwner() != null && !sameColor.get(j).getOwner().equals(owner)){
                monopoly = false;
            }
        }
        int counter = 0;

        if(colorLandedOn.equals("Railroad")){ //if the property is a railroad
            for(int c = 0; c < sameColor.size(); c++){
                if(sameColor.get(c).getOwner() != null && sameColor.get(c).getOwner().equals(owner)){
                    counter++;
                }
            }
        }
        if(counter !=0){ //Railroad rent based on the number of owned railroads
           if(counter == 1){
               board.findPlayer(owner).addMoney(25);
               return 25;
           }else if(counter == 2){
               board.findPlayer(owner).addMoney(50);
               return 50;
           }else if(counter == 3){
               board.findPlayer(owner).addMoney(100);
               return 100;
           }else{
               board.findPlayer(owner).addMoney(200);
               return 200;
           }
        }

        if(monopoly && board.propLand(this.location - 1).getNumberOfHouses() <1){
            rent = board.propLand(this.location - 1).getRent() * 2; //rent if owner has a monopoly
        }else{
            rent = board.propLand(this.location -1).getRent();
        }

        if(owner != null) { //avoids null pointer exception
            board.findPlayer(owner).addMoney(board.propLand(this.location - 1).getRent());
            Thread.sleep(1000);
            System.out.println(owner + " now has " + board.findPlayer(owner).getMoney());
        }
        return rent;
    }
    private void bankcrucpy(){
        if(worth<=0){
            this.inGame = false; //if player is bankrupt, out of the game
        }
    }
    private void calculateWorth(){
        this.worth = 0;//reset worth before recalculating it
        this.worth = this.money;
        for(int i = 0; i < propertiesOwned.size(); i ++){
            this.worth+= propertiesOwned.get(i).getPrice() / 2;
            int houses = propertiesOwned.get(i).getNumberOfHouses();
            if(houses !=0){
                this.worth += (houses * propertiesOwned.get(i).getHouseCost());
            }
        }   //worth is calculated by money and assets
    }
    private void mortgageProperty(Board board){
        printProperties();
        System.out.println("Which Property: ");
        int choice = sc.nextInt();
        if(choice < 0 || choice>propertiesOwned.size()){
            System.out.println("Error try again");
            return;
        }
        board.propLand(propertiesOwned.get(choice).getLocationNum()-1).setRent(0);//sets rent to 0
        this.money += board.propLand(propertiesOwned.get(choice).getLocationNum()-1).getPrice()/2;

    }
    private void addHouseOrHotel(Board board){
        System.out.println("Enter Color Location of where to add the house: ");
        String colorLandedOn = sc.nextLine();
        boolean monopoly = true;
        ArrayList <Property> sameColor = new ArrayList<Property>();
        //Create arraylist of all properties of the same color as the property landed on
        //compare the owners of all of them, if they are all equal, monopoly becomes true
        for(int i = 0; i < 40; i ++){
            if(board.propLand(i).getColor().equals(colorLandedOn)) {
                Property addProp = board.propLand(i);
                sameColor.add(addProp);
            }
        }
        if(sameColor.size() == 0) return;
        for(int j = 0; j < sameColor.size(); j ++){
            if(sameColor.get(j).getOwner() != null && !sameColor.get(j).getOwner().equals(this.name)){
                monopoly = false;
            }
        }
        if(monopoly && !colorLandedOn.equals("Railroad") &&!colorLandedOn.equals("Tax") && !colorLandedOn.equals("Chance") && !colorLandedOn.equals("Go")
        && !colorLandedOn.equals("Jail") && !colorLandedOn.equals("Utilities") && !colorLandedOn.equals("Community Chest")){
            for(int i = 0; i < sameColor.size();  i++){
                System.out.println("How many houses for: " + sameColor.get(i).getName());
                int choice = sc.nextInt();
                if(choice >=0 && choice<5 && sameColor.get(i).getNumberOfHouses() + choice <=4){
                    board.propLand(sameColor.get(i).getLocationNum()-1).addNumberOfHouses(choice);
                    if(sameColor.get(i).getNumberOfHouses() == 1){
                        board.propLand(sameColor.get(i).getLocationNum() - 1).setRent(sameColor.get(i).getRentHouse());
                    }else if(sameColor.get(i).getNumberOfHouses() == 2){
                        board.propLand(sameColor.get(i).getLocationNum() - 1).setRent(sameColor.get(i).getRentHouse2());

                    } else if (sameColor.get(i).getNumberOfHouses() == 3) {
                        board.propLand(sameColor.get(i).getLocationNum() - 1).setRent(sameColor.get(i).getRentHouse3());

                    }else{
                        board.propLand(sameColor.get(i).getLocationNum() - 1).setRent(sameColor.get(i).getRentHouse4());

                    }
                    this.money -= sameColor.get(i).getHouseCost() * choice;
                }
                if(sameColor.get(i).getNumberOfHouses() == 4){
                    System.out.println("Add a house? (1 for Yes, anything else for no");
                    int choice2 = sc.nextInt();
                    if(choice2 == 1){
                        board.propLand(sameColor.get(i).getLocationNum()-1).setNumberOfHouses(5);
                        board.propLand(sameColor.get(i).getLocationNum() -1).setRent(sameColor.get(i).getHouseCost());
                    }
                }
            }
            //loop through properties of the same color
            ///enter how many houses to add on each property
            //increase numofhouses and rent
            //add restrictions based on house limit of 4 and price
            //increase worth
        }else{
            System.out.println("Cannot add house");
        }
    }
    private void addHouseComputer(Board board){
        ArrayList <Property> sameColor = new ArrayList<Property>();
        String arr[] = {"Brown", "LBlue", "Purple", "Orange", "Red", "Yellow", "Green", "Blue"};
        for(int j = 0; j < arr.length; j++){
            for (int i = 0; i < 40; i++) {
                if(board.propLand(i).getColor().equals(arr[j])) {
                    Property addProp = board.propLand(i);
                    sameColor.add(addProp);
                }
            }
        }
    }
    private void removeHouse(Board board){
        System.out.println("What Color: ");
        String color = sc.next();
        ArrayList <Property> sameColor = new ArrayList<Property>();
        //Create arraylist of all properties of the same color as the property landed on
        //compare the owners of all of them, if they are all equal, monopoly becomes true
        for(int i = 0; i < 40; i ++){
            if(board.propLand(i).getColor().equals(color)) {
                Property addProp = board.propLand(i);
                sameColor.add(addProp);
            }
        }
        for(int j = 0; j < sameColor.size(); j++){
            System.out.println("Remove house on :" + sameColor.get(j).getName() + " (1 for yes, anything else for no)");
            int choice = sc.nextInt();
            if(choice == 1){
                int num = board.propLand(sameColor.get(j).getLocationNum() - 1).getNumberOfHouses();
                if(num != 0){
                    System.out.println("How many houses: ");
                    int choice2 = sc.nextInt();
                    if(choice2 <=num){
                        board.propLand(sameColor.get(j).getLocationNum() - 1).setNumberOfHouses(num-choice2);
                        this.money += sameColor.get(j).getHouseCost() * num /2;
                    }
                }
            }
        }
    }
    private void removeHotel(Board board){
        System.out.println("What Color: ");
        String color = sc.next();
        ArrayList <Property> sameColor = new ArrayList<Property>();
        //Create arraylist of all properties of the same color as the property landed on
        //compare the owners of all of them, if they are all equal, monopoly becomes true
        for(int i = 0; i < 40; i ++){
            if(board.propLand(i).getColor().equals(color)) {
                Property addProp = board.propLand(i);
                sameColor.add(addProp);
            }
        }
        for(int j = 0; j < sameColor.size(); j++){
            System.out.println("Remove hotel on :" + sameColor.get(j).getName() + " (1 for yes, anything else for no)");
            int choice = sc.nextInt();
            if(choice == 1){
                int num = board.propLand(sameColor.get(j).getLocationNum() - 1).getNumberOfHouses();
                if(num < 4){
                        board.propLand(sameColor.get(j).getLocationNum() - 1).setNumberOfHouses(0);
                        this.money += sameColor.get(j).getHouseCost() * num /2;
                    }
                }
            }
        }

    private void trade(){
        System.out.println("Property to trade: ");
        printProperties();

    }
    private void unMortgage(Board board){
        //create int array of all the rents from the properties in Board class
        //set the rent to the index of the array
    }

}

