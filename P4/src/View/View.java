/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Main.Notifiable;
import Model.HuffmanTree;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static javax.swing.ScrollPaneConstants.*;

/**
 * @author ikerg
 */
public class View extends JFrame {

    private Notifiable main;

    private File actualFile;

    private JPanel huffmanPanel;
    private JPanel infoPanel;
    private JLabel huffmanListPanel;
    private JButton openFileButton;
    private JButton generateHuffmanButton;
    private JLabel actualFileInfoLabel;
    private JLabel actualFileSizeLabel;
    private JLabel actualFileNameLabel;
    private JLabel compressedFileInfoLabel;
    private JLabel compressedFileSizeLabel;
    private JScrollPane scrollPane;
    private JTextArea area;

    public View(Notifiable main) {
        this.main = main;
        this.setTitle("Huffman compressor");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.actualFile = null;
        initComponents();
    }

    private void initComponents() {
        huffmanPanel = new JPanel();
        huffmanPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        huffmanPanel.setPreferredSize(new Dimension(200, 400));
        huffmanPanel.setBackground(Color.WHITE);
        huffmanPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        huffmanListPanel = new JLabel("Huffman tree");
        huffmanListPanel.setPreferredSize(new Dimension(180, 30));
        huffmanListPanel.setFont(new Font("Arial", Font.PLAIN, 25));
        area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 12));
        area.setAlignmentX(Component.LEFT_ALIGNMENT);
        area.setEnabled(false);
        scrollPane = new JScrollPane(area,
                VERTICAL_SCROLLBAR_ALWAYS,
                HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(180, 350));
        huffmanPanel.add(huffmanListPanel);
        huffmanPanel.add(scrollPane);

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
        generateHuffmanButton.setEnabled(false);
        actualFileInfoLabel = new JLabel("Actual file info");
        actualFileInfoLabel.setPreferredSize(new Dimension(180, 30));
        actualFileInfoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        actualFileNameLabel = new JLabel("Name: none");
        actualFileNameLabel.setPreferredSize(new Dimension(180, 24));
        actualFileSizeLabel = new JLabel("Size: none");
        actualFileSizeLabel.setPreferredSize(new Dimension(180, 24));
        compressedFileInfoLabel = new JLabel("Compressed file info");
        compressedFileInfoLabel.setPreferredSize(new Dimension(180, 30));
        compressedFileInfoLabel.setFont(new Font("Arial", 1, 12));
        compressedFileSizeLabel = new JLabel("Size in bytes: none");
        compressedFileSizeLabel.setPreferredSize(new Dimension(180, 24));

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
        area.setText("");
        main.notify("generate", actualFile);
    }

    private void openFileButtonPressed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setDialogTitle("Open file");
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.actualFile = jFileChooser.getSelectedFile();
            actualFileNameLabel.setText("Name: " + actualFile.getName());
            actualFileSizeLabel.setText("Size: " + actualFile.length() + " bytes");
            generateHuffmanButton.setEnabled(true);
        } else {
            generateHuffmanButton.setEnabled(false);
        }
    }

    public void setCompressedInfo(HuffmanTree o) {
        compressedFileSizeLabel.setText("Size: " + o.getCompressedSize() + " bytes");
        StringBuilder sb = new StringBuilder();
        o.getHuffmanTreeMap().forEach((key, value) -> sb.append(key).append(" | ").append(value).append("\n"));
        area.setText(sb.toString());

    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
