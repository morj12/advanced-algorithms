package controller;

import main.Notifiable;
import model.AbstractPiece;
import model.Board;
import model.Move;

public class Controller implements Notifiable {

    private Notifiable main;
    private boolean isExecuted;
    private Board board;
    private final int WAIT = 333;

    /**
     * Gets main reference
     * Inits the mutex
     */
    public Controller(Notifiable main) {
        this.main = main;
        this.isExecuted = false;
    }

    /**
     * Creates hasSolution boolean
     * Creates new board
     * Creates a thread that executes findPaths and assigns the returning value to hasSolution
     */
    public void start(int dimension, AbstractPiece piece, int x, int y) {
        board = new Board(dimension);
        this.isExecuted = true;
        var t = new Thread(() -> prepare(piece, x, y));
        t.start();

    }

    /**
     * Creates a boolean and assigns it the value of findPath
     */
    private void prepare(AbstractPiece piece, int x, int y) {
        boolean hasSolution;
        hasSolution = findPath(piece, 0, x, y);
        if (hasSolution) {
            main.notify("finished", null);
        } else {
            main.notify("finishedNoSolution", null);
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
            /** If finished return true **/
            if (stepNumber == board.getDimension() * board.getDimension()) {
                return true;
            }
            /** Get moves **/
            Move[] moves = piece.getMoves();
            int[] newPosition;
            for (Move move : moves) {
                newPosition = move.getMoveAsArray();
                newPosition[0] += x;
                newPosition[1] += y;
                /** Continue with backtracking if the move is valid **/
                if (isValidPosition(newPosition)) {
                    if (findPath(piece, stepNumber, newPosition[0], newPosition[1])) return true;
                }
            }
            /** Find another path **/
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
