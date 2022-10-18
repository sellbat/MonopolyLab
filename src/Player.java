import java.util.ArrayList;

public class Player {
    private static String name;
    private double money;
    private Link position;
    private Boolean jailed;
    private ArrayList<BoardSpace> properties;

    public Player(String playerName){
        money = 1500.00;
        Player.name = playerName;

    }
    public boolean land(BoardSpace spot){
        if (spot.purchasable) {
            System.out.println("Would you like to purchase this land?");


        }

    }
    public double pay(BoardSpace spot){
        money -= spot.

    }
    public double buy(){

    }

}
