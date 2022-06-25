package View;

import Main.Main;
import Main.Notifiable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View extends JFrame {

    private Notifiable main;

    private JPanel buttonsPanel;

    private ImagePanel imagePanel;

    private JPanel infoPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton loadFlagsButton;
    private JButton selectImageButton;
    private JButton startButton;
    private JButton stopButton;
    private JSlider samplesSlider;
    private JLabel samplesNumberLabel;
    private JProgressBar progressBar;
    private JLabel flagNameLabel;
    private JLabel statusLabel;

    private BufferedImage image;

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

        loadFlagsButton = new JButton("Load all flags");
        loadFlagsButton.addActionListener(this::loadFlagsButtonPressed);
        selectImageButton = new JButton("Select Image");
        selectImageButton.addActionListener(this::selectImageButtonPressed);
        startButton = new JButton("Start");
        startButton.addActionListener(this::startButtonPressed);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this::stopButtonPressed);
        samplesSlider = new JSlider(SwingConstants.HORIZONTAL, 1000, 50000, 25000);
        samplesSlider.addChangeListener(this::sliderChanged);
        samplesNumberLabel = new JLabel("Samples: " + samplesSlider.getValue());
        samplesSlider.setPreferredSize(new Dimension(100, 20));
        buttonsPanel.add(loadFlagsButton);
        buttonsPanel.add(selectImageButton);
        buttonsPanel.add(startButton);
        buttonsPanel.add(stopButton);
        buttonsPanel.add(samplesSlider);
        buttonsPanel.add(samplesNumberLabel);

        imagePanel = new ImagePanel();
        imagePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
        progressBar.setStringPainted(true);
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        flagNameLabel = new JLabel("No flag chosen");
        flagNameLabel.setPreferredSize(new Dimension(150, 20));
        statusLabel = new JLabel("Waiting for user");
        statusLabel.setPreferredSize(new Dimension(150, 20));
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        leftPanel.add(flagNameLabel);
        rightPanel.add(statusLabel);
        infoPanel.add(BorderLayout.CENTER, progressBar);
        infoPanel.add(BorderLayout.WEST, leftPanel);
        infoPanel.add(BorderLayout.EAST, rightPanel);

        this.add(BorderLayout.NORTH, buttonsPanel);
        this.add(BorderLayout.CENTER, imagePanel);
        this.add(BorderLayout.SOUTH, infoPanel);

        enableButtons(true, false, false, false);
    }

    private void sliderChanged(ChangeEvent changeEvent) {
        if (samplesSlider.getValueIsAdjusting()) {
            samplesNumberLabel.setText("Samples: " + samplesSlider.getValue());
            main.notify("samples", samplesSlider.getValue());
        }
    }

    public void clearAndPrepareProgress(int max) {
        progressBar.setValue(0);
        progressBar.setMaximum(max);
    }

    public void updateProgress() {
        progressBar.setValue(progressBar.getValue() + 1);
    }

    private void loadFlagsButtonPressed(ActionEvent actionEvent) {
        main.notify("load");
    }

    private void selectImageButtonPressed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser(new File(Main.EXAMPLE_FLAGS));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setDialogTitle("Open flag");
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jFileChooser.getSelectedFile();
                if (isImage(file.getName())) {
                    image = ImageIO.read(file);
                    imagePanel.updateImage(image);
                    flagNameLabel.setText(file.getName());
                    startButton.setEnabled(true);
                    Main.SAMPLES_NUMBER = image.getHeight() * image.getWidth();
                    samplesSlider.setMaximum(Main.SAMPLES_NUMBER);
                    samplesSlider.setValue(Main.SAMPLES_NUMBER / 2);
                    samplesNumberLabel.setText("Samples: " + samplesSlider.getValue());
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "The chosen file is not an image"
                    );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean isImage(String fileName) {
        return fileName.matches(".*\\.(png|jpg|jpeg|)");
    }

    private void startButtonPressed(ActionEvent actionEvent) {
        if (image != null) {
            main.notify("start", image);
        }
    }

    private void stopButtonPressed(ActionEvent actionEvent) {
        main.notify("stop");
    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void enableButtons(boolean load, boolean select, boolean start, boolean stop) {
        loadFlagsButton.setEnabled(load);
        selectImageButton.setEnabled(select);
        startButton.setEnabled(start);
        stopButton.setEnabled(stop);
    }

    public void showNewFlag(String flagName) {
        if (!flagName.equals("")) {
            statusLabel.setText("Found: " + flagName);
            ImageIcon flagIcon = new ImageIcon(Main.ALL_FLAGS_DIRECTORY + flagName);
            JOptionPane.showMessageDialog(this,
                    "Flag found. The file name is: " + flagName, "Found", JOptionPane.PLAIN_MESSAGE,
                    scaleImage(flagIcon, 220, 150));
        } else {
            statusLabel.setText("Not found.");
            JOptionPane.showMessageDialog(this,
                    "Flag not found. Please, try again.", "Not found", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ImageIcon scaleImage(ImageIcon icon, int w, int h) {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();
        if (icon.getIconWidth() > w) {
            nw = w;
            nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if (nh > h) {
            nh = h;
            nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
}
