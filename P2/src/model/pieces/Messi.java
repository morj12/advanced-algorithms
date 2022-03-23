/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import model.Movement;
import model.AbstractPiece;

/**
 *
 * @author ikerg
 */
public class Messi extends AbstractPiece {
    
    public Messi() {
        name = this.getClass().getName();
        image = "image/messi.png";
        movements = SetMovements();
    }

    private Movement[] SetMovements() {
        movements = new Movement[8];
        int pos = 0;
        
        movements[pos] = new Movement(1,2);     //one right two up
        movements[pos++] = new Movement(2,1);   //two right one up
        movements[pos++] = new Movement(1,-2);  //one right two down
        movements[pos++] = new Movement(2,-1);  //two right one down
        movements[pos++] = new Movement(-1,2);  //one left two up
        movements[pos++] = new Movement(-2,1);  //two left one up
        movements[pos++] = new Movement(-1,-2); //one left two down
        movements[pos++] = new Movement(-2,-1); //two left one down 
        
        return movements;
    }
}
