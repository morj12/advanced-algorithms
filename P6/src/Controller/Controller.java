package Controller;

import Main.Notifiable;
import Model.Board;
import Model.Step;

public class Controller {

    private Notifiable main;
    private Board board;
    private Thread pathFinder;
    private int dimension;

    public Controller(Notifiable main, Board board, int dimension) {
        this.main = main;
        this.board = board;
        this.dimension = dimension;
    }

    public void prepare() {
        pathFinder = new Thread(this::executeSearch);
        pathFinder.start();
    }

    private void executeSearch() {
        int counter = 0;
        int[] coords = Utility.getZeroCoords(board.getMatrix());
        int[][] finalMatrix = new int[dimension][dimension];
        for (int i = 0; i < finalMatrix.length; i++) {
            for (int j = 0; j < finalMatrix[i].length; j++) {
                finalMatrix[i][j] = counter++;
            }
        }

        Step finalStep = Step.solve(board.getMatrix(), coords[0], coords[1], finalMatrix);
        if (finalStep != null) {
            showSteps(finalStep);
        }
        main.notify("finished", finalStep);
    }

    private void showSteps(Step finalStep) {
        Step step = reverse(finalStep);
        while (step != null) {
            board.setMatrix(step.getMatrix());
            step = step.getParent();
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

    public Step reverse(Step step) {
        Step prev = null;
        Step current = step;
        Step next;
        while (current != null) {
            next = current.getParent();
            current.setParent(prev);
            prev = current;
            current = next;
        }
        step = prev;
        return step;
    }

}
