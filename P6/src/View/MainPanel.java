package View;

import Main.Notifiable;
import Model.Matrix;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class MainPanel extends JFrame {

    private final Notifiable main;
    private Matrix matrix;

    private BoardPanel boardPanel;
    private JPanel buttonsPanel;
    private JComboBox<Integer> cards;
    private JButton startButton;
    private JButton shuffleButton;
    private JButton cancelButton;

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
        this.boardPanel.setMatrix(matrix);
    }

    public MainPanel(Notifiable main, Matrix matrix) {
        this.main = main;
        this.matrix = matrix;
        this.setTitle("Puzzle 8");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        initComponents();
    }

    private void initComponents() {
        cards = new JComboBox<>(new Integer[]{9, 16, 25});
        cards.addItemListener(this::numbersChanged);
        startButton = new JButton("Start");
        startButton.addActionListener(this::startButtonPressed);
        shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(this::shuffleButtonPressed);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancelButtonPressed);
        cancelButton.setEnabled(false);
        buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        buttonsPanel.add(cards);
        buttonsPanel.add(startButton);
        buttonsPanel.add(shuffleButton);
        buttonsPanel.add(cancelButton);
        this.add(BorderLayout.NORTH, buttonsPanel);
        boardPanel = new BoardPanel(matrix);
        this.add(BorderLayout.SOUTH, boardPanel);
    }

    private void numbersChanged(ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == ItemEvent.SELECTED && itemEvent.getID() == ItemEvent.ITEM_STATE_CHANGED)
            main.notify("numbers", cards.getSelectedItem());
    }

    private void cancelButtonPressed(ActionEvent actionEvent) {
        main.notify("cancelled", null);
    }

    private void shuffleButtonPressed(ActionEvent actionEvent) {
        main.notify("shuffle", null);
    }

    private void startButtonPressed(ActionEvent actionEvent) {
        main.notify("start", null);
    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void enableElements(boolean combo, boolean start, boolean shuffle, boolean cancel) {
        cards.setEnabled(combo);
        startButton.setEnabled(start);
        shuffleButton.setEnabled(shuffle);
        cancelButton.setEnabled(cancel);
    }
}
