import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws InterruptedException{
        Random rand = new Random();
        ArrayList<String> names = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        board.populateBoard();
        board.createChanceCards();
        board.createCommunityChest();
        board.shuffleChance();
        board.shuffleCommunityChest();

        System.out.println("Welcome to Monopoly");
        int num, compPlayers;
        while(true){
            System.out.print("Enter number of human players: ");
            num = sc.nextInt();
            if(num < 5 && num > 0){
                break;
            }
            System.out.println("Invalid Number");
        }

        for(int i = 0; i < num; i ++){
            System.out.print("Enter player name: ");
            String name = sc.next();
            if(!checkOtherNames(names, name)){
                System.out.println("Invalid, name already exits");
                i--;
            }
            names.add(name);
            Player player = new Player(name, true);
            board.addPlayer(player);
        }
        while(true){
            System.out.print("Enter number of computer players (Max 4): ");
            compPlayers = sc.nextInt();
            if(compPlayers > -1 && num > 1){ //avoid playing with one player
                if(compPlayers < 5) break;
            }else{
                if(compPlayers > 0 && compPlayers < 5)break;
            }

            System.out.println("Invalid Number");
        }
        ArrayList<String> compNames = new ArrayList<String>();
        populateNames(compNames);
        for(int c = 0; c < compPlayers; c++){
            int index = rand.nextInt(compNames.size());
            String name = compNames.get(index);
            compNames.remove(name);
            Player comp = new Player(name, false);
            board.addPlayer(comp);
        }
        Thread.sleep(1000);
        int n = 0;
        board.shufflePlayerNames();
        while(true){
            board.getPlayer(n).playerMenu(board);
            n++;

            if(n == board.numPlayers()){
                n = 0;
            }
            Thread.sleep(1000);
        }

    }
    private static void populateNames(ArrayList<String> compNames){
        compNames.add("Thimble");
        compNames.add("Battleship");
        compNames.add("Shoe");
        compNames.add("Race Car");
        compNames.add("Wheel Barrel");
        compNames.add("Dog");
    }
    private static boolean checkOtherNames(ArrayList<String> arrayList, String s){
        for(String i : arrayList){
            if(s.equals(i)) return false;
        }
        return true;
    }
    // TODO create roll dice function, create loop to start game
}
