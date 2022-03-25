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
        view = new View(this, dimension);
        view.showGui();


    }

    @Override
    public void notify(String s, Object o) {

    }
}
