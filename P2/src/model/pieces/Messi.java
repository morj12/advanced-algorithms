package model.pieces;

import model.AbstractPiece;
import model.Position;

import java.util.Arrays;

public class Messi extends AbstractPiece {

    private static boolean state = false;
    private Position[] actualMoves;

    public Messi() {
        name = this.getClass().getSimpleName();
        moves = SetMoves();
        hasState = true;
    }

    private Position[] SetMoves() {
        moves = new Position[16];
        int pos = 0;
        // knight moves
        moves[pos++] = new Position(1, 2);
        moves[pos++] = new Position(2, 1);
        moves[pos++] = new Position(1, -2);
        moves[pos++] = new Position(2, -1);
        moves[pos++] = new Position(-1, 2);
        moves[pos++] = new Position(-2, 1);
        moves[pos++] = new Position(-1, -2);
        moves[pos++] = new Position(-2, -1);
        // megaknight moves
        moves[pos++] = new Position(0, 2);
        moves[pos++] = new Position(2, 2);
        moves[pos++] = new Position(2, 0);
        moves[pos++] = new Position(2, -2);
        moves[pos++] = new Position(0, -2);
        moves[pos++] = new Position(-2, -2);
        moves[pos++] = new Position(-2, 0);
        moves[pos] = new Position(-2, 2);
        return moves;
    }

    /** Get moves changing state **/
    @Override
    public Position[] getMoves() {
        if (!state) {
            actualMoves = Arrays.copyOfRange(moves, 0, moves.length / 2 - 1);
        } else {
            actualMoves = Arrays.copyOfRange(moves, moves.length / 2, moves.length - 1);
        }
        state = !state;
        return actualMoves;
    }

    /** Get actual moves (no state change) **/
    @Override
    public Position[] getMovesWithoutStateChange() {
        return actualMoves;
    }
}
