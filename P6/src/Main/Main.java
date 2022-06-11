package Main;

import Controller.Controller;
import Model.Board;
import View.MainPanel;

import javax.swing.*;

public class Main implements Notifiable {

    private Controller controller;
    private MainPanel view;
    private Board board;

    public static final int NUMBERS = 9;
    public static final int DIMENSION = (int) Math.sqrt(NUMBERS);

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        board = new Board();
        controller = new Controller(this, board);
        view = new MainPanel(this, board);
        view.showGui();
    }

    @Override
    public void notify(String s, Object o) {
        switch (s) {
            case "shuffle" -> controller.shuffle();
            case "shuffleFinished", "repaint" -> view.repaint();
            case "start" -> {
                controller.prepare();
                view.activateButtons(false, false);
                // TIMEOUT
                new Timer(10000, (e) -> {
                    System.out.println("Timeout!!!");
                    System.exit(0);
                }).start();
            }
            case "finished" -> view.activateButtons(true, true);
        }
    }


}