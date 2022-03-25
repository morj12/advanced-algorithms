package model.pieces;

import model.AbstractPiece;
import model.Move;

public class Drunk extends AbstractPiece {

    private Move[] alternatedMoves;

    public Drunk() {
        name = this.getClass().getSimpleName();
        image = "image/drunk.png";
        moves = SetMoves();
        hasState = true;
    }

    /*
    TODO: add moves
    alternate between horizontal/vertical step and diagonal step
     */
    private Move[] SetMoves() {
        moves = new Move[8];
        int pos = 0;
        // horizontal and vertical moves
        moves[pos++] = new Move(0,1);
        moves[pos++] = new Move(0,-1);
        moves[pos++] = new Move(1,0);
        moves[pos++] = new Move(-1,0);
        // diagonal moves
        moves[pos++] = new Move(1,1);
        moves[pos++] = new Move(-1,-1);
        moves[pos++] = new Move(1,-1);
        moves[pos++] = new Move(-1,1);


        return moves;
    }
}
