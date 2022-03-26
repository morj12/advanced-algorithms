package model.pieces;

import model.Position;
import model.AbstractPiece;

public class Pony extends AbstractPiece {
    
    public Pony() {
        name = this.getClass().getSimpleName();
        moves = SetMoves();
    }

    private Position[] SetMoves() {
        moves = new Position[8];
        int pos = 0;
        
        moves[pos++] = new Position(1,3);
        moves[pos++] = new Position(3,1);
        moves[pos++] = new Position(1,-3);
        moves[pos++] = new Position(3,-1);
        moves[pos++] = new Position(-1,3);
        moves[pos++] = new Position(-3,1);
        moves[pos++] = new Position(-1,-3);
        moves[pos] = new Position(-3,-1);
        
        return moves;
    }
}
