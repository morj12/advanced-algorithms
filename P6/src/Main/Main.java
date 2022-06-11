package Main;

import Controller.Controller;
import Model.Matrix;
import View.MainPanel;

import javax.swing.*;

public class Main implements Notifiable {

    private Controller controller;
    private MainPanel view;
    private Matrix matrix;

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

        matrix = new Matrix();
        controller = new Controller(this, matrix);
        view = new MainPanel(this, matrix);
        view.showGui();
    }

    @Override
    public void notify(String s, Object o) {
        switch (s) {
            case "shuffle" -> controller.shuffle();
            case "shuffleFinished", "repaint" -> view.repaint();
            case "start" -> {
                controller.prepare();
                view.activateButtons(false, false, true);
            }
            case "finished" -> view.activateButtons(true, true, false);
            case "cancelled" -> controller.interrupt();
        }
    }


}