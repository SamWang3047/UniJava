package COMP90041.Project_DisasterReliefRobots;

import java.util.ArrayList;

public class Scenario {

    private String disaster;
    private ArrayList<Location> locations;


    public Scenario(String disaster) {
        this.disaster = disaster;
        locations = new ArrayList<>();
    }

    public Location getLocation(int choice) {
        return locations.get(choice);
    }
    public String getDisaster() {
        return disaster;
    }

    public void setDisaster(String disaster) {
        this.disaster = disaster;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }
}
