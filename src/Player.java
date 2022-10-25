import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static String playerName;
    private double money;
    private Link<BoardSpace> position;
    private Boolean jailed;
    private int turnsInJail;
    private ArrayList<BoardSpace> properties;
    private String symbol;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public String getName() {
        return this.playerName;
    }

    public void setName(String Name) {
        this.playerName = Name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Link<BoardSpace> getPosition() {
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

    public Player(String playerName, Link<BoardSpace> start, String symbol){
        setMoney(1500.00);
        setName(playerName);
        ArrayList<BoardSpace> properties = new ArrayList<>();
        setProperties(properties);
        setPosition(start);
        setJailed(false);
        setTurnsInJail(0);
        setSymbol(symbol);
    }
}