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
    public void putPiece(AbstractPiece piece, int x, int y, int stepNumber) {
        cells[x][y].setPiece(piece, stepNumber);
    }

    /** Removes a piece **/
    public void removePiece(int x, int y) {
        cells[x][y].reset();
    }

    @Override
    public String toString() {
        String s = "***** BOARD ***** \n";
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                s += cells[j][i].toString() + " ";
            }
            s += "\n\n";
        }
        return s;
    }
}
