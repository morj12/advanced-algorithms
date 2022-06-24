package View;

import Main.Notifiable;
import Model.Graph;
import Model.Vertex;
import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame implements MouseListener {

    private Notifiable main;
    private Graph graph;

    private Map map;
    private JPanel interfacePanel;
    private JLabel pathLabel;


    public View(Notifiable main, Graph graph) {
        this.main = main;
        this.graph = graph;
        this.setTitle("Minimal path finder");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        initComponents();
    }

    public void initComponents() {
        map = new Map();
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

    public void setPath(List<Vertex> path) {
        pathLabel.setText("Path: " + getStringPath(path));
        List<Integer> indexList = new ArrayList<>();
        path.forEach(vertex -> indexList.add(findIndex(vertex)));
        map.setPath(indexList);

    }

    private String getStringPath(List<Vertex> path) {
        String s = "";
        for (int i = 0; i < path.size() - 2; i++) {
            s += path.get(i) + " -> ";
        }
        s += path.get(path.size() - 1);
        return s;
    }

    public int findIndex(Vertex vertex) {
        for (int i = 0; i < Constants.cityNames.length; i++) {
            if (vertex.getName().equals(Constants.cityNames[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean clickedInsideCoords(MouseEvent e, int[] coords) {
        int x = e.getX();
        int y = e.getY();
        return x >= coords[0] && x <= coords[0] + 15
                && y >= coords[1] && y <= coords[1] + 15;
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int[][] cityCoordinates = Constants.cityCoordinates;
        for (int i = 0; i < cityCoordinates.length; i++) {
            int[] coords = cityCoordinates[i];
            if (clickedInsideCoords(e, coords)) {
                if (graph.getVertexes().get(i).isSelected()) {
                    graph.getVertexes().get(i).setSelected(false);
                    map.unselect(i);
                    map.setPath(null);
                    pathLabel.setText("Path: ");
                } else if (graph.getVertexes().stream().filter(Vertex::isSelected).count() < 2) {
                    graph.getVertexes().get(i).setSelected(true);
                    map.select(i);
                    List<Vertex> selected = graph.getVertexes().stream().filter(Vertex::isSelected).toList();
                    if (map.getSelectedCount() == 2)
                        main.notify("findPath", selected.get(0), selected.get(1));
                }
                System.out.println(map.getSelectedCount());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
