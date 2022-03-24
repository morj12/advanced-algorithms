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
public class Knight extends AbstractPiece {
    
    public Knight() {
        name = this.getClass().getName();
        image = "image/knight.png";
        moves = SetMoves();
    }

    private Move[] SetMoves() {
        moves = new Move[8];
        int pos = 0;

        moves[pos++] = new Move(1,2);     //one right two up
        moves[pos++] = new Move(2,1);   //two right one up
        moves[pos++] = new Move(1,-2);  //one right two down
        moves[pos++] = new Move(2,-1);  //two right one down
        moves[pos++] = new Move(-1,2);  //one left two up
        moves[pos++] = new Move(-2,1);  //two left one up
        moves[pos++] = new Move(-1,-2); //one left two down
        moves[pos] = new Move(-2,-1); //two left one down

        /*
        moves[pos++] = new Move(-2,1);     //one right two up
        moves[pos++] = new Move(-1,2);   //two right one up
        moves[pos++] = new Move(1,2);  //one right two down
        moves[pos++] = new Move(2,1);  //two right one down
        moves[pos++] = new Move(2,-1);  //one left two up
        moves[pos++] = new Move(1,-2);  //two left one up
        moves[pos++] = new Move(-1,-2); //one left two down
        moves[pos] = new Move(-2,-1); //two left one down
         */
        
        return moves;
    }

}
