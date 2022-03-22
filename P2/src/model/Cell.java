package model;

public class Cell {
    private int x;
    private int y;
    private boolean visited;

    public Cell(int x, int y, boolean visited) {
        this.x = x;
        this.y = y;
        this.visited = visited;
    }

    public Cell() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
