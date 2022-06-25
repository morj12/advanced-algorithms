package View;

import Main.Notifiable;
import Model.Country;
import Model.Town;
import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class View extends JFrame implements MouseListener {

    private final Notifiable main;
    private final Country country;

    private Map map;
    private JPanel interfacePanel;
    private JLabel pathLabel;

    private int source;
    private int dest;


    public View(Notifiable main, Country country) {
        this.main = main;
        this.country = country;
        this.setTitle("Minimal path finder");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        initComponents();
    }

    public void initComponents() {
        map = new Map(country);
        interfacePanel = new JPanel();
        pathLabel = new JLabel("Path: ");
        pathLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        pathLabel.setHorizontalAlignment(SwingConstants.CENTER);
        interfacePanel.add(pathLabel);
        map.addMouseListener(this);

        this.add(BorderLayout.NORTH, map);
        this.add(BorderLayout.SOUTH, interfacePanel);
    }

    public void showGui() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setPath(List<Town> path) {
        pathLabel.setText("Path: " + getStringPath(path));
        map.setPath(path);
        repaint();

    }

    private String getStringPath(List<Town> path) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < path.size() - 1; i++) {
            s.append(path.get(i)).append(" -> ");
        }
        s.append(path.get(path.size() - 1));
        return s.toString();
    }

    private boolean clickedInsideCoords(MouseEvent e, int[] coords) {
        int x = e.getX();
        int y = e.getY();
        return x >= coords[0] && x <= coords[0] + Constants.MARKER_SIZE
                && y >= coords[1] && y <= coords[1] + Constants.MARKER_SIZE;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int[][] cityCoordinates = Constants.cityCoordinates;
        for (int i = 0; i < cityCoordinates.length; i++) {
            int[] coords = cityCoordinates[i];
            if (clickedInsideCoords(e, coords)) {
                if (country.isSelected(i)) {
                    country.setSelected(i, false);
                    if (country.getSelectedNumber() == 1 && i == source) source = dest;
                    map.setPath(null);
                    pathLabel.setText("Path: ");
                } else if (country.getSelectedNumber() < 2) {
                    country.setSelected(i, true);
                    if (country.getSelectedNumber() == 1) {
                        source = i;
                    } else {
                        dest = i;
                        main.notify("findPath",
                                country.getTown(source),
                                country.getTown(dest));
                    }
                }
                map.repaint();
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
}
