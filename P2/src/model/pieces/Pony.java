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
public class Pony extends Piece{
    
    public Pony() {
        name = this.getClass().getName();
        image = "image/pony.png";
        movements = SetMovements();
    }

    private Movement[] SetMovements() {
        movements = new Movement[8];
        int pos = 0;
        
        movements[pos] = new Movement(1,3);
        movements[pos++] = new Movement(3,1);
        movements[pos++] = new Movement(1,-3);
        movements[pos++] = new Movement(3,-1);
        movements[pos++] = new Movement(-1,3);
        movements[pos++] = new Movement(-3,1);
        movements[pos++] = new Movement(-1,-3);
        movements[pos++] = new Movement(-3,-1);
        
        return movements;
    }
}
