package model;

public class Board {


    private int dimension;
    private Cell[][] cells;

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /** Inits array of cells **/
    public Board(int dimension) {
        this.dimension = dimension;
        cells = new Cell[dimension][dimension];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    /** Puts a piece **/
    public void putPiece(AbstractPiece piece, int stepNumber, Position position) {
        cells[position.getX()][position.getY()].setPiece(piece, stepNumber);
    }

    public boolean isVisitedCell(Position position) {
        return cells[position.getX()][position.getY()].isVisited();
    }

    /** Removes a piece **/
    public void removePiece(Position position) {
        cells[position.getX()][position.getY()].reset();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("***** BOARD ***** \n");
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                s.append(cells[i][j].toString()).append(" ");
            }
            s.append("\n\n");
        }
        return s.toString();
    }
}
