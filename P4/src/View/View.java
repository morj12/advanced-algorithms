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
import java.util.Arrays;

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

    private JButton[] buttons;

    private JLabel actualFileInfoLabel;
    private JLabel actualFileSizeLabel;
    private JLabel actualFileNameLabel;
    private JLabel compressedFileInfoLabel;
    private JLabel compressedFileSizeLabel;
    private JScrollPane scrollPane;
    private JTextArea area;

    private JLabel theoreticalEntropy;
    private JLabel reallEntropy;

    public View(Notifiable main) {
        this.main = main;
        this.setTitle("Huffman compressor");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.actualFile = null;
        initComponents();
    }

    private void initButtons() {
        buttons = new JButton[4];
        buttons[0] = new JButton("Open file");
        buttons[0].setPreferredSize(new Dimension(180, 30));
        buttons[0].addActionListener(this::openFileButtonPressed);
        buttons[1] = new JButton("Generate Huffman tree");
        buttons[1].setPreferredSize(new Dimension(180, 30));
        buttons[1].addActionListener(this::generateHuffmanButtonPressed);
        buttons[1].setEnabled(false);
        buttons[2] = new JButton("Encode and save file");
        buttons[2].setPreferredSize(new Dimension(180, 30));
        buttons[2].addActionListener(this::encodeAndSaveButtonPressed);
        buttons[2].setEnabled(false);
        buttons[3] = new JButton("Decode and save file");
        buttons[3].setPreferredSize(new Dimension(180, 30));
        buttons[3].addActionListener(this::decodeAndSaveButtonPressed);
        buttons[3].setEnabled(false);
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
        theoreticalEntropy = new JLabel("Theoretical entropy: none");
        theoreticalEntropy.setPreferredSize(new Dimension(180, 24));
        reallEntropy = new JLabel("Real entropy: none");
        reallEntropy.setPreferredSize(new Dimension(180, 24));

        initButtons();
        Arrays.stream(buttons).forEach(infoPanel::add);

        infoPanel.add(actualFileInfoLabel);
        infoPanel.add(actualFileNameLabel);
        infoPanel.add(actualFileSizeLabel);
        infoPanel.add(compressedFileInfoLabel);
        infoPanel.add(compressedFileSizeLabel);
        infoPanel.add(theoreticalEntropy);
        infoPanel.add(reallEntropy);

        this.add(BorderLayout.WEST, huffmanPanel);
        this.add(BorderLayout.EAST, infoPanel);
    }

    private void decodeAndSaveButtonPressed(ActionEvent actionEvent) {
        main.notify("decode&save", actualFile);
    }

    private void encodeAndSaveButtonPressed(ActionEvent actionEvent) {
        main.notify("encode&save", actualFile);

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
            if (actualFile.getName().contains(".huffman")) {
                buttons[1].setEnabled(false);
                buttons[2].setEnabled(false);
                buttons[3].setEnabled(true);
            } else {
                buttons[1].setEnabled(true);
                buttons[2].setEnabled(false);
                buttons[3].setEnabled(false);
            }
        } else {
            buttons[1].setEnabled(false);
        }
    }

    public void setCompressedInfo(HuffmanTree o) {
        compressedFileSizeLabel.setText("Size: " + o.getCompressedSize() + " bytes");
        theoreticalEntropy.setText("Theoretical entropy: " + o.getTheoreticalEntropy());
        reallEntropy.setText("Real entropy: " + o.getRealEntropy());
        reallEntropy.setText("Theorical entropy: " + o);
        StringBuilder sb = new StringBuilder();
        o.getHuffmanTreeMap().forEach((key, value) -> sb.append(key).append(" | ").append(value).append("\n"));
        area.setText(sb.toString());

    }

    public void enableButtons(boolean ... buttons) {
        for (int i = 0; i < buttons.length; i++) {
            this.buttons[i].setEnabled(buttons[i]);
        }
    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void createEncodeOkMessage() {
        JOptionPane.showMessageDialog(this, "Encoding finished");
    }

    public void createDecodeOkMessage() {
        JOptionPane.showMessageDialog(this, "Decoding finished");
    }
}
