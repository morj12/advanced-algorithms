package controller;

import main.ErrorWriter;
import main.Main;
import main.Notifiable;
import model.TimePoint;

public class Controller extends Thread implements Notifiable {

    private final Main main;
    private final int algoIndex;
    private boolean execute;


    public Controller(Main main, int algo_index) {
        this.main = main;
        this.algoIndex = algo_index;
        this.execute = true;
    }

    public void run() {
        TimePoint p;

        for (int i = 0; execute && i < 35; i++) {
            p = switch (algoIndex) {
                case 0 -> linearStep(i);
                case 1 -> quadraticStep(i);
                case 2 -> logarithmicStep(i);
                default -> null;
            };
            if (execute) {
                main.getModel().addPoint(p, algoIndex);
            }
        }
    }

    private TimePoint linearStep(int iterations) {
        long time = System.currentTimeMillis();
        for (int i = 0; execute && i <= iterations; i++) step(5, 0);
        time = System.currentTimeMillis() - time;
        return new TimePoint(time, iterations);
    }

    private TimePoint quadraticStep(int iterations) {
        long time = System.currentTimeMillis();
        for (int i = 0; execute && i <= iterations; i++) {
            for (int j = 0; execute && j < iterations; j++) {
                step(5, 0);
            }
        }
        time = System.currentTimeMillis() - time;
        return new TimePoint(time, iterations);
    }

    private TimePoint logarithmicStep(int iterations) {
        long time = System.currentTimeMillis();
        for (int i = 1; execute && i <= iterations; i *= 2) step(5, 0);
        time = System.currentTimeMillis() - time;
        return new TimePoint(time, iterations);
    }

    private void step(long m, int n) {
        try {
            Thread.sleep(m, n);
        } catch (Exception e) {
            ErrorWriter.writeError(e);
        }
    }

    @Override
    public void notify(String s) {
        switch (s) {
            case "START" -> this.start();
            case "STOP" -> execute = false;
        }
    }
}
