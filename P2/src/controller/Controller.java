package controller;

import main.Notifiable;
import model.AbstractPiece;
import model.Board;
import model.Move;

import java.util.concurrent.Semaphore;

public class Controller implements Notifiable {

    private Notifiable main;
    private boolean isExecuted;     // not used
    private Board board;
    private Semaphore mutex;        // not used

    private final int WAIT = 333;
    private Thread algorithm;

    /**
     * Gets main reference
     * Inits the mutex
     */
    public Controller(Notifiable main) {
        this.main = main;
        this.mutex = new Semaphore(1);
    }

    /**
     * Creates hasSolution boolean
     * Creates new board
     * Creates a thread that executes findPaths and assigns the returning value to hasSolution
     */
    public void start(int dimension, AbstractPiece piece, int x, int y) {
        board = new Board(dimension);
        main.notify("started", null);
        this.isExecuted = true;
        algorithm = new Thread(() -> prepare(piece, x, y));
        algorithm.start();

    }

    /**
     * Creates a boolean and assigns it the value of findPath
     */
    private void prepare(AbstractPiece piece, int x, int y) {
        boolean hasSolution;
        hasSolution = findPath(piece, 0, x, y);
        if (hasSolution) {
            System.out.println("DONE step: " + step);
        } else {
            System.out.println("FAILED step: " + step);
        }
    }

    /**
     * Backtracking algorithm for one piece
     * Needs to use mutex on operations "setPiece" and "removePiece" (NOT USED NOW)
     */
    private int step = 0;

    private boolean findPath(AbstractPiece piece, int stepNumber, int x, int y) {
        if (isExecuted) {
            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
            }
            main.notify("draw:" + stepNumber + "," + x + "," + y, null);
            board.putPiece(piece, stepNumber++, x, y);
            if (stepNumber == board.getDimension() * board.getDimension()) {
                main.notify("finished", null);
                return true;
            }
            // get all moves and loop foreach
            Move[] moves = piece.getMoves();
            int[] newPosition;
            for (Move move : moves) {
                // get the new position
                newPosition = move.getMoveAsArray();
                newPosition[0] += x;
                newPosition[1] += y;
                // check if it's out of bounds
                if (isValidPosition(newPosition)) {
                    if (findPath(piece, stepNumber, newPosition[0], newPosition[1])) return true;
                }
            }
            // if no solution, clear
            board.removePiece(x, y);
            main.notify("remove:" + x + "," + y, null);
        }
        return false;
    }

    private boolean isValidPosition(int[] newPosition) {
        return !(newPosition[0] < 0
                || newPosition[0] >= board.getDimension()
                || newPosition[1] < 0
                || newPosition[1] >= board.getDimension()
                || board.isVisitedCell(newPosition[0], newPosition[1]));
    }


    public void stopAlgorithm() {
        isExecuted = false;
    }

    @Override
    public void notify(String s, Object o) {

    }
}
