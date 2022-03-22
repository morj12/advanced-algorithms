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
public class Megaknight extends Piece {

    public Megaknight() {
        name = this.getClass().getName();
        image = "image/megaknight.png";
        movements = SetMovements();
    }

    private Movement[] SetMovements() {
        movements = new Movement[8];
        int pos = 0;
        
        movements[pos] = new Movement(0,2); 
        movements[pos++] = new Movement(2,2);
        movements[pos++] = new Movement(2,0);
        movements[pos++] = new Movement(2,-2);
        movements[pos++] = new Movement(0,-2);
        movements[pos++] = new Movement(-2,-2);
        movements[pos++] = new Movement(-2,0);
        movements[pos++] = new Movement(-2,2);
        
        return movements;
    }
}
