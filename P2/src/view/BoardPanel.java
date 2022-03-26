package view;

import main.Notifiable;

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
    private Notifiable view;

    private int[][] cells;


    private boolean isCellSelected;
    private int[] selectedCell;

    public BoardPanel(Notifiable view, int dimension) {
        this.view = view;
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.dimension = dimension;
        this.cellSize = BOARD_SIZE / dimension;
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
        initComponents();
        this.addMouseListener(this);
    }

    private void initComponents() {
        isCellSelected = false;
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
        reset();
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
                    g.setFont(g.getFont().deriveFont((float) (BOARD_SIZE / dimension * 0.8)));
                    g.setColor(Color.RED);
                    g.drawString(Integer.toString(cells[i][j]), j * cellSize, i * cellSize + cellSize);
                    g.setColor(Color.black);

                }
            }
        }

    }

    public boolean isCellSelected() {
        return isCellSelected;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.isEnabled()) {
            int x = e.getX() / cellSize;
            int y = e.getY() / cellSize;
            if (!isCellSelected || (!(selectedCell[0] == x && selectedCell[1] == y))) {
                selectedCell = new int[]{x, y};
                isCellSelected = true;
                view.notify("select", selectedCell);
            } else  {
                selectedCell = null;
                isCellSelected = false;
                view.notify("unselect", null);
            }
        }
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
        cells = new int[dimension][dimension];
        Arrays.stream(cells).forEach(cellColumn -> Arrays.fill(cellColumn, -1));
    }
}
