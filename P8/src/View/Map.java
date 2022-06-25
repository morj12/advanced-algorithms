package View;

import Model.Country;
import Model.Town;
import Utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Map extends JPanel {

    private final Country country;

    private BufferedImage map;

    private List<Town> path;


    public Map(Country country) {
        this.country = country;

        try {
            map = ImageIO.read(new File("src/Img/map.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPath(List<Town> path) {
        this.path = path;
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(map, 0, 0, Constants.MAP_WIDTH, Constants.MAP_HEIGHT, null);
        for (int i = 0; i < country.getTowns().size(); i++) {
            if (country.isSelected(i)) {
                g.setColor(Color.BLUE);
            } else if (path != null && path.contains(country.getTown(i))) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.RED);
            }
            g.fillOval(Constants.cityCoordinates[i][0],
                    Constants.cityCoordinates[i][1],
                    Constants.MARKER_SIZE,
                    Constants.MARKER_SIZE);
        }
    }


}
