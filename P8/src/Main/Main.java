package Main;

import Controller.Controller;
import Model.Edge;
import Model.Graph;
import Model.Vertex;
import Utils.Constants;
import View.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main implements Notifiable {

    Controller controller;
    View view;
    Graph country;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Main().start();
    }

    private void start() {
        List<Vertex> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        addNodes(nodes);
        addEdges(edges, nodes);

        country = new Graph(nodes, edges);
        controller = new Controller(this, country);
        view = new View(this, country);
        view.showGui();
    }

    @Override
    public void notify(String message, Object... arguments) {
        switch (message) {
            case "findPath" -> controller.executeAsync((Vertex) arguments[0], (Vertex) arguments[1]);
            case "pathFound" -> view.setPath((List<Vertex>) arguments[0]);
        }
    }

    private void addNodes(List<Vertex> nodes) {
        Arrays.stream(Constants.cityNames).forEach((city) -> nodes.add(new Vertex(city)));
    }

    private void addEdges(List<Edge> edges, List<Vertex> nodes) {
        addEdges(nodes, edges, 0, 1, 63);
        addEdges(nodes, edges, 1, 2, 29);
        addEdges(nodes, edges, 1, 12, 73);
        addEdges(nodes, edges, 1, 14, 106);
        addEdges(nodes, edges, 12, 14, 52);
        addEdges(nodes, edges, 12, 13, 32);
        addEdges(nodes, edges, 13, 14, 37);
        addEdges(nodes, edges, 13, 17, 90);
        addEdges(nodes, edges, 13, 16, 64);
        addEdges(nodes, edges, 17, 18, 170);
        addEdges(nodes, edges, 17, 16, 45);
        addEdges(nodes, edges, 16, 28, 80);
        addEdges(nodes, edges, 16, 15, 54);
        addEdges(nodes, edges, 14, 5, 39);
        addEdges(nodes, edges, 15, 14, 110);
        addEdges(nodes, edges, 5, 2, 131);
        addEdges(nodes, edges, 3, 2, 41);
        addEdges(nodes, edges, 4, 3, 88);
        addEdges(nodes, edges, 7, 4, 89);
        addEdges(nodes, edges, 6, 5, 18);
        addEdges(nodes, edges, 15, 6, 70);
        addEdges(nodes, edges, 4, 5, 41);
        addEdges(nodes, edges, 18, 28, 51);
        addEdges(nodes, edges, 15, 28, 52);
        addEdges(nodes, edges, 29, 28, 50);
        addEdges(nodes, edges, 27, 28, 29);
        addEdges(nodes, edges, 21, 28, 92);
        addEdges(nodes, edges, 19, 27, 45);
        addEdges(nodes, edges, 19, 18, 55);
        addEdges(nodes, edges, 19, 20, 41);
        addEdges(nodes, edges, 20, 27, 72);
        addEdges(nodes, edges, 26, 20, 40);
        addEdges(nodes, edges, 20, 21, 70);
        addEdges(nodes, edges, 25, 26, 123);
        addEdges(nodes, edges, 24, 21, 45);
        addEdges(nodes, edges, 25, 24, 56);
        addEdges(nodes, edges, 25, 23, 75);
        addEdges(nodes, edges, 23, 21, 120);
        addEdges(nodes, edges, 4, 21, 212);
        addEdges(nodes, edges, 4, 24, 256);
        addEdges(nodes, edges, 11, 29, 37);
        addEdges(nodes, edges, 10, 11, 72);
        addEdges(nodes, edges, 9, 10, 35);
        addEdges(nodes, edges, 7, 9, 48);
        addEdges(nodes, edges, 8, 9, 53);
        addEdges(nodes, edges, 7, 8, 47);
        addEdges(nodes, edges, 22, 8, 129);
        addEdges(nodes, edges, 23, 22, 57);
    }

    private void addEdges(
            List<Vertex> nodes,
            List<Edge> edges,
            int first,
            int second,
            int distance) {
        edges.add(new Edge(nodes.get(first), nodes.get(second), distance));
        edges.add(new Edge(nodes.get(second), nodes.get(first), distance));
    }
}
