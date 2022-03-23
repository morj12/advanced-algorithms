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
public class Rook extends AbstractPiece {
    
    public Rook() {
        outOfBoundsDimension = true;
        name = this.getClass().getName();
        image = "image/rook.png";
        movements = new Movement[0];
    }
    
    public Rook(int dimension) {
        outOfBoundsDimension = true;
        name = this.getClass().getName();
        image = "image/rook.png";
        movements = SetMovements(dimension);
    }

    private Movement[] SetMovements(int limit) {
        movements = new Movement[(limit - 1) * 4];
        int pos = 0;
        
        for (int i = -(limit - 1); i < limit; i++) {
            if (i != 0) { 
                movements[pos] = new Movement(0, i);
                movements[pos++] = new Movement(i, 0);
            }
        }
        
        return movements;
    }
}
