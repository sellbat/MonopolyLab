public class BoardSpace {
    //used to store property information
    private int cost;
    private int fee;
    private int sell;
    private boolean purchasable;
    private boolean jail;

    private String color;
    private String name;

    private Player owner;
    private int houseNum;
    private boolean mortgaged;
//Brantley
    public BoardSpace(String name,int cost, int fee, int sell, boolean purchasable, boolean jail, String color){
        this.name=name;
        this.color=color;
        this.cost=cost;
        this.fee=fee;
        //sell price is half the cost of the property
        this.sell=cost/2;
        this.purchasable=purchasable;
        this.jail=jail;
        //no houses are originally on the property
        this.houseNum=0;
        this.mortgaged=false;
    }

    public boolean isMortgaged() {
        return mortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public int getFee() {
        return fee;
    }

    public int getSell() {
        return sell;
    }

    public String getColor() {
        return color;
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public boolean isJail() {
        return jail;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setJail(boolean jail) {
        this.jail = jail;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }
    public Player getOwner() {return owner;}

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
