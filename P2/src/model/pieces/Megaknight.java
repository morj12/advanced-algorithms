package model.pieces;

import model.Position;
import model.AbstractPiece;

public class Megaknight extends AbstractPiece {

    public Megaknight() {
        name = this.getClass().getSimpleName();
        moves = SetMoves();
    }

    private Position[] SetMoves() {
        moves = new Position[8];
        int pos = 0;
        moves[pos++] = new Position(0,2);
        moves[pos++] = new Position(2,2);
        moves[pos++] = new Position(2,0);
        moves[pos++] = new Position(2,-2);
        moves[pos++] = new Position(0,-2);
        moves[pos++] = new Position(-2,-2);
        moves[pos++] = new Position(-2,0);
        moves[pos] = new Position(-2,2);

        return moves;
    }
}
