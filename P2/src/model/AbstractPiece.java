package model;

public abstract class AbstractPiece {

    protected final int MAX_DIMENSION = 10;

    protected String name;
    protected Position[] moves;
    protected String image;

    protected boolean hasState;

    public boolean hasState() {
        return hasState;
    }

    public String getName() {
        return name;
    }

    public Position[] getMoves() {
        return moves;
    }

    public String getImage() {
        return image;
    }

    public Position[] getMovesWithoutStateChange() {
        return moves;
    }

}
