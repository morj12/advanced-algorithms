package main;

import model.Board;
import model.pieces.Drunk;
import model.pieces.King;
import model.pieces.Messi;
import view.View;

import javax.swing.*;

public class Main implements Notifiable {

    private int dimension = 8;

    private View view;

    public static void main(String[] args) {
        new Main().start();
    }



    private void start() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        view = new View(dimension);
        view.showGui();

        Board board = new Board(dimension);
        System.out.println(board);

        board.initPiece(new Drunk(), 3, 1);
        board.initPiece(new Messi(), 2, 5);
        board.initPiece(new King(), 1, 0);

        System.out.println(board);

    }

    @Override
    public void notify(String s, Object o) {

    }
}
