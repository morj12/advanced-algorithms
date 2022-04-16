package Model;

public class Node implements Comparable<Node> {
    public static final int LEFT_NODE = 0;
    public static final int RIGHT_NODE = 1;

    private Node leftNode;
    private Node rightNode;
    private int frequency;
    private Byte data;

    public Node() {
        this.data = null;
        this.frequency = 0;
        this.leftNode = null;
        this.rightNode = null;
    }

    public Node (Byte data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.leftNode = null;
        this.rightNode = null;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Byte getData() {
        return data;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setLeftNode(Model.Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Model.Node rightNode) {
        this.rightNode = rightNode;
    }

    public void addNode (Node node, int position) {
        switch (position) {
            case LEFT_NODE -> this.leftNode = node;
            case RIGHT_NODE -> this.rightNode = node;
        }
    }

    public void deleteNode(){
        this.rightNode = null;
        this.leftNode = null;
        this.frequency = 0;
        this.data = 0;
    }

    @Override
    public int compareTo(Node o) {
        return this.frequency - o.frequency;
    }
}
