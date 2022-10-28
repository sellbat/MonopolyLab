import javax.print.attribute.standard.OrientationRequested;
import java.security.PKCS12Attribute;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Game game;
    static BoardSpace MediterraneanAvenue = new BoardSpace("Mediterranean Avenue",60,2,30,true,false,"brown");
    static BoardSpace BalticAvenue = new BoardSpace("Baltic Avenue",60,4,30,true,false,"brown");
    static BoardSpace ReadingRR = new BoardSpace("Reading RR",200,25,30,true,false,"rail");
    static BoardSpace OrientalAvenue = new BoardSpace("OrientalAvenue",100,6,30,true,false,"light blue");
    static BoardSpace VermontAvenue = new BoardSpace("Vermont Avenue",100,6,30,true,false,"light blue");
    static BoardSpace ConnecticutAvenue = new BoardSpace("Connecticut Avenue",120,8,30,true,false,"light blue");
    static BoardSpace StCharlesPlace = new BoardSpace("St. Charles Place",140,10,30,true,false,"pink");
    static BoardSpace ElectricCompany = new BoardSpace("Electric Company",150,1,30,true,false,"util");
    static BoardSpace StatesAvenue = new BoardSpace("States Avenue",140,10,30,true,false,"pink");
    static BoardSpace VirginiaAvenue = new BoardSpace("Virginia Avenue",160,12,30,true,false,"pink");
    static BoardSpace PennsylvaniaRR = new BoardSpace("Pennsylvania RR",200,25,30,true,false,"rail");
    static BoardSpace StJamesPlace = new BoardSpace("St. James Place",180,14,30,true,false,"orange");
    static BoardSpace TennesseeAvenue = new BoardSpace("Tennessee Avenue",180,14,30,true,false,"orange");
    static BoardSpace NewYorkAvenue = new BoardSpace("New York Avenue",200,16,30,true,false,"orange");
    static BoardSpace KentuckyAvenue = new BoardSpace("Kentucky Avenue",220,18,30,true,false,"red");
    static BoardSpace IndianaAvenue = new BoardSpace("Indiana Avenue",220,18,30,true,false,"red");
    static BoardSpace IllinoisAvenue = new BoardSpace("Illinois Avenue",240,20,30,true,false,"red");
    static BoardSpace BORR = new BoardSpace("B&O RR",200,25,30,true,false,"rail");
    static BoardSpace AtlanticAvenue = new BoardSpace("Atlantic Avenue",260,22,30,true,false,"yellow");
    static BoardSpace VentnorAvenue = new BoardSpace("Ventnor Avenue",260,22,30,true,false,"yellow");
    static BoardSpace WaterWorks = new BoardSpace("Water Works",150,1,30,true,false,"util");
    static BoardSpace MarvinGardens = new BoardSpace("Marvin Gardens",280,24,30,true,false,"yellow");
    static BoardSpace PacificAvenue = new BoardSpace("Pacific Avenue",300,26,30,true,false,"green");
    static BoardSpace NorthCarolinaAvenue = new BoardSpace("North Carolina Avenue",300,26,30,true,false,"green");
    static BoardSpace PennsylvaniaAvenue = new BoardSpace("Pennsylvania Avenue",320,28,30,true,false,"green");
    static BoardSpace ShortLine = new BoardSpace("Short Line",200,25,30,true,false,"rail");
    static BoardSpace ParkPlace = new BoardSpace("Park Place",350,35,30,true,false,"dark blue");
    static BoardSpace Boardwalk = new BoardSpace("Boardwalk",400,50,30,true,false,"dark blue");
    static CircularLinkedList<BoardSpace> board = new CircularLinkedList();
    static CircularLinkedList<Player> pieces = new CircularLinkedList();
    static BoardSpace Go = new BoardSpace("Go",0,0,0,false,false,"weird");
    static BoardSpace CC1 = new BoardSpace("Community Chest",0,0,0,false,false,"weird");
    static BoardSpace Income = new BoardSpace("Income Tax",0,200,0,false,false,"weird");
    static BoardSpace Ch1 = new BoardSpace("Chance",0,0,0,false,false,"weird");
    static BoardSpace Jail = new BoardSpace("Jail",0,0,0,false,true,"weird");
    static BoardSpace CC2 = new BoardSpace("Community Chest",0,0,0,false,false,"weird");
    static BoardSpace free = new BoardSpace("Free Parking",0,0,0,false,false,"weird");
    static BoardSpace Ch2 = new BoardSpace("Chance",0,0,0,false,false,"weird");
    static BoardSpace GoToJail = new BoardSpace("Go To Jail",0,0,0,false,false,"weird");
    static BoardSpace CC3 = new BoardSpace("Community Chest",0,0,0,false,false,"weird");
    static BoardSpace Ch3 = new BoardSpace("Chance",0,0,0,false,false,"weird");
    static BoardSpace Luxury = new BoardSpace("Luxury Tax",0,100,0,false,false,"weird");
    public static int roll(){

        return (int)(Math.random()*6+1);
    }
    public static void premove(Link<Player> current){
        System.out.println("Would "+current.data.getName()+" like to do anything before rolling");
        Scanner input= new Scanner(System.in);
        String ready = input.next();
        if (ready.equals("yes")) {
            if (!current.data.getProperties().isEmpty()) {
                System.out.println("Would " + current.data.getName() + " like to sell any of your properties?");
                String seller = input.next();
                if (seller.equals("yes") || seller.equals("Yes")) {
                    System.out.println("Select which property by number");
                    for (int i = 0; i < current.data.getProperties().size(); i++) {
                        System.out.println(i + 1 + ": " + current.data.getProperties().get(i).getName());
                    }
                    int sold = input.nextInt() - 1;
                    game.sell(current.data.getProperties().get(sold), current.data);
                    premove(current);
                    return;
                }
                System.out.println("Would you like to mortgage any of your properties?");
                String morter = input.next();
                if (morter.equals("yes") || morter.equals("Yes")) {
                    System.out.println("Select which property by number");
                    boolean any = false;
                    for (int i = 0; i < current.data.getProperties().size(); i++) {
                        if (!current.data.getProperties().get(i).isMortgaged()) {
                            any = true;
                            System.out.println(i + 1 + ": " + current.data.getProperties().get(i).getName());
                        }
                    }
                    if (any) {
                        int mortgaged = input.nextInt() - 1;
                        game.mortgage(current.data.getProperties().get(mortgaged), current.data);
                        premove(current);
                        return;
                    } else {
                        System.out.println("You have no mortgagable land");
                        premove(current);
                        return;
                    }
                }
                System.out.println("Would you like to unmortgage any of your properties?");
                String unmorter = input.next();
                if (unmorter.equals("yes") || unmorter.equals("Yes")) {
                    System.out.println("Select which property by number");
                    boolean any = false;
                    for (int i = 0; i < current.data.getProperties().size(); i++) {
                        if (current.data.getProperties().get(i).isMortgaged()) {
                            any = true;
                            System.out.println(i + 1 + ": " + current.data.getProperties().get(i).getName());
                        }
                    }
                    if (any) {
                        int unmortgaged = input.nextInt() - 1;
                        game.unMortgage(current.data.getProperties().get(unmortgaged), current.data);
                        premove(current);
                        return;
                    } else {
                        System.out.println("You have no unmortgagable land");
                        premove(current);
                        return;
                    }
                }
                System.out.println("Would you like to upgrade any of your properties?");
                String upgrader = input.next();
                if (upgrader.equals("yes") || upgrader.equals("Yes")) {
                    String owned = "brown darkblue ";
                    for (int i = 0; i < current.data.getProperties().size(); i++) {
                        String color = current.data.getProperties().get(i).getColor();
                        if (color.equals("light blue")) {
                            color = "lightblue";
                        }
                        if (color.equals("dark blue")) {
                            color = "darkblue";
                        }
                        owned = owned + color + " ";
                    }
                    while (owned.length() > 1) {
                        String color = owned.substring(0, owned.indexOf(' '));
                        String copy = owned;
                        int count = 0;
                        while (copy.length() > 1) {
                            if (copy.substring(0, copy.indexOf(' ')).equals(color)) {
                                count++;
                            }
                            copy = copy.substring(copy.indexOf(' ') + 1);
                        }
                        if (count == 3) {
                            if (color.equals("darkblue")) {
                                color = "dark blue";
                            }
                            if (color.equals("lightblue")) {
                                color = "light blue";
                            }
                            System.out.println("Would you like to upgrade a " + color + " property?");
                            upgrader = input.next();
                            if (upgrader.equals("yes") || upgrader.equals("Yes")) {
                                System.out.println("Select which property by number");
                                for (int i = 0; i < current.data.getProperties().size(); i++) {
                                    if (current.data.getProperties().get(i).getColor().equals(color)) {
                                        System.out.println(i + 1 + ": " + current.data.getProperties().get(i).getName());
                                    }
                                }
                                int upgrade = input.nextInt() - 1;
                                int price = 0;
                                current.data.getProperties().get(upgrade).setHouseNum(current.data.getProperties().get(upgrade).getHouseNum() + 1);
                                if (current.data.getProperties().get(upgrade).getColor().equals("brown") || current.data.getProperties().get(upgrade).getColor().equals("light blue")) {
                                    price = 50;
                                }
                                if (current.data.getProperties().get(upgrade).getColor().equals("pink") || current.data.getProperties().get(upgrade).getColor().equals("orange")) {
                                    price = 100;
                                }
                                if (current.data.getProperties().get(upgrade).getColor().equals("red") || current.data.getProperties().get(upgrade).getColor().equals("yellow")) {
                                    price = 150;
                                }
                                if (current.data.getProperties().get(upgrade).getColor().equals("green") || current.data.getProperties().get(upgrade).getColor().equals("dark blue")) {
                                    price = 200;
                                }
                                current.data.setMoney(current.data.getMoney() - price);
                                premove(current);
                                return;
                            }
                        }
                        owned = owned.substring(owned.indexOf(' ') + 1);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        CircularLinkedList<BoardSpace> printBoard = new CircularLinkedList<>();
        BoardSpace[] all = {Go,MediterraneanAvenue,CC1,BalticAvenue,Income,ReadingRR,OrientalAvenue,Ch1,VermontAvenue,ConnecticutAvenue,Jail,StCharlesPlace,ElectricCompany,StatesAvenue,VirginiaAvenue,PennsylvaniaRR,StJamesPlace,CC2,TennesseeAvenue,NewYorkAvenue,free,KentuckyAvenue,Ch2,IndianaAvenue,IllinoisAvenue,BORR,AtlanticAvenue,VentnorAvenue,WaterWorks,MarvinGardens,GoToJail,PacificAvenue,NorthCarolinaAvenue,CC3,PennsylvaniaAvenue,ShortLine,Ch3,ParkPlace,Luxury,Boardwalk};
        for (int i=all.length-1;i>=0;i--){
            Link<BoardSpace> add = new Link(all[i]);
            board.insertFirst(add.data);
        }
        BoardSpace[] allPrint = {free, KentuckyAvenue, Ch2, IndianaAvenue,IllinoisAvenue,BORR,AtlanticAvenue,VentnorAvenue,WaterWorks,MarvinGardens,GoToJail,NewYorkAvenue,PacificAvenue,TennesseeAvenue,NorthCarolinaAvenue,CC2,CC3,StJamesPlace,PennsylvaniaAvenue,PennsylvaniaRR,ShortLine,VirginiaAvenue,Ch3,StatesAvenue,ParkPlace,ElectricCompany,Luxury,StCharlesPlace,Boardwalk,Jail,ConnecticutAvenue,VermontAvenue,Ch1,OrientalAvenue,ReadingRR,Income,BalticAvenue,CC1,MediterraneanAvenue,Go};
        for (int i=all.length-1;i>=0;i--){
            Link<BoardSpace> add = new Link(allPrint[i]);
            printBoard.insertFirst(add.data);
        }
        Link<BoardSpace> GoSpot = new Link<>(Go);

        Player thimble = new Player("Thimble", GoSpot, "P1");
        Player boat = new Player("Boat",GoSpot, "P2");
        Player iron = new Player("Iron",GoSpot, "P3");
        Player topHat = new Player("Top Hat",GoSpot, "P4");
        Player Brantley = new Player("Brantley",GoSpot, "P5");
        Player connor = new Player("Connor",GoSpot, "P4");
        Player[] players = {thimble,boat};
        for (int i=0;i<players.length;i++){
            Link<Player> add = new Link(players[i]);
            pieces.insertFirst(add.data);
        }
        game = new Game(board, pieces);
        game.displayBoard(2,printBoard);
        int counter = 0;
        Link<Player> now = pieces.getFirst();
        while (!game.isGameOver()){
            game.displayBoard(game.getPlayers().size(),printBoard);
            premove(now);
            game.move(now,board);
            game.bankruptcy(now.data);
            now=now.nextLink;
            game.gameOver();
        }
        System.out.println("thanks for playing");
    }

}
