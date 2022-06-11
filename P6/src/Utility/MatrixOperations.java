package Utility;

import Main.Main;

import java.util.Random;

public class MatrixOperations {

    public static int[][] copyMatrix(int[][] src) {
        int[][] dst = new int[src.length][src.length];
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, src[i].length);
        }
        return dst;
    }

    public static int[] getZeroCoords(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) return new int[]{i, j};
            }
        }
        return null;
    }

    public static int calculateDistance(int[][] matrix, int[][] finalMat) {
        int count = 0;
        for (int i = 0; i < Main.DIMENSION; i++)
            for (int j = 0; j < Main.DIMENSION; j++)
                if (matrix[i][j]!=0 && matrix[i][j] != finalMat[i][j])
                    count++;
        return count;
    }

    public static void swap(int[][] matrix, int srcX, int srcY, int dstX, int dstY) {
        int temp = matrix[srcX][srcY];
        matrix[srcX][srcY] = matrix[dstX][dstY];
        matrix[dstX][dstY] = temp;
    }

    public static int[][] createCorrectMatrix() {
        int counter = 0;
        int[][] matrix = new int[Main.DIMENSION][Main.DIMENSION];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = counter++;
            }
        }
        return matrix;
    }

    public static void shuffleMatrix(int [][] matrix) {
        Random random = new Random();

        // Fisher-Yates algorithm
        for (int i = matrix.length - 1; i > 0; i--) {
            for (int j = matrix[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);
                swap(matrix, i, j, m, n);
            }
        }
    }
}
