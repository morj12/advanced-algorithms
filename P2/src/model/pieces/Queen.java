package model.pieces;

import model.AbstractPiece;
import model.Move;

public class Queen extends AbstractPiece {

    public Queen() {
        outOfBoundsDimension = true;
        name = this.getClass().getName();
        image = "image/queen.png";
        moves = new Move[0];
    }

    public Queen(int dimension) {
        outOfBoundsDimension = true;
        name = this.getClass().getName();
        image = "image/rook.png";
        moves = SetMoves(dimension);
    }

    private Move[] SetMoves(int limit) {
        moves = new Move[(limit - 1) * 4 * 2];
        int pos = 0;
        for (int i = -(limit - 1); i < limit; i++) {
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
