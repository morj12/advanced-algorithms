package model.pieces;

import model.AbstractPiece;
import model.Position;

public class Queen extends AbstractPiece {


    public Queen() {
        name = this.getClass().getName();
        moves = SetMoves();
        hasState = false;
    }

    private Position[] SetMoves() {
        moves = new Position[(MAX_DIMENSION - 1) * 4 * 2];
        int pos = 0;
        for (int i = -(MAX_DIMENSION - 1); i < MAX_DIMENSION; i++) {
            if (i != 0) {
                moves[pos++] = new Position(0, i);
                moves[pos++] = new Position(i, 0);
                moves[pos++] = new Position(i, i);
                moves[pos++] = new Position(-i, i);
            }
        }
        return moves;
    }
}
