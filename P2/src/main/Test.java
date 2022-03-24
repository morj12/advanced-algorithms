package main;

import controller.Controller;
import model.AbstractPiece;
import model.Board;
import model.pieces.Drunk;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Messi;
import view.View;

public class Test implements Notifiable{

    private int dimension = 8;

    public static void main(String[] args) {
        new Test().start();
    }



    private void start() {
        var controller = new Controller(this);
        controller.start(dimension, new AbstractPiece[]{new Knight()}, new int[]{0}, new int[]{0});


    }

    private void setMoves() {
        King piece = new King();

    }

    @Override
    public void notify(String s, Object o) {}
}
