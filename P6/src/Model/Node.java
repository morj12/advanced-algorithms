package Model;

import Main.Main;

public class Node implements Comparable<Node> {

    private int[][] matrix = new int[Main.DIMENSION][Main.DIMENSION];
    private int zeroX, zeroY;
    private int depth;
    private int distance;
    private Node parent;

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getZeroX() {
        return zeroX;
    }

    public void setZeroX(int zeroX) {
        this.zeroX = zeroX;
    }

    public int getZeroY() {
        return zeroY;
    }

    public void setZeroY(int zeroY) {
        this.zeroY = zeroY;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Node o) {
        return (this.distance + this.depth) > (o.distance + o.depth) ? 1 : -1;
    }
}
