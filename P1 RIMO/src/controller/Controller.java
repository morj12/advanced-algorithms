package controller;

import main.Main;
import main.Notifiable;

public class Controller implements Notifiable {

    private static Controller instance;

    private final Main main;
    private Algorithm[] algorithms;

    private Controller(Main main) {
        this.main = main;
        this.algorithms = new Algorithm[Complexity.values().length];
    }

    public static Controller getInstance(Main main) {
        if (instance == null) {
            instance = new Controller(main);
        }
        return instance;
    }

    public void tryExecuteAlgorithm(Complexity complexity) {
        if (algorithms[complexity.ordinal()] == null) {
            algorithms[complexity.ordinal()] = new Algorithm(main, Complexity.values()[complexity.ordinal()]);
            algorithms[complexity.ordinal()].start();
        } else {
            main.getModel().notify(complexity.name());
            algorithms[complexity.ordinal()].stopAlgorithm();
            algorithms[complexity.ordinal()] = null;
        }
    }

    @Override
    public void notify(String s) {
    }

}
