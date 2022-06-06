package Model;

public class Board {

    private int [][] matrix;
    private int dimension;

    public Board(int dimension) {
        this.dimension = dimension;
        matrix = new int[dimension][dimension];
        int counter = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) matrix[i][j] = counter++;
        }
    }

    public int calculateIncorrectElementsNumber() {
        int errorsCounter = 0;
        int counter = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (matrix[i][j] != counter++) errorsCounter++;
            }
        }
        return errorsCounter;
    }

    public int[][] getMatrix() {
        return matrix;
    }

}
