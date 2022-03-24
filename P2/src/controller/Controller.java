package controller;

import main.Notifiable;
import model.AbstractPiece;
import model.Board;
import model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.exit;

public class Controller implements Notifiable {

    private Notifiable main;
    private boolean isExecuted;
    private Board board;
    private Semaphore mutex;

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
    public void start(int dimension, AbstractPiece[] pieces, int[] x, int[] y) {
        AtomicBoolean hasSolution = new AtomicBoolean(false);
        board = new Board(dimension);
        var t = new Thread(() -> hasSolution.set(findPaths(pieces, x, y)));
        t.start();

    }

    /**
     * Creates a list of booleans for each piece
     * For each piece calls findPath and assigns the returning value
     * If all elements are true, returns true
     * Else returns false
     */
    private boolean findPaths(AbstractPiece[] pieces, int[] x, int[] y) {
        List<Boolean> hasSolution = new ArrayList<>();

        for (int i = 0; i < pieces.length; i++) {
            hasSolution.add(findPath(pieces[i], 0, x[i], y[i]));
        }
        if (hasSolution.stream().allMatch(b -> b.booleanValue())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Backtracking algorithm for one piece
     * Needs to use mutex on operations "setPiece" and "removePiece"
     */

    private int calls = 0;

    private boolean findPath(AbstractPiece piece, int stepNumber, int x, int y) {
        calls++;
        board.putPiece(piece, stepNumber++, x, y);
        if (stepNumber == board.getDimension() * board.getDimension())  {
            System.out.println(board);
            return true;
        }
        // get all moves and loop foreach
        Move[] moves = piece.getMoves();
        int[] newPosition;
        for (Move move : moves) {
            try {
                // get the new position
                newPosition = move.getMoveAsArray();
                newPosition[0] += x;
                newPosition[1] += y;
                // check if it's out of bounds
                if (!(newPosition[0] < 0
                        || newPosition[0] >= board.getDimension()
                        || newPosition[1] < 0
                        || newPosition[1] >= board.getDimension())) {
                    if (findPath(piece, newPosition[0], newPosition[1], stepNumber)) return true;
                }
            } catch (StackOverflowError e) {
                System.out.println("STACKOVERFLOWERROR");
                System.out.println("stepnumber = " + stepNumber);
                System.out.println("calls = " + calls);
                exit(0);
            }
        }
        // if no solution, clear
        board.removePiece(x, y);
        return false;
    }


    @Override
    public void notify(String s, Object o) {

    }
}
