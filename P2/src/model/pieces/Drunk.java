package model.pieces;

import model.AbstractPiece;
import model.Move;

public class Drunk extends AbstractPiece {

    public Drunk() {
        name = this.getClass().getName();
        image = "image/drunk.png";
        moves = SetMoves();
    }

    /*
    TODO: add moves
    alternate between horizontal/vertical step and diagonal step
     */
    private Move[] SetMoves() {
        moves = new Move[8];
        int pos = 0;
        return moves;
    }
}
