package Model;

import Controller.Utility;
import jdk.jshell.execution.Util;

import java.util.PriorityQueue;

public class Step implements Comparable<Step> {

    public Step getParent() {
        return parent;
    }

    public void setParent(Step parent) {
        this.parent = parent;
    }

    private Step parent;
    private int matrix[][];
    private int x, y;
    private int distance;
    private int depth;

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int compareTo(Step o) {
        return (this.distance + this.depth) > (o.distance + o.depth) ? 1 : -1;
    }

    public void copyMatrix(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public void move(int newX, int newY) {
        int temp = matrix[x][y];
        matrix[x][y] = matrix[newX][newY];
        matrix[newX][newY] = temp;
    }

    public static Step newStep(int[][] matrix, int newX, int newY, int depth, Step parent) {
        Step step = new Step();
        step.setParent(parent);
        step.setMatrix(Utility.copyMatrix(matrix));
        if (parent != null) {
            step.x = parent.x;
            step.y = parent.y;
        }


        step.move(newX, newY);

        step.setDistance(999);
        step.setDepth(depth);

        step.setX(newX);
        step.setY(newY);

        return step;

    }

    public static int isSafe(Step step, int x, int y) {
        return (x >= 0 && x < step.matrix.length && y >= 0 && y < step.matrix.length) ? 1 : 0;
    }

    // print path from root node to destination node
    public static void printPath(Step root) {
        if (root == null) {
            return;
        }
        printPath(root.parent);
        printMatrix(root.getMatrix());
        System.out.println("");
    }

    public static void printMatrix(int[][] mat) {
        for (int[] ints : mat) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println("");
        }
    }

    public static Step solve(int[][] initialMat, int x,
                             int y, int[][] finalMat) {

        PriorityQueue<Step> pq = new PriorityQueue<>();
        Step root = newStep(initialMat, x, y, 0, null);
        root.setDistance(Utility.calculateDistance(initialMat, finalMat));
        pq.add(root);
        while (!pq.isEmpty()) {
            Step min = pq.remove();
            if (min.distance == 0) {
                printPath(min);
                return min;
            }
            for (int i = 0; i < 4; i++) {
                if (isSafe(min, min.x + row[i], min.y + col[i]) > 0) {
                    Step child = newStep(min.matrix, min.x + row[i], min.y + col[i], min.depth + 1, min);
                    child.distance = Utility.calculateDistance(child.matrix, finalMat);
                    pq.add(child);
                }
            }
        }
        return null;
    }

    public static int[] row = {1, 0, -1, 0};
    public static int[] col = {0, -1, 0, 1};
}
