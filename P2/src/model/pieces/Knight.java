package model.pieces;

import model.Position;
import model.AbstractPiece;


public class Knight extends AbstractPiece {
    
    public Knight() {
        name = this.getClass().getSimpleName();
        moves = SetMoves();
    }

    private Position[] SetMoves() {
        moves = new Position[8];
        int pos = 0;

        moves[pos++] = new Position(1,2);     //one right two up
        moves[pos++] = new Position(2,1);   //two right one up
        moves[pos++] = new Position(1,-2);  //one right two down
        moves[pos++] = new Position(2,-1);  //two right one down
        moves[pos++] = new Position(-1,2);  //one left two up
        moves[pos++] = new Position(-2,1);  //two left one up
        moves[pos++] = new Position(-1,-2); //one left two down
        moves[pos] = new Position(-2,-1); //two left one down
        
        return moves;
    }

}
