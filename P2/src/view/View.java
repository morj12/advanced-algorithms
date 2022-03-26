package view;

import main.Notifiable;
import model.AbstractPieceCreator;
import model.Position;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Arrays;

public class View extends JFrame implements Notifiable {

    private static View instance;
    private Notifiable main;


    private JPanel settingsPanel;
    private JPanel buttonsPanel;
    private JPanel sliderPanel;
    private JPanel pieceChoosePanel;
    private BoardPanel board;
    private JButton start;
    private JButton stop;
    private JLabel selectedCellLabel;
    private JLabel dimensionSliderLabel;
    private JSlider dimensionSlider;
    private JLabel boxLabel;
    private JComboBox<String> pieceBox;
    private JLabel speedSliderLabel;
    private JSlider speedSlider;

    private int dimension;
    private int[] selectedCell;

    private final int DEFAULT_DIMENSION = 8;
    private final int MINIMUM_WAIT = 20;

    public static View getInstance(Notifiable main) {
        if (instance == null) {
            instance = new View(main);
        }
        return instance;
    }

    private View(Notifiable main) {
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
        dimensionSliderLabel = new JLabel("Dimension: " + dimension);
        dimensionSlider = new JSlider(SwingConstants.HORIZONTAL, 4, 10, dimension);
        dimensionSlider.setPreferredSize(new Dimension(180, 20));
        dimensionSlider.addChangeListener(e -> dimensionSliderChanged(dimensionSlider));
        sliderPanel.add(dimensionSliderLabel);
        sliderPanel.add(dimensionSlider);
        settingsPanel.add(BorderLayout.CENTER, sliderPanel);

        pieceChoosePanel = new JPanel();
        pieceChoosePanel.setPreferredSize(new Dimension(200, 80));
        pieceChoosePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        boxLabel = new JLabel("Select a piece");
        pieceBox = new JComboBox<>();
        pieceBox.addItem("Select a piece");
        Arrays.stream(AbstractPieceCreator.pieces).forEach(piece -> pieceBox.addItem(piece));
        speedSliderLabel = new JLabel("Speed");
        speedSlider = new JSlider(SwingConstants.HORIZONTAL, 0,  1000, 500);
        speedSlider.setPreferredSize(new Dimension(180, 20));
        speedSlider.addChangeListener(e -> speedSliderChanged(speedSlider));
        pieceChoosePanel.add(boxLabel);
        pieceChoosePanel.add(pieceBox);
        pieceChoosePanel.add(speedSliderLabel);
        pieceChoosePanel.add(speedSlider);
        settingsPanel.add(BorderLayout.EAST, pieceChoosePanel);

        board = new BoardPanel(this, dimension);

        this.add(BorderLayout.NORTH, settingsPanel);
        this.add(BorderLayout.CENTER, board);
    }

    /** Speed slider change event **/
    private void speedSliderChanged(JSlider speedSlider) {
        main.notify("speed:", MINIMUM_WAIT + speedSlider.getMaximum() - speedSlider.getValue());
    }

    public void reset() {
        board.reset();
        repaint();
    }

    public void setPiece(int stepNumber, Position position) {
        board.setPiece(stepNumber, position, (String) pieceBox.getSelectedItem());
    }

    public void removePiece(Position position) {
        board.removePiece(position);
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
                            + dimensionSlider.getValue()
                            + ","
                            + pieceBox.getSelectedItem()
                            + ","
                            + selectedCell[0]
                            + ","
                            + selectedCell[1], null);
        }
    }

    /**
     * Dimension slider change event
     **/
    private void dimensionSliderChanged(JSlider e) {
        if (!e.getValueIsAdjusting()) main.notify("dimension:" + e.getValue(), null);
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
        board.setDimension(dimension);
        board.repaint();
        dimensionSliderLabel.setText("Dimension:" + dimension);
    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void lockSettings() {
        dimensionSlider.setEnabled(false);
        pieceBox.setEnabled(false);
        board.setEnabled(false);
    }

    public void unlockSettings() {
        dimensionSlider.setEnabled(true);
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
