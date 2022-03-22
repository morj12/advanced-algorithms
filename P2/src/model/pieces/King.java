/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import model.Movement;
import model.Piece;

/**
 *
 * @author ikerg
 */
public class King extends Piece{
    
    public King() {
        name = this.getClass().getName();
        image = "image/king.png";
        movements = SetMovements();
    }

    private Movement[] SetMovements() {
        movements = new Movement[8];
        int pos = 0;
        
        movements[pos] = new Movement(0,1); 
        movements[pos++] = new Movement(1,1);
        movements[pos++] = new Movement(1,0);
        movements[pos++] = new Movement(1,-1);
        movements[pos++] = new Movement(0,-1);
        movements[pos++] = new Movement(-1,-1);
        movements[pos++] = new Movement(-1,0);
        movements[pos++] = new Movement(-1,1);
        
        return movements;
    }
}
