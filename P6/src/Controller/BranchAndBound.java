package Controller;

import Main.Main;
import Model.Node;
import Utility.MatrixOperations;

import java.util.PriorityQueue;

public class BranchAndBound {

    public static int[] ROW_SHIFT = {1, 0, -1, 0};
    public static int[] COLUMN_SHIFT = {0, -1, 0, 1};

    public Node newNode(int[][] matrix, int srcX, int srcY,
                               int dstX, int dstY, int depth,
                               Node parent) {
        Node node = new Node();
        node.parent = parent;

        node.matrix = MatrixOperations.copyMatrix(matrix);
        MatrixOperations.swap(node.matrix, srcX, srcY, dstX, dstY);

        node.distance = Integer.MAX_VALUE;
        node.depth = depth;

        node.zeroX = dstX;
        node.zeroY = dstY;

        return node;
    }

    public boolean IsInsideBounds(int x, int y) {
        return (x > -1 && x < Main.DIMENSION && y > -1 && y < Main.DIMENSION);
    }

    public Node solve(int[][] matrix){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int [] zeroCoords = MatrixOperations.getZeroCoords(matrix);
        int x = zeroCoords[0];
        int y = zeroCoords[1];

        int[][] solutionMatrix = MatrixOperations.createCorrectMatrix();

        Node root = newNode(matrix, x, y, x, y, 0, null);
        root.distance = MatrixOperations.calculateDistance(matrix, solutionMatrix);
        pq.add(root);

        while (!pq.isEmpty()) {
            Node min = pq.remove();
            if (min.distance == 0) {
                return min;
            }

            for (int i = 0; i < ROW_SHIFT.length; i++) {
                if (IsInsideBounds(min.zeroX + ROW_SHIFT[i], min.zeroY + COLUMN_SHIFT[i])) {
                    Node child = newNode(
                            min.matrix,
                            min.zeroX,
                            min.zeroY,
                            min.zeroX + ROW_SHIFT[i],
                            min.zeroY + COLUMN_SHIFT[i],
                            min.depth + 1,
                            min);
                    child.distance = MatrixOperations.calculateDistance(child.matrix, solutionMatrix);
                    pq.add(child);
                }
            }
        }
        return null;
    }
}