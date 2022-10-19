import java.util.ArrayList;

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
    public boolean land(BoardSpace spot){
        if (spot.purchasable) {

        }
        
    }
    public double pay(BoardSpace spot){
        money -= spot.

    }
    public double buy(){

    }

}
