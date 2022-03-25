package model.pieces;

import model.Move;
import model.AbstractPiece;

public class Messi extends AbstractPiece {

    private Move[] alternatedMoves;

    public Messi() {
        name = this.getClass().getSimpleName();
        image = "image/messi.png";
        moves = SetMoves();
        hasState = true;
    }

    private Move[] SetMoves() {
        moves = new Move[8];
        int pos = 0;

        // TODO: add more moves
        moves[pos++] = new Move(1,2);     //one right two up
        moves[pos++] = new Move(2,1);   //two right one up
        moves[pos++] = new Move(1,-2);  //one right two down
        moves[pos++] = new Move(2,-1);  //two right one down
        moves[pos++] = new Move(-1,2);  //one left two up
        moves[pos++] = new Move(-2,1);  //two left one up
        moves[pos++] = new Move(-1,-2); //one left two down
        moves[pos] = new Move(-2,-1); //two left one down
        // alternated moves
        
        return moves;
    }
}
