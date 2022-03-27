package main;

import controller.Controller;
import model.AbstractPiece;
import model.AbstractPieceCreator;
import model.Position;
import view.View;

import javax.swing.*;

public class Main implements Notifiable {

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
        view = View.getInstance(this);
        controller = Controller.getInstance(this);
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
            view.setPiece(stepNumber, new Position(x, y));
            view.repaint();
        } else if (s.startsWith("remove:")) {
            /** Remove a piece **/
            String[] split = s.substring(7).split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            view.removePiece(new Position(x, y));
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
                view.loadPiece(split[1]);
                int x = Integer.parseInt(split[2]);
                int y = Integer.parseInt(split[3]);
                controller.start(dimension, piece, new Position(y, x));
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
        } else if(s.equals("speed:")) {
            controller.setSpeed((int) o);
        }
    }
}
