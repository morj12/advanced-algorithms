package View;

import Main.Notifiable;
import Model.Board;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainPanel extends JFrame {

    private final Notifiable main;
    private Board board;

    private BoardPanel boardPanel;
    private JPanel buttonsPanel;
    private JButton startButton;
    private JButton shuffleButton;

    public MainPanel(Notifiable main, Board board) {
        this.main = main;
        this.board = board;
        this.setTitle("Puzzle 8");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        initComponents();
    }

    private void initComponents() {
        startButton = new JButton("Start");
        startButton.addActionListener(this::startButtonPressed);
        shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(this::shuffleButtonPressed);
        buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        buttonsPanel.add(startButton);
        buttonsPanel.add(shuffleButton);
        this.add(BorderLayout.NORTH, buttonsPanel);
        boardPanel = new BoardPanel(board);
        this.add(BorderLayout.SOUTH, boardPanel);
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

    public void activateButtons(boolean start, boolean shuffle) {
        startButton.setEnabled(start);
        shuffleButton.setEnabled(shuffle);
    }
}
