package Model;

import java.util.Random;

public class Board {

    private int [][] matrix;
    private int dimension;

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Board(int dimension) {
        this.dimension = dimension;
        this.matrix = new int[dimension][dimension];
        int counter = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) matrix[i][j] = counter++;
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void shuffle() {
        Random random = new Random();

        // Fisher-Yates algorithm
        for (int i = matrix.length - 1; i > 0; i--) {
            for (int j = matrix[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[m][n];
                matrix[m][n] = temp;
            }
        }
    }
}
