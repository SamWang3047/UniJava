package COMP90041.Project_DisasterReliefRobots;

import java.util.ArrayList;

public class Scenario {

    private String disaster;
    private Location[] locations;


    public Scenario(String disaster) {
        this.disaster = disaster;
        locations = new Location[2];
    }

    public Location getLocation(int choice) {
        if (choice == 1) {
            return locations[0];
        } else if(choice == 2) {
            return locations[1];
        }
        return null;
    }
    public String getDisaster() {
        return disaster;
    }

    public void setDisaster(String disaster) {
        this.disaster = disaster;
    }

    public Location[] getLocations() {
        return locations;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }


}
