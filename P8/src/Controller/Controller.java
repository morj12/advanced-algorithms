package Controller;

import Main.Notifiable;
import Model.Country;
import Model.Road;
import Model.Town;

import java.util.*;

public class Controller {

    private final Notifiable main;
    private boolean isExecuted;
    private final Country country;

    private Set<Town> processedTowns;
    private Set<Town> unprocessedTowns;
    private Map<Town, Town> predecessors;
    private Map<Town, Integer> distances;

    public Controller(Notifiable main, Country country) {
        this.main = main;
        this.country = country;
    }

    public void executeAsync(Town source, Town dest) {
        if (!isExecuted) {
            new Thread(() -> execute(source, dest)).start();
        }
    }

    public void execute(Town source, Town dest) {
        isExecuted = true;
        processedTowns = new HashSet<>();
        unprocessedTowns = new HashSet<>();
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        distances.put(source, 0);
        unprocessedTowns.add(source);
        while (unprocessedTowns.size() > 0) {
            Town town = getClosestTown(unprocessedTowns);
            processedTowns.add(town);
            unprocessedTowns.remove(town);
            findMinimalDistances(town);
        }
        isExecuted = false;
        main.notify("pathFound", getPath(dest));
    }

    private Town getClosestTown(Set<Town> towns) {
        Town shortest = null;
        for (Town town : towns) {
            if (shortest == null) {
                shortest = town;
            } else {
                if (getShortestDistance(town) < getShortestDistance(shortest)) {
                    shortest = town;
                }
            }
        }
        return shortest;
    }

    private void findMinimalDistances(Town town) {
        List<Town> neighbors = getNeighbors(town);
        neighbors
                .stream()
                .filter(neighbor -> getShortestDistance(neighbor) > getShortestDistance(town) + getDistance(town, neighbor))
                .forEach(neighbor -> {
                    distances.put(neighbor, getShortestDistance(town) + getDistance(town, neighbor));
                    predecessors.put(neighbor, town);
                    unprocessedTowns.add(neighbor);
                });
    }

    private List<Town> getNeighbors(Town town) {
        return country.getRoads()
                .stream()
                .filter(road -> road.source().equals(town) && !isSettled(road.dest()))
                .map(Road::dest) // get the neighbor from dest
                .toList();
    }

    private int getShortestDistance(Town destination) {
        return Objects.requireNonNullElse(distances.get(destination), Integer.MAX_VALUE);
    }

    private int getDistance(Town town, Town neighbor) {
        for (Road road : country.getRoads()) {
            if (road.source().equals(town) && road.dest().equals(neighbor)) return road.distance();
        }
        return Integer.MAX_VALUE;
    }

    private boolean isSettled(Town town) {
        return processedTowns.contains(town);
    }

    public List<Town> getPath(Town neighbor) {
        List<Town> path = new ArrayList<>();
        Town step = neighbor;
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }

}
