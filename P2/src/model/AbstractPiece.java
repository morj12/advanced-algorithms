package model;

public abstract class AbstractPiece {
    protected String name;
    protected Move[] moves;
    protected String image;
    protected boolean outOfBoundsDimension = false;

    public String getName() {
        return name;
    }

    public Move[] getMoves() {
        return moves;
    }

    public String getImage() {
        return image;
    }

    public boolean isOutOfBoundsDimension() {
        return outOfBoundsDimension;
    }
}
