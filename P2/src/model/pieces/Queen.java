package model.pieces;

import model.AbstractPiece;
import model.Move;

public class Queen extends AbstractPiece {


    public Queen() {
        name = this.getClass().getName();
        image = "image/rook.png";
        moves = SetMoves();
    }

    private Move[] SetMoves() {
        moves = new Move[(MAX_DIMENSION - 1) * 4 * 2];
        int pos = 0;
        for (int i = -(MAX_DIMENSION - 1); i < MAX_DIMENSION; i++) {
            if (i != 0) {
                moves[pos++] = new Move(0, i);
                moves[pos++] = new Move(i, 0);
                moves[pos++] = new Move(i, i);
                moves[pos++] = new Move(-i, i);
            }
        }
        return moves;
    }
}
