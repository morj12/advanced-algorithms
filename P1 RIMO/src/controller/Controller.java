package controller;

import static model.Model.STOPS;
import model.TimePoint;
import main.Main;
import main.ErrorWriter;
import main.Notifiable;

public class Controller extends Thread implements Notifiable {

    private final Main main;
    private final int algo_index;
    private boolean execute;


    public Controller(Main main, int algo_index) {
        this.main = main;
        this.algo_index = algo_index;
        this.execute = true;
    }

    public void run() {
        TimePoint p = null;

        for (int i = 0; execute && i < STOPS.length; i++) {

            if (algo_index == 0) {
                p = linear(i);
            } else if (algo_index == 1) {
                p = quadratic(i);
            } else if (algo_index == 2) {
                p = logarithmic(i);
            }
            if (execute) {
                main.getModel().addPoint(p);
            }
        }
    }

    private TimePoint linear(int iter) {

        long time = System.currentTimeMillis();
        for (int i = 0; execute && i < STOPS[iter]; i++) {
            step(5, 0);
        }
        time = System.currentTimeMillis() - time;

        TimePoint p = new TimePoint(algo_index, time, STOPS[iter]);
        return p;
    }

    private TimePoint quadratic(int iter) {

        long time = System.currentTimeMillis();
        for (int i = 0; execute && i < STOPS[iter]; i++) {
            for (int j = 0; execute && j < STOPS[iter]; j++) {
                step(5, 0);
            }
        }
        time = System.currentTimeMillis() - time;

        TimePoint p = new TimePoint(algo_index, time, STOPS[iter]);
        return p;
    }

    private TimePoint logarithmic(int iter) {

        long time = System.currentTimeMillis();
        for (int i = 1; execute && i <= STOPS[iter]; i *= 2) {
            step(5, 0);
        }
        time = System.currentTimeMillis() - time;

        TimePoint p = new TimePoint(algo_index, time, STOPS[iter]);
        return p;
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
        if (s.startsWith("START")) {
            this.start();
        } else if (s.startsWith("STOP")) {
            execute = false;
        }
    }
}
