package model;

public class Board {

    private int dimension;
    private Cell[][] cells;

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

    /** Puts initial piece **/
    public void initPiece(AbstractPiece piece, int x, int y) {
        cells[x][y].setPiece(piece);
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
