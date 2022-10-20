import javax.print.attribute.standard.OrientationRequested;
import java.security.PKCS12Attribute;

public class Main {
    static BoardSpace MediterraneanAvenue = new BoardSpace("MediterraneanAvenue",60,2,30,true,false,"brown");
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
    static CircularLinkedList board = new CircularLinkedList();
    static CircularLinkedList pieces = new CircularLinkedList();
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
    static Player thimble = new Player("Thimble",board.getFirst());
    static Player boat = new Player("Boat",board.getFirst());
    static Player iron = new Player("Iron",board.getFirst());
    static Player topHat = new Player("Top Hat",board.getFirst());
    public static int roll(){
        return (int)(Math.random()*6+1+Math.random()*6+1);
    }
    public static void main(String[] args) {
        BoardSpace[] all = {Go,MediterraneanAvenue,CC1,BalticAvenue,Income,ReadingRR,OrientalAvenue,Ch1,VermontAvenue,ConnecticutAvenue,Jail,StCharlesPlace,ElectricCompany,StatesAvenue,VirginiaAvenue,PennsylvaniaRR,StJamesPlace,CC2,TennesseeAvenue,NewYorkAvenue,free,KentuckyAvenue,Ch2,IndianaAvenue,IllinoisAvenue,BORR,AtlanticAvenue,VentnorAvenue,WaterWorks,MarvinGardens,GoToJail,PacificAvenue,NorthCarolinaAvenue,CC3,PennsylvaniaAvenue,ShortLine,Ch3,ParkPlace,Luxury,Boardwalk};
        for (int i=all.length-1;i>=0;i--){
            Link<BoardSpace> add = new Link(all[i]);
            board.insertFirst(add);
        }
        Player[] players = {thimble,boat,topHat,iron};
        for (int i=0;i<players.length;i++){
            Link<Player> add = new Link(players[i]);
            pieces.insertFirst(add);
        }
    }
}
