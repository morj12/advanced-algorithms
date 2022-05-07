package Controller;

import Main.Notifiable;
import Model.HuffmanTree;
import Model.Node;
import Utils.BinaryLogarithm;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Controller implements Notifiable {

    private Notifiable main;
    Map<Byte, String> huffmanCodeMap;
    StringBuilder sb;
    byte[] compressedBytes;
    double theoreticalEntropy;
    double realEntropy;
    double symbolNumber;
    private boolean isExecuted;

    public Controller(Notifiable main) {
        this.main = main;
        this.isExecuted = false;
        initComponents();
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public void startThreadCreateHuffmanTree(File file) {
        new Thread(() -> readAndCompress(file)).start();
    }

    public void readAndCompress(File file) {
        try {
            initComponents();
            this.isExecuted = true;

            byte[] allBytes = Files.readAllBytes(file.toPath());
            PriorityQueue<Node> nodes = getNodesList(allBytes);
            Node root = createHuffmanTree(nodes);

            if (root != null) {
                createHuffmanCodes(root, "", sb);
                compressedBytes = compressFile(allBytes, huffmanCodeMap);
                realEntropy(compressedBytes.length * 8);
                main.notify("compressed", new HuffmanTree(
                        huffmanCodeMap,
                        compressedBytes.length,
                        theoreticalEntropy,
                        realEntropy));
            }
            isExecuted = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initComponents() {
        this.huffmanCodeMap = new HashMap<>();
        this.sb = new StringBuilder();
        this.theoreticalEntropy = 0;
        this.realEntropy = 0;
        this.symbolNumber = 0;
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
        main.notify("progressBarStart", allBytes.length);
        int step = 0;
        for (byte fileByte : allBytes) {
            main.notify("step", ++step);
            // puts frequency 1 if not exists, if exists increments frequency
            frequencyTable.merge(fileByte, 1, Integer::sum);
        }

        //get theoretical entropy
        theoreticEntropy(frequencyTable);
        symbolNumber = getSymbolNumber(frequencyTable);

        main.notify("progressBarStart", frequencyTable.size());
        step = 0;
        for (Map.Entry<Byte, Integer> frequency : frequencyTable.entrySet()) {
            main.notify("step", ++step);
            nodes.add(new Node(frequency.getKey(), frequency.getValue()));
        }
        return nodes;
    }

    private Node createHuffmanTree(PriorityQueue<Node> nodes) {
        while (nodes.size() > 1) {
            Node left = nodes.poll();
            Node right = nodes.poll();
            Node root = new Node(
                    null,
                    left.getFrequency() + right.getFrequency());
            root.setLeftNode(left);
            root.setRightNode(right);
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

        main.notify("progressBarStart", bytesNumber);
        for (int i = 0; i < sb.length(); i += 8) {
            main.notify("step", i);
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

    public byte[] decompressFile(byte[] compressedBytes, Map<Byte, String> huffmanCodeMap) {
        StringBuilder sb = new StringBuilder();

        main.notify("progressBarStart", compressedBytes.length);
        for (int i = 0; i < compressedBytes.length; i++) {
            main.notify("step", i);
            byte byteToString = compressedBytes[i];
            sb.append(byteToBitString((i != compressedBytes.length - 1), byteToString));
        }

        String decompressedString = sb.toString();
        Map<String, Byte> map = new HashMap<>();

        for (Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        ArrayList<Byte> bytesDecompress = new ArrayList<>();
        int start = 0;
        int end = 1;

        main.notify("progressBarStart", decompressedString.length());
        while (start < decompressedString.length()) {
            main.notify("step", start);
            while (end < decompressedString.length()
                    && map.get(decompressedString.substring(start, end)) == null) {
                end++;
            }
            if (map.containsKey(decompressedString.substring(start, end))) {
                bytesDecompress.add(map.get(decompressedString.substring(start, end)));
            }
            start = end;
        }

        byte[] decompressedFile = new byte[bytesDecompress.size()];
        int i = 0;
        main.notify("progressBarStart", bytesDecompress.size());
        for (Byte b : bytesDecompress) {
            decompressedFile[i++] = b;
            main.notify("step", i);
        }

        return decompressedFile;
    }

    private String byteToBitString(boolean flagByte, byte byteToChange) {
        int temporal = byteToChange;
        if (flagByte) {
            temporal |= 256;
        }
        String bitString = Integer.toBinaryString(temporal);
        return flagByte || temporal < 0 ? bitString.substring(bitString.length() - 8) : bitString;
    }

    public void startThreadZipFile(File sourceFile, File destinationFile) {
        new Thread(() -> zipFile(sourceFile, destinationFile)).start();
    }

    public void startThreadUnzipFile(File sourceFile, File destinationFile) {
        new Thread(() -> unzipFile(sourceFile, destinationFile)).start();
    }

    public void zipFile(File sourceFile, File destinationFile) {
        try (
                FileInputStream inputStream = new FileInputStream(sourceFile);
                OutputStream outputStream = new FileOutputStream(destinationFile);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            isExecuted = true;
            byte[] bytesFile = new byte[inputStream.available()];
            inputStream.read(bytesFile);
            objectOutputStream.writeObject(compressedBytes);
            objectOutputStream.writeObject(huffmanCodeMap);
            main.notify("encoded", null);

            isExecuted = false;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void unzipFile(File sourceFile, File destinationFile) {
        try (
                InputStream inputStream = new FileInputStream(sourceFile);
                OutputStream outputStream = new FileOutputStream(destinationFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            isExecuted = true;

            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            Map<Byte, String> huffmanMapCodes = (Map<Byte, String>) objectInputStream.readObject();
            byte[] decompressedBytes = decompressFile(huffmanBytes, huffmanMapCodes);
            outputStream.write(decompressedBytes);

            main.notify("decoded", null);
            isExecuted = false;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void theoreticEntropy(Map<Byte, Integer> frequencyTable) {
        double symbolNumber = getSymbolNumber(frequencyTable);

        double localEntropy = 0;
        for (Map.Entry<Byte, Integer> entry: frequencyTable.entrySet()) {
            double entryValue = (double) entry.getValue();
            var frequency = entryValue / symbolNumber;
            var logarithm = BinaryLogarithm.binaryLog(1 / (frequency));
            localEntropy += frequency * logarithm;

        }
        theoreticalEntropy = localEntropy;
    }

    public double getSymbolNumber(Map<Byte, Integer> frequencyTable) {
        AtomicReference<Double> originalSymbolNumber = new AtomicReference<>((double) 0);
        frequencyTable.forEach((key, value) -> originalSymbolNumber.updateAndGet(v -> v + value));
        return originalSymbolNumber.get();
    }

    public void realEntropy(double bits) {
        realEntropy = bits / symbolNumber;
    }

    @Override
    public void notify(String s, Object o) {

    }
}
