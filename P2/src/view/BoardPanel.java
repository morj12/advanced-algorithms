package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class BoardPanel extends JPanel {

    private int dimension;
    private final int BOARD_SIZE = 600;
    private int cellSize;

    public BoardPanel(int dimension) {
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.dimension = dimension;
        this.cellSize = BOARD_SIZE / dimension;
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_SIZE, BOARD_SIZE);
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
        this.cellSize = BOARD_SIZE / dimension;
    }

    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, BOARD_SIZE, BOARD_SIZE);
        g.setColor(Color.black);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if ((i + j) % 2 == 1) {
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }

    }
}
