package model;

public abstract class AbstractPiece {
    protected String name;
    protected Move[] moves;
    protected String image;
    protected boolean outOfBoundsDimension = false;

    public Move[] getMoves() {
        return moves;
    }

    public String getName() {
        return name;
    }
}
