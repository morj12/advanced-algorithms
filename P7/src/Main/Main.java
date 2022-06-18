package Main;

import Controller.Controller;
import View.View;

import javax.swing.*;

public class Main implements Notifiable {

    private Controller controller;
    private View view;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println(e);
        }
        new Main().start();
    }

    private void start() {
        view = new View(this);
        view.showGui();
    }

    @Override
    public void notify(String message, Object[] object) {

    }
}
