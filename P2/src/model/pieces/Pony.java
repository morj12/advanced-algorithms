package model.pieces;

import model.Move;
import model.AbstractPiece;

public class Pony extends AbstractPiece {
    
    public Pony() {
        name = this.getClass().getSimpleName();
        image = "image/pony.png";
        moves = SetMoves();
    }

    private Move[] SetMoves() {
        moves = new Move[8];
        int pos = 0;
        
        moves[pos++] = new Move(1,3);
        moves[pos++] = new Move(3,1);
        moves[pos++] = new Move(1,-3);
        moves[pos++] = new Move(3,-1);
        moves[pos++] = new Move(-1,3);
        moves[pos++] = new Move(-3,1);
        moves[pos++] = new Move(-1,-3);
        moves[pos] = new Move(-3,-1);
        
        return moves;
    }
}
