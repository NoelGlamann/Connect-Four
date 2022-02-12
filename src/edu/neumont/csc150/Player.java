package edu.neumont.csc150;

public abstract class Player {
    private String name;
    private boolean isHuman;
    private Piece myColor;
    private int playerOrder;

    public String getName() {
        return name;
    }
    protected void setName(String name) {
        this.name = name;
    }
    public boolean isHuman() {
        return isHuman;
    }
    protected void setHuman(boolean human) {
        isHuman = human;
    }
    public Piece getMyColor() {
        return myColor;
    }
    protected void setMyColor(Piece myColor) {
        this.myColor = myColor;
    }
    public int getPlayerOrder() {
        return playerOrder;
    }
    public void setPlayerOrder(int playerOrder) {
        this.playerOrder = playerOrder;
    }

    @Override
    public abstract String toString();
}
