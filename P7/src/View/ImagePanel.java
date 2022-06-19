package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage flag;

    public void updateImage(BufferedImage image) {
        this.flag = image;
        this.repaint();
    }

    public BufferedImage getFlag() {
        return flag;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    @Override
    public void paint(Graphics g) {
        if (flag != null) {
            g.drawImage(flag, 0, 0, 600, 400, null);
        }
    }
}
