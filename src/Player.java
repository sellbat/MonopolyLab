import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static String name;
    private double money;
    private Link position;
    private Boolean jailed;
    private int turnsInJail;
    private ArrayList<BoardSpace> properties;
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Link getPosition() {
        return position;
    }

    public void setPosition(Link position) {
        this.position = position;
    }

    public Boolean getJailed() {
        return jailed;
    }

    public void setJailed(Boolean jailed) {
        this.jailed = jailed;
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public void setTurnsInJail(int turnsInJail) {
        this.turnsInJail = turnsInJail;
    }

    public ArrayList<BoardSpace> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<BoardSpace> properties) {
        this.properties = properties;
    }

    public Player(String playerName, Link start){
        setMoney(1500.00);
        setName(playerName);
        ArrayList<BoardSpace> properties = new ArrayList<>();
        setProperties(properties);
        setPosition(start);
        setJailed(false);
        setTurnsInJail(0);
    }
    public void move(int moves, CircularLinkedList map){
        Link current = getPosition();
        BoardSpace spot = (BoardSpace) current.data;
        for(int i=0; i<moves; i++){
            current = current.nextLink;
            if(current==map.getFirst() && !jailed){ //adds 200 to balance for passing Go
                    setMoney(getMoney()+200);
            }
        }
        if(jailed){
            setTurnsInJail(getTurnsInJail()-1);
            System.out.println(getName() + "'s turn has ended");
        }

        else {
            setPosition(current);
            if (spot.getColor().equals("weird")) {
                if(spot.getName().equals("Go To Jail")){
                    setJailed(true);
                    setTurnsInJail(3);
                    setPosition(map.getLast()); //if last is the jail cell
                    System.out.println(getName() + "'s turn has ended");
                }
                if(spot.getFee()>0){
                    pay(spot);
                    System.out.println(getName() + "'s turn has ended");
                }
                else{ //temporarily for the other weird spots
                    System.out.println(getName() + "'s turn has ended");
                }

            } else {
                if(spot.isPurchasable()){
                    buy(spot);
                }
                else{
                    pay(spot);
                }
            }
        }

    }
    public void pay(BoardSpace spot){
        setMoney(money -= spot.getFee());
        spot.getOwner().setMoney(spot.getOwner().getMoney() + spot.getFee());
    }
    public void buy(BoardSpace spot){
        Scanner input = new Scanner(System.in);
        String color = "";
        System.out.println(getName() + " would you like to purchase " + spot.getName() + " for " + spot.getCost());
        String ans = input.next();
        if (ans.equals("yes") || ans.equals("Yes")) {
            setMoney(getMoney() - spot.getCost());
            properties.add(spot);
            spot.setPurchasable(false);
            //spot.setOwner() in main
        }
        else {
            System.out.println(getName() + "'s turn has ended");
        }
    }

}