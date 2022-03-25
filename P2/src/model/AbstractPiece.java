package model;

public abstract class AbstractPiece {
    protected String name;
    protected Move[] moves;
    protected String image;
    protected boolean outOfBoundsDimension = false;
    protected boolean hasState;

    public String getName() {
        return name;
    }

    public Move[] getMoves() {
        return moves;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoves(Move[] moves) {
        this.moves = moves;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setOutOfBoundsDimension(boolean outOfBoundsDimension) {
        this.outOfBoundsDimension = outOfBoundsDimension;
    }

    public boolean isHasState() {
        return hasState;
    }

    public void setHasState(boolean hasState) {
        this.hasState = hasState;
    }

    public boolean isOutOfBoundsDimension() {
        return outOfBoundsDimension;
    }
}
