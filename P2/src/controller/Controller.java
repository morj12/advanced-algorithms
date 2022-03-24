package controller;

import main.Notifiable;
import model.AbstractPiece;
import model.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

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
        new Thread(() -> hasSolution.set(findPaths(dimension, pieces, x, y))).start();
    }

    /**
     * Creates a list of booleans for each piece
     * For each piece calls findPath and assigns the returning value
     * If all elements are true, returns true
     * Else returns false
     */
    public boolean findPaths(int dimension, AbstractPiece[] pieces, int[] x, int[] y) {
        List<Boolean> hasSolution = new ArrayList<>();

        for (int i = 0; i < pieces.length; i++) {
            hasSolution.add(findPath(dimension, pieces[i], x[i], y[i]));
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
    private boolean findPath(int dimension, AbstractPiece piece, int x, int i) {

        return false;
    }


    @Override
    public void notify(String s, Object o) {

    }
}
