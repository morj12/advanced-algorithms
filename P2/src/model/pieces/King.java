package model.pieces;

import model.Move;
import model.AbstractPiece;

public class King extends AbstractPiece {
    
    public King() {
        name = this.getClass().getSimpleName();
        moves = SetMoves();
    }

    private Move[] SetMoves() {
        moves = new Move[8];
        int pos = 0;

        moves[pos++] = new Move(0,1);
        moves[pos++] = new Move(0,-1);
        moves[pos++] = new Move(1,0);
        moves[pos++] = new Move(-1,0);
        moves[pos++] = new Move(1,-1);
        moves[pos++] = new Move(1,1);
        moves[pos++] = new Move(-1,-1);
        moves[pos] = new Move(-1,1);
        
        return moves;
    }
}
