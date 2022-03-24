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
public class Pony extends AbstractPiece {
    
    public Pony() {
        name = this.getClass().getName();
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
