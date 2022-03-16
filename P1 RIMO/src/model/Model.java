package model;

import java.util.ArrayList;
import controller.Complexity;
import main.Main;
import main.Notifiable;

public class Model implements Notifiable {

    private static Model instance;

    private Main main;
    private ArrayList<TimePoint>[] pointLists;

    private Model(Main main) {
        this.main = main;
        pointLists = (ArrayList<TimePoint>[]) new ArrayList[Complexity.values().length];
        for (int i = 0; i < pointLists.length; i++) {
            pointLists[i] = new ArrayList<>();
        }
    }

    public static Model getInstance(Main main) {
        if (instance == null) {
            instance = new Model(main);
        }
        return instance;
    }

    public void addPoint(TimePoint timePoint, int algoIndex) {
        pointLists[algoIndex].add(timePoint);
    }

    public ArrayList<TimePoint>[] getPointLists() {
        return pointLists;
    }

    @Override
    public void notify(String s) {
        Complexity complexity = Complexity.valueOf(s);
        switch (complexity) {
            case LINEAR -> pointLists[0].clear();
            case QUADRATIC -> pointLists[1].clear();
            case LOGARITHMIC -> pointLists[2].clear();
        }
    }
}
