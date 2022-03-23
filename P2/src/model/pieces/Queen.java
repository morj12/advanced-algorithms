package model.pieces;

import model.AbstractPiece;
import model.Movement;

public class Queen extends AbstractPiece {

    public Queen() {
        outOfBoundsDimension = true;
        name = this.getClass().getName();
        image = "image/queen.png";
        movements = new Movement[0];
    }

    public Queen(int dimension) {
        outOfBoundsDimension = true;
        name = this.getClass().getName();
        image = "image/rook.png";
        movements = SetMovements(dimension);
    }

    // TODO: add moves
    private Movement[] SetMovements(int limit) {
        movements = new Movement[8];
        int pos = 0;
        return movements;
    }
}
