package Controller;

import Main.Notifiable;
import Model.Matrix;
import Model.Node;

public class Controller {

    private Notifiable main;
    private Matrix matrix;
    private Thread pathFinder;
    BranchAndBound bnb;

    public Controller(Notifiable main, Matrix matrix) {
        this.main = main;
        this.matrix = matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public void prepare() {
        this.bnb = new BranchAndBound();
        pathFinder = new Thread(this::executeSearch);
        pathFinder.start();
    }

    private void executeSearch() {
        Node finalNode = bnb.branchAndBound(matrix.getMatrix());
        if (finalNode != null) {
            showNodes(finalNode);
        }
        main.notify("finished", finalNode);
    }

    private void showNodes(Node node) {
        Node step = reverse(node);
        while (step != null) {
            matrix.setMatrix(step.getMatrix());
            step = step.getParent();
            main.notify("repaint", null);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    public void shuffle() {
        matrix.shuffle();
        main.notify("shuffleFinished", null);
    }

    public Node reverse(Node step) {
        Node prev = null;
        Node current = step;
        Node next;
        while (current != null) {
            next = current.getParent();
            current.setParent(prev);
            prev = current;
            current = next;
        }
        step = prev;
        return step;
    }

    public void interrupt() {
        bnb.interrupt();
        bnb = null;
    }
}
