package Model;

import java.util.Map;

public class HuffmanTree {

    private Map<Byte, String> huffmanTreeMap;
    private int compressedSize;
    private double theoreticalEntropy;
    private double realEntropy;

    public double getTheoreticalEntropy() {
        return theoreticalEntropy;
    }

    public double getRealEntropy() {
        return realEntropy;
    }

    public HuffmanTree(Map<Byte, String> huffmanTreeMap, int compressedSize, double theoreticalEntropy, double realEntropy) {
        this.huffmanTreeMap = huffmanTreeMap;
        this.compressedSize = compressedSize;
        this.theoreticalEntropy = theoreticalEntropy;
        this.realEntropy = realEntropy;
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