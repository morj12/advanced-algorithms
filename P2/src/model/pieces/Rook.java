package model.pieces;

import model.Move;
import model.AbstractPiece;


public class Rook extends AbstractPiece {


    public Rook() {
        name = this.getClass().getName();
        moves = SetMoves();
    }

    private Move[] SetMoves() {
        moves = new Move[(MAX_DIMENSION - 1) * 4];
        int pos = 0;
        
        for (int i = -(MAX_DIMENSION - 1); i < MAX_DIMENSION; i++) {
            if (i != 0) { 
                moves[pos++] = new Move(0, i);
                moves[pos++] = new Move(i, 0);
            }
        }
        
        return moves;
    }
}
