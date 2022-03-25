package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class BoardPanel extends JPanel implements MouseListener {

    private int dimension;
    private final int BOARD_SIZE = 600;
    private int cellSize;

    private int [][] cells;

    public BoardPanel(int dimension) {
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.dimension = dimension;
        this.cellSize = BOARD_SIZE / dimension;
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
        initComponents();
    }

    private void initComponents() {
        cells = new int[dimension][dimension];
        Arrays.stream(cells).forEach(cellColumn -> Arrays.fill(cellColumn, -1));
    }

    public void setPiece(int stepNumber, int x, int y) {
        cells[x][y] = stepNumber;
    }

    public void removePiece(int x, int y) {
        cells[x][y] = -1;
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

                // if visited
                if (cells[i][j] != -1) {
                    g.setColor(Color.BLUE);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    g.setColor(Color.black);

                }
            }
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void reset() {
        Arrays.stream(cells).forEach(cellColumn -> Arrays.fill(cellColumn, -1));
    }
}
