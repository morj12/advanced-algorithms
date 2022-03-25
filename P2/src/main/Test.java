package main;

import controller.Controller;
import model.Board;
import model.pieces.*;
import view.View;

public class Test implements Notifiable {

    private int dimension = 8;
    private View view;
    private Board board;
    private Controller controller;
    private boolean isExecutedAlgorithm;

    public static void main(String[] args) {
        new Test().start();
    }

    private void start() {
        isExecutedAlgorithm = false;
        board = new Board(dimension);
        view = new View(this, dimension);
        controller = new Controller(this);
        view.showGui();

//        controller.start(dimension, new King(), 5, 5);
//        controller.start(dimension, new Queen(dimension), 5, 5);
//        controller.start(dimension, new Knight(), 5, 5);
//        controller.start(dimension, new Megaknight(), 5, 5);
//        controller.start(dimension, new Pony(), 5, 5);
//        controller.start(dimension, new Rook(dimension), 5, 5);

        // not checked
//        controller.start(dimension, new Messi(), 0, 0);
//        controller.start(dimension, new Drunk(), 0, 0);


    }


    @Override
    public void notify(String s, Object o) {
        if (s.startsWith("draw:")) {
            var split = s.substring(5).split(",");
            int stepNumber = Integer.parseInt(split[0]);
            int x = Integer.parseInt(split[1]);
            int y = Integer.parseInt(split[2]);
            view.setPiece(stepNumber, x, y);
            view.repaint();
        } else if (s.startsWith("remove:")) {
            var split = s.substring(7).split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            view.removePiece(x, y);
            view.repaint();
        } else if (s.equals("finished")) {
            isExecutedAlgorithm = false;
        } else if (s.equals("started")) {
            isExecutedAlgorithm = true;
        }
    }
}
