/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Notifiable;
import Model.HuffmanTree;
import Model.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ikerg
 */
public class Controller implements Notifiable {

    private Notifiable main;
    Map<Byte, String> huffmanCodeMap;
    StringBuilder sb;

    public synchronized boolean isExecuted() {
        return isExecuted;
    }

    private boolean isExecuted;

    public Controller(Notifiable main) {
        this.main = main;
        this.huffmanCodeMap = new HashMap<>();
        this.sb = new StringBuilder();
        this.isExecuted = false;
    }

    public void start(File file) {
        new Thread(() -> readAndCompress(file)).start();
    }

    public void readAndCompress(File file) {
        try {
            initComponents();
            byte[] allBytes = Files.readAllBytes(file.toPath());
            PriorityQueue<Node> nodes = getNodesList(allBytes);
            Node root = createHuffmanTree(nodes);
            byte[] compressedBytes;

            if (root != null) {
                createHuffmanCodes(root, "", sb);
                compressedBytes = compressFile(allBytes, huffmanCodeMap);
                main.notify("compressedSize", new HuffmanTree(huffmanCodeMap, compressedBytes.length));
            }
            isExecuted = false;
        } catch (IOException ignored) {
        }
    }

    public void initComponents() {
        this.huffmanCodeMap = new HashMap<>();
        this.sb = new StringBuilder();
        this.isExecuted = true;
    }

    private void createHuffmanCodes(Node node, String code, StringBuilder sb) {
        StringBuilder localSb = new StringBuilder(sb);
        localSb.append(code);
        if (node.getData() == null) {
            createHuffmanCodes(node.getLeftNode(), "0", localSb);
            createHuffmanCodes(node.getRightNode(), "1", localSb);
        } else {
            huffmanCodeMap.put(node.getData(), localSb.toString());
        }
    }

    private PriorityQueue<Node> getNodesList(byte[] allBytes) {
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        Map<Byte, Integer> frequencyTable = new HashMap<>();
        for (byte fileByte : allBytes) {
            // puts frequency 1 if not exists, if exists increments frequency
            frequencyTable.merge(fileByte, 1, Integer::sum);
        }
        for (Map.Entry<Byte, Integer> frequency : frequencyTable.entrySet()) {
            nodes.add(new Node(frequency.getKey(), frequency.getValue()));
        }
        return nodes;
    }

    private Node createHuffmanTree(PriorityQueue<Node> nodes) {
        while (nodes.size() > 1) {
            Node left = nodes.poll();
            Node right = nodes.poll();
            Node root = new Node(null, left.getFrequency() + right.getFrequency());
            root.setLeftNode(left);
            root.setRightNode(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(root);
        }
        return nodes.poll();
    }

    private byte[] compressFile(byte[] file, Map<Byte, String> huffmanCodeMap) {
        StringBuilder sb = new StringBuilder();
        for (byte fileByte : file) {
            sb.append(huffmanCodeMap.get(fileByte));
        }
        int bytesNumber = sb.length() % 8 == 0 ? sb.length() / 8 : sb.length() / 8 + 1;

        int index = 0;
        byte[] huffmanBytes = new byte[bytesNumber];
        for (int i = 0; i < sb.length(); i += 8) {
            String byteString;
            if (sb.length() < i + 8) {
                byteString = sb.substring(i);
            } else {
                byteString = sb.substring(i, i + 8);
            }
            huffmanBytes[index++] = (byte) Integer.parseInt(byteString, 2);
        }
        return huffmanBytes;
    }

    public byte[] decompressFile(byte[] compressedBytes, Map<Byte,String> huffmanCodeMap){
        boolean flagByte = true;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < compressedBytes.length; i++){
            byte byteToString = compressedBytes[i];
            if(i == compressedBytes.length - 1) {
                flagByte = false;
            }
            sb.append((byteToBitString(flagByte,byteToString)));
        }

        String stringDecompress = sb.toString();
        Map<String, Byte> map = new HashMap<>();

         //Switch order key and value?
        for(Map.Entry<Byte,String> entry : huffmanCodeMap.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        ArrayList<Byte> bytesDecompress = new ArrayList<>();
        int start = 0;
        int end = 1;

        while(start < stringDecompress.length()) {
            while(end < stringDecompress.length() && map.get(stringDecompress.substring(start, end)) == null){
                end++;
            }
            bytesDecompress.add(map.get(stringDecompress.substring(start,end)));
            start = end;
        }

        byte[] decompressedFile = new byte[bytesDecompress.size()];
        for(int i = 0; i < decompressedFile.length; i++){
            if(decompressedFile != null){
                decompressedFile[i] = bytesDecompress.get(i);
            }
        }

        //CHECK COMPRESSED FILE
        System.out.println(new String(decompressedFile));

        return decompressedFile;
    }

    private String byteToBitString (boolean flagByte, byte byteToChange){
        int temporal = byteToChange;

        if(flagByte) {
            temporal |= 256;
        }

        String bitString = Integer.toBinaryString(temporal);

        return flagByte ? bitString.substring(bitString.length() - 8) : bitString;
    }

    @Override
    public void notify(String s, Object o) {

    }
}
