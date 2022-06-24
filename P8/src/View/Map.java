package View;

import Utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Map extends JPanel {

    public static final int HEIGHT = 900;
    public static final int WIDTH = 1600;

    private BufferedImage map;
    private int selectedCount;

    private boolean[] selectedIndexes;
    private List<Integer> path;


    public Map() {
        selectedCount = 0;
        selectedIndexes = new boolean[30];

        try {
            map = ImageIO.read(new File("src/Img/map.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPath(List<Integer> path) {
        this.path = path;
        this.repaint();
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void select(int i) {
        selectedIndexes[i] = true;
        selectedCount++;
        this.repaint();
    }

    public void unselect(int i) {
        selectedIndexes[i] = false;
        selectedCount--;
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(map, 0, 0, WIDTH, HEIGHT, null);
        for (int i = 0; i < Constants.cityCoordinates.length; i++) {
            if (selectedIndexes[i]) {
                g.setColor(Color.BLUE);
            } else if (path != null && path.contains(i)) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.RED);
            }
            g.fillOval(Constants.cityCoordinates[i][0], Constants.cityCoordinates[i][1], 15, 15);
        }
    }


}
