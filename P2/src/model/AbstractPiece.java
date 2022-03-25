package model;

public abstract class AbstractPiece {

    protected final int MAX_DIMENSION = 10;

    protected String name;
    protected Move[] moves;
    protected String image;


    public String getName() {
        return name;
    }

    public Move[] getMoves() {
        return moves;
    }

    public String getImage() {
        return image;
    }

}
