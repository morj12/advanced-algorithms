/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author ikerg
 */
public class View  extends JFrame {

    private JPanel huffmanPanel;
    private JPanel infoPanel;
    private JLabel huffmanListPanel;
    private JList<String> huffmanList;

    private JButton openFileButton;
    private JButton generateHuffmanButton;
    private JLabel actualFileInfoLabel;
    private JLabel actualFileSizeLabel;
    private JLabel actualFileNameLabel;
    private JLabel compressedFileInfoLabel;
    private JLabel compressedFileSizeLabel;

    public View() {
        this.setTitle("Huffman compressor");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        initComponents();
    }

    private void initComponents() {
        huffmanPanel = new JPanel();
        huffmanPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        huffmanPanel.setPreferredSize(new Dimension(200, 400));
        huffmanPanel.setBackground(Color.WHITE);
        huffmanPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        huffmanList = new JList<>(new String[]{"Hello", "world", "niggers"});
        huffmanList.setAlignmentX(Component.LEFT_ALIGNMENT);
        huffmanListPanel = new JLabel("Huffman tree");
        huffmanListPanel.setPreferredSize(new Dimension(180, 30));
        huffmanListPanel.setFont(new Font("Arial", Font.PLAIN, 25));
        huffmanPanel.add(huffmanListPanel);
        huffmanPanel.add(huffmanList);

        infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        infoPanel.setPreferredSize(new Dimension(200, 400));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        openFileButton = new JButton("Open file");
        openFileButton.setPreferredSize(new Dimension(180, 30));
        openFileButton.addActionListener(this::openFileButtonPressed);
        generateHuffmanButton = new JButton("Generate Huffman tree");
        generateHuffmanButton.setPreferredSize(new Dimension(180, 30));
        generateHuffmanButton.addActionListener(this::generateHuffmanButtonPressed);
        actualFileInfoLabel = new JLabel("Actual file info");
        actualFileNameLabel = new JLabel("Name: niggers.json");
        actualFileNameLabel.setPreferredSize(new Dimension(180, 30));
        actualFileSizeLabel = new JLabel("Size: 3033");
        actualFileSizeLabel.setPreferredSize(new Dimension(180, 30));
        compressedFileInfoLabel = new JLabel("Compressed file info");
        compressedFileInfoLabel.setPreferredSize(new Dimension(180, 30));
        compressedFileSizeLabel = new JLabel("Size in bytes: 2022");
        compressedFileSizeLabel.setPreferredSize(new Dimension(180, 30));

        infoPanel.add(openFileButton);
        infoPanel.add(generateHuffmanButton);
        infoPanel.add(actualFileInfoLabel);
        infoPanel.add(actualFileNameLabel);
        infoPanel.add(actualFileSizeLabel);
        infoPanel.add(compressedFileInfoLabel);
        infoPanel.add(compressedFileSizeLabel);

        this.add(BorderLayout.WEST, huffmanPanel);
        this.add(BorderLayout.EAST, infoPanel);

    }

    private void generateHuffmanButtonPressed(ActionEvent actionEvent) {

    }

    private void openFileButtonPressed(ActionEvent actionEvent) {

    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new View().showGui();
    }
}
