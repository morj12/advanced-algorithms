package Model;

import java.util.List;

public class Country {
    private List<Town> towns;
    private List<Road> roads;

    public Country(List<Town> towns, List<Road> roads) {
        this.towns = towns;
        this.roads = roads;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public boolean isSelected(int i) {
        return towns.get(i).isSelected();
    }

    public void setSelected(int i, boolean select) {
        towns.get(i).setSelected(select);
    }

    public int getSelectedNumber() {
        return (int) getTowns().stream().filter(Town::isSelected).count();
    }

    public Town getTown(int i) {
        return towns.get(i);
    }

}