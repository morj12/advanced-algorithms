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
public class King extends AbstractPiece {
    
    public King() {
        name = this.getClass().getSimpleName();
        image = "image/king.png";
        moves = SetMoves();
    }

    private Move[] SetMoves() {
        moves = new Move[8];
        int pos = 0;

        moves[pos++] = new Move(0,1);
        moves[pos++] = new Move(0,-1);
        moves[pos++] = new Move(1,0);
        moves[pos++] = new Move(1,-1);
        moves[pos++] = new Move(1,1);
        moves[pos++] = new Move(-1,-1);
        moves[pos++] = new Move(-1,0);
        moves[pos] = new Move(-1,1);
        
        return moves;
    }
}
