package model;

import java.util.ArrayList;

import control.Complexity;
import principal.Main;
import principal.Notifiable;

public class Model implements Notifiable {

    private Main main;
    private ArrayList<TimePoint>[] pointLists;

    public static final int[] STOPS = {1, 5, 10, 15, 20, 25, 30, 35};

    public Model(Main main) {
        this.main = main;
        pointLists = (ArrayList<TimePoint>[]) new ArrayList[3];
        for (int i = 0; i < pointLists.length; i++) {
            pointLists[i] = new ArrayList();
        }
    }

    public void addPoint(TimePoint timePoint) {
        int id = timePoint.getAlgoIndex();
        pointLists[id].add(timePoint);
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
