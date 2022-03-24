package model.pieces;

import model.Move;
import model.AbstractPiece;

public class Megaknight extends AbstractPiece {

    public Megaknight() {
        name = this.getClass().getName();
        image = "image/megaknight.png";
        moves = SetMoves();
    }

    private Move[] SetMoves() {
        moves = new Move[8];
        int pos = 0;

        moves[pos++] = new Move(0,2);
        moves[pos++] = new Move(2,2);
        moves[pos++] = new Move(2,0);
        moves[pos++] = new Move(2,-2);
        moves[pos++] = new Move(0,-2);
        moves[pos++] = new Move(-2,-2);
        moves[pos++] = new Move(-2,0);
        moves[pos] = new Move(-2,2);

        return moves;
    }
}
