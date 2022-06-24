package Model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertex> vertexes;
    private List<Edge> edges;

    public Graph() {
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void setVertexes(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }



}