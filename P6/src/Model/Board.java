package Model;

import Utility.MatrixOperations;

public class Board {

    private int [][] matrix;

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Board() {
        this.matrix = MatrixOperations.createCorrectMatrix();
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void shuffle() {
        MatrixOperations.shuffleMatrix(this.matrix);
    }
}
