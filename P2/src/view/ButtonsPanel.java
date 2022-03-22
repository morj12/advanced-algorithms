package view;

import main.Notifiable;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    private Notifiable view;
    private JButton startButton;
    private JButton stopButton;
    private JLabel sliderLabel;
    private JSlider slider;

    public ButtonsPanel(Notifiable view) {
        this.view = view;
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        initComponents();
    }

    private void initComponents() {
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(180, 30));
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(180, 30));
        sliderLabel = new JLabel("Dimension : 8");
        slider = new JSlider(SwingConstants.HORIZONTAL, 4, 10, 8);
        slider.setPreferredSize(new Dimension(180, 25));
        slider.addChangeListener(e -> view.notify("dimensionChanged", e.getSource())); // change listener
        this.add(sliderLabel);
        this.add(slider);
        this.add(startButton);
        this.add(stopButton);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 140);
    }

    public void setDimension(String dimension) {
        sliderLabel.setText("Dimension: " + dimension);
    }
}
