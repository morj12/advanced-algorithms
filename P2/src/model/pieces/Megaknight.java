package model.pieces;

import model.Position;
import model.AbstractPiece;

public class Megaknight extends AbstractPiece {

    private static boolean state = false;
    private Position[] actualMoves;
    private int stepsize;

    public Megaknight() {
        name = this.getClass().getSimpleName();
        moves = setMoves();
        hasState = true;
        stepsize = 1;
    }

    private Position[] setMoves() {
        moves = new Position[8];
        int pos = 0;
        moves[pos++] = new Position(0,stepsize);
        moves[pos++] = new Position(stepsize,stepsize);
        moves[pos++] = new Position(stepsize,0);
        moves[pos++] = new Position(stepsize,-stepsize);
        moves[pos++] = new Position(0,-stepsize);
        moves[pos++] = new Position(-stepsize,-stepsize);
        moves[pos++] = new Position(-stepsize,0);
        moves[pos] = new Position(-stepsize,stepsize);
        return moves;
    }

    /** Get moves changing state **/
    @Override
    public Position[] getMoves() {
        if (!state) {
            stepsize = 2;
        } else {
            stepsize = 1;
        }
        state = !state;
        moves = setMoves();
        return moves;
    }

    /** Get actual moves (no state change) **/
    @Override
    public Position[] getMovesWithoutStateChange() {
        return moves;
    }


}
