package view;

import main.Notifiable;
import model.Position;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class BoardPanel extends JPanel implements MouseListener {

    private int dimension;
    private final int BOARD_SIZE = 600;
    private int cellSize;
    private Notifiable view;
    private BufferedImage image;

    private int[][] cells;
    private Stack<Position> positionStack;

    private boolean isCellSelected;
    private int[] selectedCell;

    public BoardPanel(Notifiable view, int dimension) {
        this.view = view;
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.dimension = dimension;
        this.cellSize = BOARD_SIZE / dimension;
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.positionStack = new Stack<>();
        initComponents();
        this.addMouseListener(this);
    }

    private void initComponents() {
        isCellSelected = false;
        cells = new int[dimension][dimension];
        Arrays.stream(cells).forEach(cellColumn -> Arrays.fill(cellColumn, -1));
    }

    public void setPiece(int stepNumber, Position position, String selectedItem) {
        cells[position.getX()][position.getY()] = stepNumber;
        this.positionStack.push(new Position(position.getX(), position.getY()));

    }

    public void removePiece(Position position) {
        cells[position.getX()][position.getY()] = -1;
        try {
            this.positionStack.pop();
        } catch (EmptyStackException e) {
            positionStack.clear();
        }
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
            }
        }
        
        try {
            if (!positionStack.empty()) {
                g.setColor(Color.red);
                g.setFont(g.getFont().deriveFont((float) (BOARD_SIZE / dimension * 0.8)));

                for (int i = 0; i < positionStack.size() - 1; i++) {
                    if (cells[positionStack.get(i).getX()][positionStack.get(i).getY()] != -1) {
                        g.drawString(Integer.toString(cells[positionStack.get(i).getX()][positionStack.get(i).getY()]), positionStack.get(i).getY() * cellSize, positionStack.get(i).getX() * cellSize + cellSize);
                    }
                }

                g.drawImage(image, positionStack.get(positionStack.size() - 1).getY() * cellSize, positionStack.get(positionStack.size() - 1).getX() * cellSize, cellSize, cellSize, this);
            }
        } catch (EmptyStackException | ArrayIndexOutOfBoundsException e){
            positionStack.clear();
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
            } else {
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
        positionStack.clear();
        Arrays.stream(cells).forEach(cellColumn -> Arrays.fill(cellColumn, -1));
    }

    public void loadPiece(String s) {
        try {
            this.image = ImageIO.read(new File("src/image/" + s + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(BoardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
