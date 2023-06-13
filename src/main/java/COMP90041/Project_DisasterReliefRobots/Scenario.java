package COMP90041.Project_DisasterReliefRobots;

import java.util.ArrayList;

public class Scenario {

    private String disaster;
    private ArrayList<Location> locations;
    private int rescuedLocation;


    public Scenario(String disaster) {
        this.disaster = disaster;
        locations = new ArrayList<>();
        rescuedLocation = -1;
    }

    public Scenario(String disaster, ArrayList<Location> locations) {
        this.disaster = disaster;
        this.locations = locations;
        rescuedLocation = -1;
    }

    public void presentScenario() {
        System.out.println("======================================");
        System.out.println("# Scenario: " + disaster);
        System.out.println("======================================");
        for (int i = 0; i < locations.size(); i++) {
            System.out.print("[" + (i + 1) + "] " + locations.get(i).getLocationInfo());
        }
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

    public int getRescuedLocation() {
        return rescuedLocation;
    }

    public void setRescuedLocation(int rescuedLocation) {
        this.rescuedLocation = rescuedLocation;
    }
}
