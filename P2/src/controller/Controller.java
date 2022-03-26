package controller;

import main.Notifiable;
import model.AbstractPiece;
import model.Board;
import model.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Controller implements Notifiable {

    private final int DEFAULT_SPEED = 500;

    private Notifiable main;
    private boolean isExecuted;
    private Board board;
    private int speed;

    /**
     * Gets main reference
     * Inits the mutex
     */
    public Controller(Notifiable main) {
        this.main = main;
        this.isExecuted = false;
        this.speed = DEFAULT_SPEED;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Creates hasSolution boolean
     * Creates new board
     * Creates a thread that executes findPaths and assigns the returning value to hasSolution
     */
    public void start(int dimension, AbstractPiece piece, Position position) {
        board = new Board(dimension);
        this.isExecuted = true;
        var t = new Thread(() -> prepare(piece, position));
        t.start();

    }

    /**
     * Creates a boolean and assigns it the value of findPath
     */
    private void prepare(AbstractPiece piece, Position position) {
        boolean hasSolution;
        hasSolution = findPath(piece, 0, position);
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

    private boolean findPath(AbstractPiece piece, int stepNumber, Position position) {
        if (isExecuted) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
            }
            main.notify("draw:" + stepNumber + "," + position.getX() + "," + position.getY(), null);
            board.putPiece(piece, stepNumber++, position);
            /** If finished return true **/
            if (stepNumber == board.getDimension() * board.getDimension()) {
                return true;
            }
            /** Get moves **/
            List<Position> moves = optimize(piece.getMoves(), piece, position);
            Position newPosition;
            for (Position move : moves) {
                newPosition = Position.sum(move, position);
                /** Continue with backtracking if the move is valid **/
                if (isValidPosition(newPosition)) {
                    if (findPath(piece, stepNumber, newPosition)) return true;
                }
            }
            /** Find another path **/
            board.removePiece(position);
            main.notify("remove:" + position.getX() + "," + position.getY(), null);
        }
        return false;
    }

    /**
     * Returns a new list of moves ordered by the number
     * of possible derived moves of each move.
     * Uses a wrapper class which assigns the number of derived
     * moves to a move.
     */
    private List<Position> optimize(Position[] moves, AbstractPiece piece, Position position) {
        List<MoveWrapper> wrappedMoves = new ArrayList<>();
        for (Position move : moves) {
            Position pos = Position.sum(move, position);
            wrappedMoves.add(new MoveWrapper(move, getMovesNumber(piece, pos)));
        }
        wrappedMoves.sort(Comparator.comparingInt(wrappedMove -> wrappedMove.possibleMoves));
        List<Position> optimizedMoves = new ArrayList<>();
        wrappedMoves.forEach(move -> optimizedMoves.add(move.move));
        return optimizedMoves;
    }

    /**
     * Just gets the number of moves for a piece at fixed position
     **/
    private int getMovesNumber(AbstractPiece piece, Position position) {
        int counter = 0;
        Position[] moves = piece.getMoves();
        for (Position move : moves) {
            if (isValidPosition(Position.sum(move, position))) counter++;
        }
        return counter;
    }

    /**
     * Wrapper class which assigns the number of derived moves to a move
     **/
    static class MoveWrapper {
        Position move;
        int possibleMoves;

        public MoveWrapper(Position move, int possibleMoves) {
            this.move = move;
            this.possibleMoves = possibleMoves;
        }
    }

    private boolean isValidPosition(Position newPosition) {
        return !(newPosition.getX() < 0
                || newPosition.getX() >= board.getDimension()
                || newPosition.getY() < 0
                || newPosition.getY() >= board.getDimension()
                || board.isVisitedCell(newPosition));
    }


    public void stopAlgorithm() {
        isExecuted = false;
    }


    @Override
    public void notify(String s, Object o) {

    }
}
