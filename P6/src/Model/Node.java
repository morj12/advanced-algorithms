package Model;

import Main.Main;

public class Node implements Comparable<Node> {

    public int[][] matrix = new int[Main.DIMENSION][Main.DIMENSION];
    public int zeroX, zeroY;
    public int depth;
    public int distance;
    public Node parent;

    @Override
    public int compareTo(Node o) {
        return (this.distance + this.depth) > (o.distance + o.depth) ? 1 : -1;
    }
}
