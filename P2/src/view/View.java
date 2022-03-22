package view;

import main.Notifiable;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Notifiable {

    private SettingsPanel settingsPanel;
    private BoardPanel boardPanel;
    private StatusPanel statusPanel;

    public View(int dimension) {
        this.setTitle("Chess pieces route");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        settingsPanel = new SettingsPanel(this);
        boardPanel = new BoardPanel(dimension);
        statusPanel = new StatusPanel();
        initComponents();
    }

    private void initComponents() {
        this.add(BorderLayout.NORTH, settingsPanel);
        this.add(BorderLayout.CENTER, boardPanel);
        this.add(BorderLayout.SOUTH, statusPanel);
    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void notify(String s, Object o) {
        if (s.equals("dimensionChanged")) {
            var slider = (JSlider) o;
            if (!slider.getValueIsAdjusting()) {
                boardPanel.setDimension(slider.getValue());
                boardPanel.repaint();
                settingsPanel.setDimension(slider.getValue());
            }
        }
    }
}
