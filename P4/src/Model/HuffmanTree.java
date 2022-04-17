package Model;

import java.util.Map;

public class HuffmanTree {

    private Map<Byte, String> huffmanTreeMap;
    private int compressedSize;

    public HuffmanTree(Map<Byte, String> huffmanTreeMap, int compressedSize) {
        this.huffmanTreeMap = huffmanTreeMap;
        this.compressedSize = compressedSize;
    }

    public Map<Byte, String> getHuffmanTreeMap() {
        return huffmanTreeMap;
    }

    public void setHuffmanTreeMap(Map<Byte, String> huffmanTreeMap) {
        this.huffmanTreeMap = huffmanTreeMap;
    }

    public int getCompressedSize() {
        return compressedSize;
    }

    public void setCompressedSize(int compressedSize) {
        this.compressedSize = compressedSize;
    }
}