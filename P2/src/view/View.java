package view;

import main.Notifiable;
import model.AbstractPieceCreator;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Arrays;

public class View extends JFrame implements Notifiable {

    private Notifiable main;

    private JPanel settingsPanel;
    private JPanel buttonsPanel;
    private JPanel sliderPanel;
    private JPanel pieceChoosePanel;
    private BoardPanel board;
    private JButton start;
    private JButton stop;
    private JLabel selectedCellLabel;
    private JLabel sliderLabel;
    private JSlider slider;
    private JLabel boxLabel;
    private JComboBox<String> pieceBox;

    private int dimension;
    private int[] selectedCell;

    private final int DEFAULT_DIMENSION = 8;


    public View(Notifiable main, int dimension) {
        this.setTitle("Chess pieces route");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.main = main;
        this.dimension = dimension;
        initComponents();
    }

    public View(Notifiable main) {
        this.setTitle("Chess pieces route");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.main = main;
        this.dimension = DEFAULT_DIMENSION;
        initComponents();
    }

    /**
     * GUI elements configuration only
     **/
    private void initComponents() {

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BorderLayout());
        settingsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(200, 60));
        buttonsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        start = new JButton("Start");
        start.addActionListener(e -> startPressed());
        stop = new JButton("Stop");
        stop.addActionListener(e -> stopPressed());
        selectedCellLabel = new JLabel("Selected cell: none");
        buttonsPanel.add(start);
        buttonsPanel.add(stop);
        buttonsPanel.add(selectedCellLabel);
        settingsPanel.add(BorderLayout.WEST, buttonsPanel);

        sliderPanel = new JPanel();
        sliderPanel.setPreferredSize(new Dimension(200, 60));
        sliderPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        sliderLabel = new JLabel("Dimension: " + dimension);
        slider = new JSlider(SwingConstants.HORIZONTAL, 4, 10, dimension);
        slider.setPreferredSize(new Dimension(180, 20));
        slider.addChangeListener(e -> sliderChanged(slider));
        sliderPanel.add(sliderLabel);
        sliderPanel.add(slider);
        settingsPanel.add(BorderLayout.CENTER, sliderPanel);

        pieceChoosePanel = new JPanel();
        pieceChoosePanel.setPreferredSize(new Dimension(200, 60));
        pieceChoosePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        boxLabel = new JLabel("Select a piece");
        pieceBox = new JComboBox<>();
        pieceBox.addItem("Select a piece");
        Arrays.stream(AbstractPieceCreator.pieces).forEach(piece -> pieceBox.addItem(piece));
        pieceChoosePanel.add(boxLabel);
        pieceChoosePanel.add(pieceBox);
        settingsPanel.add(BorderLayout.EAST, pieceChoosePanel);

        board = new BoardPanel(this, dimension);

        this.add(BorderLayout.NORTH, settingsPanel);
        this.add(BorderLayout.CENTER, board);
    }

    public void reset() {
        board.reset();
        repaint();
    }

    public void setPiece(int stepNumber, int x, int y) {
        board.setPiece(stepNumber, x, y);
    }

    public void removePiece(int x, int y) {
        board.removePiece(x, y);
    }

    /**
     * Mouse stop event
     **/
    private void stopPressed() {
        main.notify("stop", null);
    }

    /**
     * Mouse start event
     **/
    private void startPressed() {
        if (!(pieceBox.getSelectedItem()).equals("Select a piece") && board.isCellSelected()) {
            main.notify(
                    "start:"
                            + slider.getValue()
                            + ","
                            + pieceBox.getSelectedItem()
                            + ","
                            + selectedCell[0]
                            + ","
                            + selectedCell[1], null);
        }
    }

    /**
     * Slider change event
     **/
    private void sliderChanged(JSlider e) {
        if (!e.getValueIsAdjusting()) main.notify("dimension:" + e.getValue(), null);
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
        board.setDimension(dimension);
        board.repaint();
        sliderLabel.setText("Dimension:" + dimension);
    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void lockSettings() {
        slider.setEnabled(false);
        pieceBox.setEnabled(false);
        board.setEnabled(false);
    }

    public void unlockSettings() {
        slider.setEnabled(true);
        pieceBox.setEnabled(true);
        board.setEnabled(true);
    }

    @Override
    public void notify(String s, Object o) {
        if (s.equals("select")) {
            selectedCell = (int[]) o;
            selectedCellLabel.setText("Selected cell: [" + selectedCell[0] + "," + selectedCell[1] + "]");

        } else if (s.equals("unselect")) {
            selectedCell = null;
            selectedCellLabel.setText("Selected cell: none");
        }
    }
}
