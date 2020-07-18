package Pieces;

public abstract class Piece {

    private int x;
    private int y;
    private boolean whiteColor;
    private String icon;


    public Piece(boolean whiteColor){
        this.whiteColor = whiteColor;
    }

    public abstract void  Move(int xMove, int yMove);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isWhiteColor() {
        return whiteColor;
    }

    public void setWhiteColor(boolean whiteColor) {
        this.whiteColor = whiteColor;
    }

    @Override
    public String toString() {
        return icon;
    }
}
