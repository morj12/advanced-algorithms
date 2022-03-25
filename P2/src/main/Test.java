package main;

import controller.Controller;
import model.AbstractPiece;
import model.Board;
import model.pieces.*;
import view.View;

public class Test implements Notifiable {

    private int dimension = 8;
    View view;
    Board board;
    Controller controller;

    public static void main(String[] args) {
        new Test().start();
    }


    /**
     * for tomorrow:
     * verify queen, rook, messi, drunk
     * paint
     */
    private void start() {
        board = new Board(dimension);
        view = new View(dimension);
        controller = new Controller(this);

        /*
        king ok
        knight ok
        megaknight ok
        pony ok
         */


//        controller.start(dimension, new AbstractPiece[]{new Knight()}, new int[]{0}, new int[]{0}); // not ok for 8
//        controller.start(dimension, new AbstractPiece[]{new Megaknight()}, new int[]{0}, new int[]{0}); // ok for 8
//        controller.start(dimension, new AbstractPiece[]{new Pony()}, new int[]{0}, new int[]{0}); // not ok for 8
//        controller.start(dimension, new AbstractPiece[]{new King()}, new int[]{0}, new int[]{0}); // ok for any dim
//        controller.start(dimension, new AbstractPiece[]{new Rook(dimension)}, new int[]{3}, new int[]{3}); // ok for any dim
        controller.start(dimension, new Queen(dimension), 5, 5);

        // not checked

//        controller.start(dimension, new AbstractPiece[]{new Messi()}, new int[]{0}, new int[]{0});
//        controller.start(dimension, new AbstractPiece[]{new Drunk()}, new int[]{0}, new int[]{0});


    }


    @Override
    public void notify(String s, Object o) {
    }
}
