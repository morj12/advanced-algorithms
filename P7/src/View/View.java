package View;

import Main.Notifiable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class View extends JFrame {

    private Notifiable main;

    private JPanel buttonsPanel;
    private JPanel imagePanel;
    private JPanel infoPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton selectImageButton;
    private JButton startButton;
    private JButton stopButton;
    private JLabel flagNameLabel;
    private JLabel statusLabel;
    private BufferedImage flagImage;

    public View(Notifiable main) {
        this.main = main;
        this.setTitle("Flag finder");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        initComponents();
    }

    private void initComponents() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        selectImageButton = new JButton("Select Image");
        selectImageButton.addActionListener(this::selectImageButtonPressed);
        startButton = new JButton("Start");
        startButton.addActionListener(this::startButtonPressed);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this::stopButtonPressed);
        buttonsPanel.add(selectImageButton);
        buttonsPanel.add(startButton);
        buttonsPanel.add(stopButton);

        imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        imagePanel.setPreferredSize(new Dimension(600, 400));

        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        flagNameLabel = new JLabel("No flag chosen");
        statusLabel = new JLabel("Waiting for user");
        leftPanel.add(flagNameLabel);
        rightPanel.add(statusLabel);
        infoPanel.add(BorderLayout.WEST, leftPanel);
        infoPanel.add(BorderLayout.EAST, rightPanel);

        this.add(BorderLayout.NORTH, buttonsPanel);
        this.add(BorderLayout.CENTER, imagePanel);
        this.add(BorderLayout.SOUTH, infoPanel);
    }

    private void selectImageButtonPressed(ActionEvent actionEvent) {
        System.out.println(actionEvent.getActionCommand());
    }

    private void startButtonPressed(ActionEvent actionEvent) {
        System.out.println(actionEvent.getActionCommand());
    }

    private void stopButtonPressed(ActionEvent actionEvent) {
        System.out.println(actionEvent.getActionCommand());
    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
