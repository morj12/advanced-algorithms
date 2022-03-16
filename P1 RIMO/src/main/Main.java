package main;

import controller.Complexity;
import controller.Controller;
import model.Model;
import model.TimePoint;
import view.View;

import javax.swing.*;

public class Main implements Notifiable {

    private Model model;
    private View view;
    private Controller controller;

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
        controller = Controller.getInstance(this);
        view = View.getInstance(this);
        view.setPointListsAndStart(model.getPointLists());
        view.showGUI();
    }

    @Override
    public void notify(String s) {
        if (s.startsWith("addpoint:")) {
            // Add new point to the model
            String pointInfo = s.substring(9);
            String[] parts = pointInfo.split(",");
            TimePoint point = new TimePoint(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]));
            model.addPoint(point, Integer.parseInt(parts[2]));
        } else if (s.startsWith("restart:")) {
            // Restart the model
            Complexity type = Complexity.valueOf(s.substring(8));
            model.notify(type.name());
        } else {
            // Start or pause an algorithm
            Complexity type = Complexity.valueOf(s);
            controller.tryExecuteAlgorithm(type);
        }
    }

}
