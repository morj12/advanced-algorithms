/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import model.Move;
import model.AbstractPiece;

/**
 *
 * @author ikerg
 */
public class Rook extends AbstractPiece {
    
    public Rook() {
        outOfBoundsDimension = true;
        name = this.getClass().getName();
        image = "image/rook.png";
        moves = new Move[0];
    }
    
    public Rook(int dimension) {
        outOfBoundsDimension = true;
        name = this.getClass().getName();
        image = "image/rook.png";
        moves = SetMoves(dimension);
    }

    private Move[] SetMoves(int limit) {
        moves = new Move[(limit - 1) * 4];
        int pos = 0;
        
        for (int i = -(limit - 1); i < limit; i++) {
            if (i != 0) { 
                moves[pos] = new Move(0, i);
                moves[pos++] = new Move(i, 0);
            }
        }
        
        return moves;
    }
}
