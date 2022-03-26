package model.pieces;

import model.Position;
import model.AbstractPiece;

public class King extends AbstractPiece {
    
    public King() {
        name = this.getClass().getSimpleName();
        moves = SetMoves();
    }

    private Position[] SetMoves() {
        moves = new Position[8];
        int pos = 0;

        moves[pos++] = new Position(0,1);
        moves[pos++] = new Position(0,-1);
        moves[pos++] = new Position(1,0);
        moves[pos++] = new Position(-1,0);
        moves[pos++] = new Position(1,-1);
        moves[pos++] = new Position(1,1);
        moves[pos++] = new Position(-1,-1);
        moves[pos] = new Position(-1,1);
        
        return moves;
    }
}
