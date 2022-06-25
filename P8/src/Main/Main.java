package Main;

import Controller.Controller;
import Model.Country;
import Model.Road;
import Model.Town;
import Utils.Constants;
import View.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main implements Notifiable {

    Controller controller;
    View view;
    Country country;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Main().start();
    }

    private void start() {
        List<Town> towns = addTowns();

        country = new Country(towns, addRoads(towns));
        controller = new Controller(this, country);
        view = new View(this, country);

        view.showGui();
    }

    @Override
    public void notify(String message, Object... arguments) {
        switch (message) {
            case "findPath" -> controller.executeAsync((Town) arguments[0], (Town) arguments[1]);
            case "pathFound" -> view.setPath((List<Town>) arguments[0]);
        }
    }

    private List<Town> addTowns() {
        List<Town> towns = new ArrayList<>();
        Arrays.stream(Constants.cityNames).forEach((city) -> towns.add(new Town(city)));
        return towns;
    }

    private List<Road> addRoads(List<Town> towns) {
        List<Road> roads = new ArrayList<>();
        Arrays.stream(Constants.roads).forEach(r -> addRoad(towns, roads, r));
        return roads;
    }

    private void addRoad(List<Town> towns,
                         List<Road> roads,
                         int[] road) {
        roads.add(new Road(towns.get(road[0]), towns.get(road[1]), road[2]));
        roads.add(new Road(towns.get(road[1]), towns.get(road[0]), road[2]));
    }
}
