package View;

import Main.Main;
import Model.Matrix;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardPanel extends JPanel {

    private Matrix matrix;

    private static int size = 600;
    private static int cellSize = size / Main.DIMENSION;

    private BufferedImage img;
    private BufferedImage[] imgs;

    public BoardPanel(Matrix matrix) {
        this.matrix = matrix;
        initComponents();
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
        initComponents();
    }

    private void initComponents() {
        cellSize = size / Main.DIMENSION;
        try {
            img = ImageIO.read(new File("src/img/floppa.png"));
            imgs = new BufferedImage[Main.DIMENSION * Main.DIMENSION];
            int counter = 0;

            for (int i = 0; i < Main.DIMENSION; i++) {
                for (int j = 0; j < Main.DIMENSION; j++) {
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
        int[][] matrix = this.matrix.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[j][i] != 0) {
                    g.drawImage(
                            imgs[matrix[j][i]],
                            i * cellSize,
                            j * cellSize,
                            this);
                }
            }
        }
    }
}
