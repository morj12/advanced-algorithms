package controller;

import main.ErrorWriter;
import main.Main;
import model.TimePoint;

public class Algorithm extends Thread{

    private static final int MAX_ITERATIONS = 35;

    private Main main;
    private Complexity algorithm;
    private boolean isExecuted;

    public Algorithm(Main main, Complexity algorithm) {
        this.main = main;
        this.algorithm = algorithm;
        this.isExecuted = true;
    }

    public void run() {
        TimePoint point;
        for (int i = 0; isExecuted && i < MAX_ITERATIONS; i++) {
            point = switch (algorithm) {
                case LINEAR -> linearStep(i);
                case QUADRATIC -> quadraticStep(i);
                case LOGARITHMIC -> logarithmicStep(i);
                default -> null;
            };
            if (isExecuted) {
                main.getModel().addPoint(point, algorithm.ordinal());
            }
        }
    }

    private TimePoint linearStep(int iterations) {
        long time = System.currentTimeMillis();
        for (int i = 0; isExecuted && i <= iterations; i++) step(5, 0);
        time = System.currentTimeMillis() - time;
        return new TimePoint(time, iterations);
    }

    private TimePoint quadraticStep(int iterations) {
        long time = System.currentTimeMillis();
        for (int i = 0; isExecuted && i <= iterations; i++) {
            for (int j = 0; isExecuted && j < iterations; j++) {
                step(5, 0);
            }
        }
        time = System.currentTimeMillis() - time;
        return new TimePoint(time, iterations);
    }

    private TimePoint logarithmicStep(int iterations) {
        long time = System.currentTimeMillis();
        for (int i = 1; isExecuted && i <= iterations; i *= 2) step(5, 0);
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

    public void stopAlgorithm() {
        this.isExecuted = false;
    }

}
