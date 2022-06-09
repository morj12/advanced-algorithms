package Controller;

public class Utility {

    public static int[][] copyMatrix(int[][] dest) {
        int[][] src = new int[dest.length][dest.length];
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[i].length; j++) {
                src[i][j] = dest[i][j];
            }
        }
        return src;
    }

    public static int[] getZeroCoords(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) return new int[] {i, j};
            }
        }
        return null;
    }

    public static int calculateDistance(int[][] initialMat, int[][] finalMat) {
        int count = 0;
        for (int i = 0; i < initialMat.length; i++)
            for (int j = 0; j < initialMat[i].length; j++)
                if (initialMat[i][j] != 0 && initialMat[i][j] != finalMat[i][j])
                    count++;
        return count;
    }
}
