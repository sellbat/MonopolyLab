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

    public Player(String playerName, ArrayList<BoardSpace> properties, Link start){
        setMoney(1500.00);
        setName(playerName);
        setProperties(properties);
        setPosition(start);
        setJailed(false);
        setTurnsInJail(0);
    }
    public int move(int moves, CircularLinkedList map, ){
        Link current = getPosition();
        for(int i=0; i<moves; i++){
            current = current.nextLink;
            if(current.getName().equals("Go")){
                setMoney(getMoney()+200);
            }
        }
        setPosition(current);
        BoardSpace spot = (BoardSpace) current.data;
        if(jailed){
            setTurnsInJail(getTurnsInJail()-1);
            System.out.println(getName() + "'s turn has ended");
        }
        else {
            if (spot.getName().equals("goToJail")) {
                map.find();
                setPosition(jail);
                setTurnsInJail(3);
                System.out.println(getName() + "'s turn has ended");
            } else {
                pay(spot);
                buy(spot);

            }
        }

    }

    public void pay(BoardSpace spot){
        setMoney(money -= spot.getFee());
    }
    public void buy(BoardSpace spot){
        Scanner input = new Scanner(System.in);
        boolean sameColor = false;
        String color = properties.get(0).getColor();
        outer:
        for (int i = 0; i < properties.size(); i++) { //checks if player has all same color properties
            if (!properties.get(i).getColor().equals(color)) {
                sameColor = false;
                break outer;
            }
        }
        if (spot.isPurchasable() && sameColor && spot.getColor().equals(color)) {
            System.out.println(getName() + " would you like to purchase " + spot.getName() + " for " + spot.getCost());
            String ans = input.next();
            if (ans.equals("yes") || ans.equals("Yes")) {
                setMoney(getMoney() - spot.getCost());
            } else {
                System.out.println(getName() + "'s turn has ended");
            }
        }
    }

}
