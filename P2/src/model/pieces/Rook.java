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
        name = this.getClass().getName();
        image = "image/rook.png";
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
