public class BoardSpace {
    private int cost;
    private int fee;
    private int sell;
    private boolean purchasable;
    private boolean jail;
    private String color;

    public BoardSpace(int cost, int fee, int sell, boolean purchasable, boolean jail, String color){
        this.color=color;
        this.cost=cost;
        this.fee=fee;
        this.sell=sell;
        this.purchasable=purchasable;
        this.jail=jail;
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
}
