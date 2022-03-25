package model.pieces;

import model.AbstractPiece;
import model.Move;

public class Drunk extends AbstractPiece {

    public Drunk() {
        name = this.getClass().getSimpleName();
        image = "image/drunk.png";
        moves = SetMoves();
    }

    private Move[] SetMoves() {
        moves = new Move[8];
        int pos = 0;
        // diagonal moves
        moves[pos++] = new Move(1,1);
        moves[pos++] = new Move(-1,-1);
        moves[pos++] = new Move(1,-1);
        moves[pos] = new Move(-1,1);
        return moves;
    }
}
