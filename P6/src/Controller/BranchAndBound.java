package Controller;

import Main.Main;
import Model.Matrix;
import Model.Node;

import java.util.PriorityQueue;

public class BranchAndBound {

    public static int[] ROW_SHIFT = {1, 0, -1, 0};
    public static int[] COLUMN_SHIFT = {0, -1, 0, 1};
    private boolean isExecuted;

    public void setExecuted(boolean executed) {
        isExecuted = executed;
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public Node createNewNode(
            int[][] matrix,
            int srcX, int srcY,
            int dstX, int dstY,
            int depth,
            Node parent) {

        Node node = new Node();
        node.setParent(parent);
        node.setMatrix(Matrix.copyMatrix(matrix));
        Matrix.swap(node.getMatrix(), srcX, srcY, dstX, dstY);
        node.setDistance(999);
        node.setDepth(depth);
        node.setZeroX(dstX);
        node.setZeroY(dstY);

        return node;
    }

    public boolean isCoordsInsideBounds(int x, int y) {
        return (x > -1 && x < Main.DIMENSION && y > -1 && y < Main.DIMENSION);
    }

    public Node branchAndBound(int[][] matrix) {
        isExecuted = true;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] zeroCoords = Matrix.getZeroCoords(matrix);
        int x = zeroCoords[0];
        int y = zeroCoords[1];

        int[][] solutionMatrix = Matrix.createCorrectMatrix();

        Node first = createNewNode(matrix, x, y, x, y, 0, null);
        first.setDistance(Matrix.calculateDistance(matrix, solutionMatrix));
        pq.add(first);

        while (!pq.isEmpty()) {
            if (!isExecuted) return null;

            Node min = pq.remove();
            if (min.getDistance() == 0) {
                return min;
            }

            for (int i = 0; i < ROW_SHIFT.length; i++) {
                if (isCoordsInsideBounds(min.getZeroX() + ROW_SHIFT[i], min.getZeroY() + COLUMN_SHIFT[i])) {
                    Node child = createNewNode(
                            min.getMatrix(),
                            min.getZeroX(),
                            min.getZeroY(),
                            min.getZeroX() + ROW_SHIFT[i],
                            min.getZeroY() + COLUMN_SHIFT[i],
                            min.getDepth() + 1,
                            min);
                    child.setDistance(Matrix.calculateDistance(child.getMatrix(), solutionMatrix));
                    pq.add(child);
                }
            }
        }
        return null;
    }

    public synchronized void interrupt() {
        if (isExecuted) isExecuted = false;
    }
}