package View;

import Model.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardPanel extends JPanel {

    private Board board;

    private static int size = 600;
    private static int dimension = 3;
    private static int cellSize = size / dimension;

    private BufferedImage img;
    private BufferedImage[] imgs;

    public BoardPanel(Board board) {
        this.board = board;
        try {
            img = ImageIO.read(new File("src/img/floppa.png"));
            imgs = new BufferedImage[dimension * dimension];
            int counter = 0;

            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    imgs[counter++] = img.getSubimage(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }


    @Override
    public void paint(Graphics g) {
        int[][] matrix = board.getMatrix();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (matrix[j][i] != 0) {
                    g.drawImage(imgs[matrix[j][i]],
                            i * cellSize,
                            j * cellSize,
                            this);
                }
            }
        }
    }
}
