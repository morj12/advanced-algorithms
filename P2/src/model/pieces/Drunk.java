package model.pieces;

import model.AbstractPiece;
import model.Move;

public class Drunk extends AbstractPiece {

    public Drunk() {
        name = this.getClass().getSimpleName();
        moves = SetMoves();
    }

    private Move[] SetMoves() {
        moves = new Move[4];
        int pos = 0;
        moves[pos++] = new Move(1,1);
        moves[pos++] = new Move(-1,-1);
        moves[pos++] = new Move(1,-1);
        moves[pos] = new Move(-1,1);
        return moves;
    }
}
