package Model;

public class Node {

    //heuristics
    private Node parent;
    private int distance;
    private int depth;

    public Node(Node parent, int distance, int depth) {
        this.parent = parent;
        this.distance = distance;
        this.depth = depth;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
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
}
