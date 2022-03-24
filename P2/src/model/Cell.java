package model;

public class Cell {

    private boolean visited;
    private int stepNumber;
    private AbstractPiece piece;

    public Cell() {

        this.visited = false;
        this.stepNumber = -1;
    }

    public AbstractPiece getPiece() {
        return piece;
    }

    public void setPiece(AbstractPiece piece) {
        this.piece = piece;
    }

    public void setPiece(AbstractPiece piece, int stepNumber) {
        this.piece = piece;
        this.stepNumber = stepNumber;
        this.visited = true;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void IncrementStep() {
        stepNumber++;
    }

    public void reset() {
        this.stepNumber = -1;
        this.piece = null;
        this.visited = false;
    }

    public void visit() {
        this.visited = true;
    }

    /** Example:  0/Me (step 0 of Megaknight) **/
    @Override
    public String toString() {
        return stepNumber + "/" + (piece != null ? piece.getClass().getSimpleName().substring(0, 2) : "  ");
    }
}
