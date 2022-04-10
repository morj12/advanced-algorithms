package Model;

public class HuffmanTree {
    private Node root;

    public HuffmanTree(){
        this.root = null;
    }

    public HuffmanTree(Node root){
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root){
        this.root = root;
    }

    public void deleteHuffmanTree(){
        this.root = null;
    }
}