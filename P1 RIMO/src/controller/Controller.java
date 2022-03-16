package controller;

import main.Main;
import main.Notifiable;

public class Controller implements Notifiable {

    private final Main main;
    private Algorithm[] algorithms;


    public Controller(Main main) {
        this.main = main;
        this.algorithms = new Algorithm[Complexity.values().length];
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
