/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Notifiable;
import Model.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @author ikerg
 */
public class Controller implements Notifiable {

    private Notifiable main;
    Map<Byte, String> huffmanCodeMap;
    StringBuilder sb;

    public Controller(Notifiable main) {
        this.main = main;
        this.huffmanCodeMap = new HashMap<>();
        this.sb = new StringBuilder();
    }

    public void start(File file) {
//        new Thread(() -> readAndCompress(file)).start();
        readAndCompress(file);
    }

    public void readAndCompress(File file) {
        try {
            byte[] allBytes = Files.readAllBytes(file.toPath());
            List<Node> nodes = getNodesList(allBytes);
            Node root = createHuffmanTree(nodes);
            if (root != null) {
                createHuffmanCodes(root, "", sb);
            }


        } catch (IOException ignored) {
        }
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

    private List<Node> getNodesList(byte[] allBytes) {
        List<Node> nodes = new ArrayList<>();
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

    private Node createHuffmanTree(List<Node> nodes) {

        /** GET ORIGINAL SIZE FOR NO REASON **/
        int size = 0;
        for (Node nigger: nodes) {
                size += nigger.getFrequency();
        }

        notify();
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node root = new Node(null , left.getFrequency() + right.getFrequency());
            root.setLeftNode(left);
            root.setRightNode(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(root);

        }

        return nodes.get(0);
    }

    @Override
    public void notify(String s, Object o) {

    }
}
