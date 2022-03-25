package main;

import controller.Controller;
import model.AbstractPiece;
import model.AbstractPieceCreator;
import view.View;

import javax.swing.*;

public class Main implements Notifiable {

    private final int initDimension = 8;
    private View view;
    private Controller controller;
    private boolean isExecutedAlgorithm;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Main().start();
    }

    private void start() {
        isExecutedAlgorithm = false;
        view = new View(this, initDimension);
        controller = new Controller(this);
        view.showGui();
    }


    @Override
    public void notify(String s, Object o) {
        if (s.startsWith("draw:")) {
            /** Draw a piece **/
            String[] split = s.substring(5).split(",");
            int stepNumber = Integer.parseInt(split[0]);
            int x = Integer.parseInt(split[1]);
            int y = Integer.parseInt(split[2]);
            view.setPiece(stepNumber, x, y);
            view.repaint();
        } else if (s.startsWith("remove:")) {
            /** Remove a piece **/
            var split = s.substring(7).split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            view.removePiece(x, y);
            view.repaint();
        } else if (s.equals("finished") || s.equals("finishedNoSolution")) {
            /** Unlock settings **/
            view.unlockSettings();
            isExecutedAlgorithm = false;
        } else if (s.startsWith("start:")) {
            /** If it's not executed, execute, reset view and lock settings **/
            if (!isExecutedAlgorithm) {
                view.reset();
                isExecutedAlgorithm = true;
                view.lockSettings();
                String[] split = s.substring(6).split(",");
                int dimension = Integer.parseInt(split[0]);
                AbstractPiece piece = AbstractPieceCreator.create(split[1]);
                controller.start(dimension, piece, 0, 0);
            }
        } else if (s.equals("stop")) {
            /** Stop and unlock settings **/
            isExecutedAlgorithm = false;
            controller.stopAlgorithm();
            view.unlockSettings();
            view.reset();
        } else if (s.startsWith("dimension:")) {
            /** Change dimension **/
            view.setDimension(Integer.parseInt(s.substring(10)));
        }
    }
}
