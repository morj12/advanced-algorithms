package model.pieces;

import model.AbstractPiece;
import model.Position;

public class Drunk extends AbstractPiece {

    public Drunk() {
        name = this.getClass().getSimpleName();
        moves = SetMoves();
    }

    private Position[] SetMoves() {
        moves = new Position[4];
        int pos = 0;
        moves[pos++] = new Position(1,1);
        moves[pos++] = new Position(-1,-1);
        moves[pos++] = new Position(1,-1);
        moves[pos] = new Position(-1,1);
        return moves;
    }
}
