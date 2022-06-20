package Main;

import Controller.Controller;
import View.View;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main implements Notifiable {

    private Controller controller;
    private View view;
    public static final String ALL_FLAGS_DIRECTORY = "src/AllFlags/";
    public static final String EXAMPLE_FLAGS = "src/ExampleFlags/";
    public static int SAMPLES_NUMBER = 50000;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Main().start();
    }

    private void start() {
        controller = new Controller(this);
        view = new View(this);
        view.showGui();
    }

    @Override
    public void notify(String message, Object... arguments) {
        switch (message) {
            case "start" -> {
                controller.searchFlagAsync((BufferedImage) arguments[0]);
                view.enableButtons(false, false, false, true);
            }
            case "finished" -> {
                view.showNewFlag((String) arguments[0]);
                view.enableButtons(false, true, true, false);
                view.repaint();
            }
            case "load" -> {
                controller.loadDistributionsAsync();
                view.enableButtons(false, false, false, false);
            }
            case "stop" -> {
                controller.stopSearch();
            }
            case "stopped" -> view.enableButtons(false, true, true, false);
            case "loaded" -> view.enableButtons(false, true, false, false);
            case "startProgress" -> view.clearAndPrepareProgress((int) arguments[0]);
            case "step" -> view.updateProgress();
            case "samples" -> SAMPLES_NUMBER = (int) arguments[0];
        }
    }
}
