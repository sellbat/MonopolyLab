import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private CircularLinkedList<BoardSpace> map;
    private CircularLinkedList<Player> players;
    private boolean isGameOver;
    private Link<Player> player;

    public CircularLinkedList<BoardSpace> getMap() {
        return map;
    }

    public void setMap(CircularLinkedList<BoardSpace> map) {
        this.map = map;
    }

    public CircularLinkedList<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(CircularLinkedList<Player> players) {
        this.players = players;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Link<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Link<Player> player) {
        this.player = player;
    }

    public Game(CircularLinkedList<BoardSpace> map, CircularLinkedList<Player> players){
        setMap(map);
        setPlayers(players);
    }

    public void move(int moves, Link<Player> currentPlayer){
        Link<BoardSpace> current = currentPlayer.data.getPosition();
        BoardSpace spot = current.data;
        for(int i=0; i<moves; i++){
            current = current.nextLink;
            if(current==map.getFirst() && !player.data.getJailed()){ //adds 200 to balance for passing Go
                player.data.setMoney(player.data.getMoney()+200);
            }
        }
        if(player.data.getJailed()) {
            Scanner input = new Scanner(System.in);
            System.out.println("Would you like to pay 50$ to the banker to escape jail?");
            String ans = input.next();
            if (ans.equals("yes") || ans.equals("Yes")) {
                player.data.setMoney(player.data.getMoney()-50);
                escapeJail(player.data);
                System.out.println(player.data.getName() + "'s turn has ended");
            }
            else{
                player.data.setTurnsInJail(player.data.getTurnsInJail()-1);
                System.out.println(player.data.getName() + "'s turn has ended");
            }
        }
        else {
            player.data.setPosition(current);
            if (spot.getColor().equals("weird")) {
                if(spot.getName().equals("Go To Jail")){
                    player.data.setJailed(true);
                    player.data.setTurnsInJail(3);
                    player.data.setPosition(map.getLast()); //if last is the jail cell
                    System.out.println(player.data.getName() + "'s turn has ended");
                }
                if(spot.getFee()>0){
                    pay(spot, player.data);
                    System.out.println(player.data.getName() + "'s turn has ended");
                }
                else{ //temporarily for the other weird spots
                    System.out.println(player.data.getName() + "'s turn has ended");
                }

            }
            else {
                if(spot.isPurchasable()){
                    buy(spot, player.data);
                }
                else{
                    pay(spot, player.data);
                }
            }
        }

    }
    public void pay(BoardSpace spot, Player player){
        player.setMoney(player.getMoney() - spot.getFee());
        spot.getOwner().setMoney(spot.getOwner().getMoney() + spot.getFee());
    }
    public void buy(BoardSpace spot, Player player){
        Scanner input = new Scanner(System.in);
        System.out.println(player.getName() + " would you like to purchase " + spot.getName() + " for " + spot.getCost());
        String ans = input.next();
        if (ans.equals("yes") || ans.equals("Yes")) {
            player.setMoney(player.getMoney() - spot.getCost());
            ArrayList<BoardSpace> tempProperties = player.getProperties();
            tempProperties.add(spot);
            player.setProperties(tempProperties);
            spot.setPurchasable(false);
            spot.setOwner(player);
        }
        else {
            System.out.println(player.getName() + "'s turn has ended");
        }
    }
    public void escapeJail(Player player){ //also use for if the player rolls two doubles and is in jail
        player.setTurnsInJail(0);
        player.setJailed(false);
    }
    public void displayBoard(int numOfPlayers, CircularLinkedList<BoardSpace> printBoard){
        Link<BoardSpace> current = printBoard.getFirst();
        printBox(numOfPlayers, 11, current);
        for(int i=0; i<11; i++){
            current = current.nextLink;
        }
        printBox(numOfPlayers, 2, current);
        current = current.nextLink.nextLink;
        printBox(numOfPlayers, 2, current);
        current = current.nextLink.nextLink;
        printBox(numOfPlayers, 2, current);
        current = current.nextLink.nextLink;
        printBox(numOfPlayers, 2, current);
        current = current.nextLink.nextLink;
        printBox(numOfPlayers, 2, current);
        current = current.nextLink.nextLink;
        printBox(numOfPlayers, 2, current);
        current = current.nextLink.nextLink;
        printBox(numOfPlayers, 2, current);
        current = current.nextLink.nextLink;
        printBox(numOfPlayers, 2, current);
        current = current.nextLink.nextLink;
        printBox(numOfPlayers, 2, current);
        current = current.nextLink.nextLink;
        printBox(numOfPlayers, 11, current);


    }
    public void printBox(int numOfPlayers, int numOnRow, Link<BoardSpace> current) {
        String topRow = "";
        String secondRow = "";
        ArrayList<String> characters = new ArrayList<>();
        String thirdRow = "";
        String feeRow = "";
        int rowCounter = 0;
        String blanks = "";
        for (int z = 0; z < numOnRow; z++) {
            if(numOnRow==2) {
                for (int u = 0; u < (21 * 9)-1; u++) {
                    blanks += " ";
                }
                blanks+="|";
            }
            ArrayList<Player> onPosition = new ArrayList<>();
            if(rowCounter==0){
                topRow+="|";
                secondRow+="|";
                thirdRow+="|";
                feeRow+="|";
            }
            BoardSpace spot = current.data;
            int counter = 0;
            Link<Player> currentPlayer = this.getPlayers().getFirst();
            for (int i = 0; i < numOfPlayers; i++) {
                currentPlayer = currentPlayer.nextLink;
            }
            while (counter < numOfPlayers) {
                if (currentPlayer.data.getPosition().data == (spot)) {
                    onPosition.add(currentPlayer.data);
                }
                currentPlayer = currentPlayer.nextLink;
                counter++;
            }
            int distance = 21;//maxDistance for entire board spot
            for (int i = 0; i < distance; i++) {topRow += "-";}
            topRow+=blanks;
            int midDistance = 20 - spot.getName().length();
            if(spot.getName().length()%2==1){secondRow+=" ";}
            for (int j = 0; j < midDistance / 2; j++) { //centers the name on the spot
                secondRow += " ";
            }
            secondRow += spot.getName();
            for (int j = 0; j < midDistance / 2; j++) {secondRow += " ";}
            secondRow+="|";
            secondRow+=blanks;//for the case of 2 rows

            for (int i = 0; i < onPosition.size(); i++) { //prints the players that are on the spot with name centered
                Player currentPrint = onPosition.get(i);
                int secondMidDistance = 20 - currentPrint.getName().length();
                if(spot.getName().length()%2==0){thirdRow+=" ";}
                for (int j = 0; j < secondMidDistance / 2; j++) {thirdRow += " ";}
                thirdRow += currentPrint.getName();
                for (int j = 0; j < secondMidDistance / 2; j++) {thirdRow += " ";}
                if(rowCounter==0) {
                    characters.add(thirdRow);

                    thirdRow+=blanks;
                    thirdRow+="|";
                }
                else{
                    String newCharacters =  characters.get(i) + thirdRow;
                    characters.set(i, newCharacters);
                    thirdRow = "| ";
                }

            }
            int lastMidDistance = 20 - String.valueOf(spot.getCost()).length();
            if(String.valueOf(spot.getCost()).length()%2==1){feeRow+=" ";}
            for (int j = 0; j < lastMidDistance / 2; j++) { //centers the name on the spot
                feeRow += " ";
            }
            if(spot.getCost() != 0){
                feeRow += String.valueOf(spot.getCost());
            }
            else{
                feeRow += " ";
            }
            for (int j = 0; j < lastMidDistance / 2; j++) {feeRow += " ";}
            feeRow+="|";
            feeRow+=blanks;

            for (int i = 0; i < (numOfPlayers - onPosition.size()); i++) { //adds extra spaces so all the spots have the same size
                String extraMid = "|";
                for (int j = 0; j < 20; j++) {extraMid += " ";}
                if(rowCounter ==0) {
                    characters.add(extraMid);
                    extraMid+="|";
                }
                else{

                    String newCharacters=  characters.get(i) + extraMid;
                    characters.set(i, newCharacters);
                }
            }
            rowCounter++;
            current = current.nextLink;
        }
        System.out.println(topRow);
        System.out.println(secondRow);
        for(int i=0; i<characters.size(); i++){
            System.out.println(characters.get(i));
        }
        System.out.println(feeRow);
        System.out.println(topRow);
    }

}

