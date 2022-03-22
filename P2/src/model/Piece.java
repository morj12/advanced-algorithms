package model;

public abstract class Piece {
    protected String name;
    protected Movement[] movements;
    protected String image;
    protected boolean outOfBoundsDimension = false;

    public Movement[] getMovements() {
        return movements;
    }

    public String getName() {
        return name;
    }
}
