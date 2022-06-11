package Controller;

import Main.Notifiable;
import Model.Board;
import Model.Node;
import Utility.MatrixOperations;

public class Controller {

    private Notifiable main;
    private Board board;
    private Thread pathFinder;

    public Controller(Notifiable main, Board board) {
        this.main = main;
        this.board = board;
    }

    public void prepare() {
        pathFinder = new Thread(this::executeSearch);
        pathFinder.start();
    }

    private void executeSearch() {
        BranchAndBound bnb = new BranchAndBound();
        Node finalNode = bnb.solve(board.getMatrix());
        if (finalNode != null) {
            showNodes(finalNode);
        }

        main.notify("finished", finalNode);
    }

    private void showNodes(Node node) {
        Node step = reverse(node);
        while (step != null) {
            board.setMatrix(step.matrix);
            step = step.parent;
            main.notify("repaint", null);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    public void shuffle() {
        board.shuffle();
        main.notify("shuffleFinished", null);
    }

    public Node reverse(Node step) {
        Node prev = null;
        Node current = step;
        Node next;
        while (current != null) {
            next = current.parent;
            current.parent = prev;
            prev = current;
            current = next;
        }
        step = prev;
        return step;
    }

}
