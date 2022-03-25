package view;

import main.Notifiable;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Notifiable {

    private Notifiable main;
    private SettingsPanel settingsPanel;
    private BoardPanel boardPanel;
    private int[] startCoords;

    public View(Notifiable main, int dimension) {
        this.setTitle("Chess pieces route");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.main = main;
        settingsPanel = new SettingsPanel(this);
        boardPanel = new BoardPanel(dimension);
        initComponents();
    }

    private void initComponents() {
        startCoords = new int[2];
        this.add(BorderLayout.NORTH, settingsPanel);
        this.add(BorderLayout.CENTER, boardPanel);
    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setPiece(int stepNumber, int x, int y) {
        boardPanel.setPiece(stepNumber, x, y);
    }

    public void removePiece(int x, int y) {
        boardPanel.removePiece(x, y);
    }


    @Override
    public void notify(String s, Object o) {
        // repaint board panel based on new dimension
        if (s.equals("dimensionChanged")) {
            var slider = (JSlider) o;
            if (!slider.getValueIsAdjusting()) {
                boardPanel.setDimension(slider.getValue());
                boardPanel.repaint();
                settingsPanel.setDimension(slider.getValue());
            }
        } else if (s.equals("start")) {
            var piece = settingsPanel.getPiece();
            if (!piece.equals("Select a piece")) {

            }
            // try algorithm start
        } else if (s.equals("stop")) {
            // try algorithm stop
        }
    }
}
