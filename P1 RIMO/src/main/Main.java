package main;

import controller.Complexity;
import controller.Controller;
import model.Model;
import view.View;

import javax.swing.*;

public class Main implements Notifiable {

    private Model model;
    private View view;
    private Controller singleController;

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        model = Model.getInstance(this);
        singleController = Controller.getInstance(this);
        view = View.getInstance(this);
        view.showGUI();
    }

    @Override
    public void notify(String s) {
        Complexity type = Complexity.valueOf(s);
        singleController.tryExecuteAlgorithm(type);
    }

    public Model getModel() {
        return model;
    }
}
