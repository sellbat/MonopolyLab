import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private CircularLinkedList<BoardSpace> map;
    private CircularLinkedList<Player> players;
    private boolean isGameOver;

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


    public Game(CircularLinkedList<BoardSpace> map, CircularLinkedList<Player> players){
        //constructor for the game
        setMap(map);
        setPlayers(players);
    }
    public static int roll(){
        //returns integer between 1 and 6
        return (int)(Math.random()*6+1);
    }
    public void move(Link<Player> player, CircularLinkedList<BoardSpace> board, int rollNumber){
        Player currentPlayer = player.data;
        int r1 = roll();
        int r2 = roll();
        System.out.println(currentPlayer.getName() + " has rolled a " + r1 + "," + r2);
        int moves= r1+r2;
        //finds current position in the game map
        Link<BoardSpace> currentPosition = board.find(currentPlayer.getPosition());
        System.out.println(currentPlayer.getName() + "'s current balance is: $" + currentPlayer.getMoney());
        //if the player rolls doubles three times in a row, he goes to jail
        if(rollNumber==2&&r1==r2){
            currentPlayer.setJailed(true);
            currentPlayer.setTurnsInJail(3);
            //link for the Jail spot
            currentPlayer.setPosition(map.getFirst().nextLink.nextLink.nextLink.nextLink.nextLink.nextLink.nextLink.nextLink.nextLink.nextLink);
            System.out.println(currentPlayer.getName() + "'s turn has ended since he rolled 3 doubles");
        }
        if(currentPlayer.getJailed()) {
            currentPlayer.setTurnsInJail(currentPlayer.getTurnsInJail()-1);
            //decrements the players turns in jail
            Scanner input = new Scanner(System.in);
            if(currentPlayer.getTurnsInJail()==0){
                //if final turn in jail
                escapeJail(currentPlayer);
                System.out.println("You are now free after 3 turns in jail");
                //add in diceroll and new turn
            }
            else {
                System.out.println("Would you like to pay 50$ to the banker to escape jail?");
                String ans = input.next();
                //player leaves jail if he pays 50$ to the banker
                if (ans.equals("yes") || ans.equals("Yes")) {
                    currentPlayer.setMoney(currentPlayer.getMoney()-50);
                    escapeJail(currentPlayer);
                    System.out.println(currentPlayer.getName() + " has escaped from jail");
                }
                else{
                    //if player rolls doubles, he escapes jail
                    if (r1==r2){
                        escapeJail(currentPlayer);
                        System.out.println(currentPlayer.getName() + " has escaped from jail by rolling doubles");
                    }
                    else {
                        //player loses turn in jail
                        System.out.println(currentPlayer.getName() + "'s has " + currentPlayer.getTurnsInJail() + " left");
                        System.out.println(currentPlayer.getName() + "'s turn has ended");
                        //exits method
                        return;
                    }
                }
            }
        }
        for(int i=0; i<moves; i++){
            currentPlayer.setPosition(currentPosition.nextLink);
            if(currentPlayer.getPosition()==map.getFirst() && !currentPlayer.getJailed()){
                //adds 200 to balance for passing Go
                currentPlayer.setMoney(currentPlayer.getMoney()+200);
            }
            //sets current position to the position after adding moves
            currentPosition = currentPosition.nextLink;
        }
        System.out.println(currentPlayer.getName() + " has landed on " + currentPlayer.getPosition().data.getName());
        BoardSpace spot = player.data.getPosition().data;
        if (spot.getColor().equals("weird")) {
            if(spot.getName().equals("Go To Jail")){
                //player goes to jail for 3 turns
                currentPlayer.setJailed(true);
                currentPlayer.setTurnsInJail(3);
                //link for the Jail spot
                currentPlayer.setPosition(map.getFirst().nextLink.nextLink.nextLink.nextLink.nextLink.nextLink.nextLink.nextLink.nextLink.nextLink);
                System.out.println(currentPlayer.getName() + "'s turn has ended");
            }
            if(spot.getFee()>0){
                //for the income tax spots and luxury
                pay(spot, currentPlayer, r1, r2);
                System.out.println(currentPlayer.getName() + "'s turn has ended");
            }
            else{
                System.out.println(currentPlayer.getName() + "'s turn has ended");
            }
        }
        else {
            if(spot.isPurchasable()){
                //menu to buy the spot if the spot is purchasable
                buy(spot, currentPlayer);
            }
            else if (spot.isMortgaged()){
                //if spot is mortgaged, do nothing
            }
            else{
                //payfee if you can't do anything above
                pay(spot, currentPlayer, r1, r2);
            }
        }
        if (r1==r2){
            //if the player rolls doubles, he moves again and the turnNumber is incremented
            move(player,board, rollNumber+1);
        }
    }


    public void pay(BoardSpace spot, Player player, int r1, int r2){
        int price = spot.getFee();
        if(spot.getColor().equals("rail")){
            int propertyCount = 1;
            for (int i=0;i<spot.getOwner().getProperties().size();i++){
                //counts the number of properties
                if (spot.getOwner().getProperties().get(i).getColor().equals(spot.getColor())){
                    propertyCount++;
                }
            }
            if(propertyCount<=2){
                price=price*propertyCount;
            }
            if(propertyCount==3){
                price = 100;
            }
            if(propertyCount==4){
                price = 200;
            }
            spot.getOwner().setMoney(spot.getOwner().getMoney() + price);
            player.setMoney(player.getMoney() - price);
        }
        else if (spot.getColor().equals("util")){
            int propertyCount = 1;
            for (int i=0;i<spot.getOwner().getProperties().size();i++){
                //counts the number of properties
                if (spot.getOwner().getProperties().get(i).getColor().equals(spot.getColor())){
                    propertyCount++;
                }
            }
            if(propertyCount==1){
                price = 4 * (r1+r2);
            }
            if(propertyCount==2){
                price = 10 *(r1+r2);
            }
            spot.getOwner().setMoney(spot.getOwner().getMoney() + price);
            player.setMoney(player.getMoney() - price);
        }
        else if (!spot.getColor().equals("weird")){
            int propertyCount = 1;
            int maxPropertyCount = 3;
            for (int i=0;i<spot.getOwner().getProperties().size();i++){
                //counts the number of properties
                if (spot.getOwner().getProperties().get(i).getColor().equals(spot.getColor())){
                    propertyCount++;
                }
            }
            if (spot.getColor().equals("brown")||spot.getColor().equals("dark blue")){
                maxPropertyCount=2;
            }
            if (maxPropertyCount==propertyCount){
                price=price*2;
                for (int i=0;i<spot.getHouseNum();i++){
                    price=price*3;
                }
            }
            spot.getOwner().setMoney(spot.getOwner().getMoney() + price);
            player.setMoney(player.getMoney() - price);
        }
        else {
            player.setMoney(player.getMoney()-spot.getFee());
        }
    }
    public void sell(BoardSpace spot, Player currentPlayer){
        if (spot.getHouseNum()>0){
            if (spot.getColor().equals("brown")||spot.getColor().equals("light blue")){
                for (int i=0;i<spot.getHouseNum();i++){
                    currentPlayer.setMoney(currentPlayer.getMoney()+25);
                }
                spot.setHouseNum(0);
            }
            if (spot.getColor().equals("pink")||spot.getColor().equals("orange")){
                for (int i=0;i<spot.getHouseNum();i++){
                    currentPlayer.setMoney(currentPlayer.getMoney()+50);
                }
                spot.setHouseNum(0);
            }
            if (spot.getColor().equals("red")||spot.getColor().equals("yellow")){
                for (int i=0;i<spot.getHouseNum();i++){
                    currentPlayer.setMoney(currentPlayer.getMoney()+75);
                }
                spot.setHouseNum(0);
            }
            if (spot.getColor().equals("green")||spot.getColor().equals("dark blue")){
                for (int i=0;i<spot.getHouseNum();i++){
                    currentPlayer.setMoney(currentPlayer.getMoney()+100);
                }
                spot.setHouseNum(0);
            }
        }
        currentPlayer.setMoney(currentPlayer.getMoney()+spot.getCost());
        for(int i=0; i<currentPlayer.getProperties().size(); i++){
            if(currentPlayer.getProperties().get(i)==spot){
                currentPlayer.getProperties().remove(i);
            }
        }
        spot.setPurchasable(true);
        return;
    }
    public void mortgage(BoardSpace spot, Player currentPlayer){
        if (spot.getHouseNum()>0){
            if (spot.getColor().equals("brown")||spot.getColor().equals("light blue")){
                for (int i=0;i<spot.getHouseNum();i++){
                    currentPlayer.setMoney(currentPlayer.getMoney()+25);
                }
                spot.setHouseNum(0);
            }
            if (spot.getColor().equals("pink")||spot.getColor().equals("orange")){
                for (int i=0;i<spot.getHouseNum();i++){
                    currentPlayer.setMoney(currentPlayer.getMoney()+50);
                }
                spot.setHouseNum(0);
            }
            if (spot.getColor().equals("red")||spot.getColor().equals("yellow")){
                for (int i=0;i<spot.getHouseNum();i++){
                    currentPlayer.setMoney(currentPlayer.getMoney()+75);
                }
                spot.setHouseNum(0);
            }
            if (spot.getColor().equals("green")||spot.getColor().equals("dark blue")){
                for (int i=0;i<spot.getHouseNum();i++){
                    currentPlayer.setMoney(currentPlayer.getMoney()+100);
                }
                spot.setHouseNum(0);
            }
        }
        currentPlayer.setMoney(currentPlayer.getMoney()+spot.getSell());
        spot.setMortgaged(true);
    }
    public void unMortgage(BoardSpace spot, Player currentPlayer){
        currentPlayer.setMoney(currentPlayer.getMoney() - 1.1*spot.getSell());
        spot.setMortgaged(false);
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

    public void bankruptcy(Player player){
        if(player.getMoney()<0){
            System.out.println(player.getName() + " has gone bankrupt");
            getPlayers().delete(player);
            for(int i=0; i<player.getProperties().size(); i++){
                player.getProperties().get(i).setMortgaged(true);
            }
        }

    }
    public void gameOver(){
        //if only one player remaining
        if(getPlayers().getFirst()==getPlayers().getLast()){
            System.out.println(getPlayers().getFirst().data.getName() + " has won!");
            setGameOver(true);
        }
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
        String topRow = "|";
        String secondRow = "|";
        ArrayList<String> characters = new ArrayList<>();
        String thirdRow = "| ";
        String feeRow = "|";
        int rowCounter = 0;
        int max = 25;
        for (int z = 0; z < numOnRow; z++) {
            String blanks = "";
            if(numOnRow==2) { //blanks for if there is only two rows
                for (int u = 0; u < (21 * 9); u++) { //21 blanks 9 times for the amount in between the two rows
                    blanks += " ";
                }
            }
            BoardSpace spot = current.data;
            ArrayList<Player> onPosition = new ArrayList<>();//amount of player on the spot
            int counter = 0;
            Link<Player> currentPlayer = this.getPlayers().getFirst();
            while (counter < numOfPlayers) {
                //adds the players that are on the spot
                if (currentPlayer.data.getPosition().data == (spot)) {
                    onPosition.add(currentPlayer.data);
                }
                currentPlayer = currentPlayer.nextLink;
                counter++;
            }
            int distance = 21;//maxDistance for entire board spot
            if(numOnRow==2&&rowCounter!=0){topRow+="|";}
            //adds the bar if its the second version of the top row
            for (int i = 0; i < distance; i++) {topRow += "-";}
            if(numOnRow==2) {//fixes spacing error in row lengths
                String topBlanks = blanks.substring(0, blanks.length() - 1);
                topRow += topBlanks;
            }
            else{
                topRow+=blanks;
            }
            int midDistance = 20 - spot.getName().length();
            if(spot.getName().length()%2==1){secondRow+=" ";}
            for (int j = 0; j < midDistance / 2; j++) { //centers the name on the spot
                secondRow += " ";
            }
            secondRow += spot.getName();
            for (int j = 0; j < midDistance / 2; j++) {secondRow += " ";}
            secondRow+="|";
            secondRow+=blanks;//for the case of 2 rows
            if(numOnRow==2){
                secondRow = secondRow.substring(0,secondRow.length()-1);
                if(rowCounter==0) {
                    secondRow += "|";
                }
            }
            for (int i = 0; i < onPosition.size(); i++) { //prints the players that are on the spot with name centered
                Player currentPrint = onPosition.get(i);
                int secondMidDistance = 20 - currentPrint.getName().length();
                for (int j = 0; j < secondMidDistance / 2; j++) {thirdRow += " ";}
                thirdRow += currentPrint.getName();
                for (int j = 0; j < secondMidDistance / 2; j++) {thirdRow += " ";}
                if(currentPrint.getName().length()%2==0){
                    thirdRow = thirdRow.substring(0,thirdRow.length()-1);
                }
                if(rowCounter==numOnRow){
                    thirdRow+="|";
                    String newCharacters = characters.get(i) + blanks + thirdRow;
                    characters.set(i, newCharacters);
                }
                if(rowCounter==0) {
                    if(numOnRow==2){
                        characters.add(thirdRow + "|");
                    }
                    else {
                        characters.add(thirdRow);
                    }
                }
                else{
                    String newCharacters = characters.get(i) + blanks + thirdRow;
                    if(numOnRow==2){
                        newCharacters = characters.get(i) +blanks.substring(0,blanks.length()-1) + thirdRow;
                    }
                    characters.set(i, newCharacters);
                }
                thirdRow="| ";
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
            if(rowCounter==0&&numOnRow==2){
                feeRow = feeRow.substring(0, feeRow.length()-1);
                feeRow+="|";
            }
            for (int i = onPosition.size(); i < (numOfPlayers); i++) { //adds extra spaces so all the spots have the same length
                String extraMid = "|";
                for (int j = 0; j < 20; j++) {extraMid += " ";}
                if(rowCounter==numOnRow){
                    extraMid+="|";
                    String newCharacters = characters.get(i) + blanks + extraMid;
                    characters.set(i, newCharacters);
                }
                if(rowCounter==0) {
                    if(numOnRow==2){
                        characters.add(extraMid + "|");
                    }
                    else {
                        characters.add(extraMid);
                    }
                }
                else{
                    String newCharacters = characters.get(i) + blanks + extraMid;
                    if(numOnRow==2){
                        newCharacters = characters.get(i) +blanks.substring(0,blanks.length()-1) + extraMid;
                    }
                    characters.set(i, newCharacters);
                }
            }
            rowCounter++;
            current = current.nextLink;
        }
        System.out.println(topRow);
        System.out.println(secondRow);
        for(int i=0; i<characters.size(); i++){
            System.out.println(characters.get(i) + "|");
        }
        System.out.println(feeRow);
        System.out.println(topRow);
    }
}

