package COMP90041.Project_DisasterReliefRobots.Exceptions;

import COMP90041.Project_DisasterReliefRobots.Location;
import COMP90041.Project_DisasterReliefRobots.Resident;
import COMP90041.Project_DisasterReliefRobots.Scenario;

public class InvalidCharacteristicException extends Exception{
    private Scenario scenario;
    private Location location;
    private Resident resident;

    public InvalidCharacteristicException() {
        super("invalid characteristic");
    }
    public InvalidCharacteristicException(int lineNumber) {
        super("invalid characteristic");
        System.out.println("WARNING: invalid characteristic in line " + lineNumber);
    }
    public InvalidCharacteristicException(Scenario scenario) {
        super("invalid characteristic");
        this.scenario = scenario;
    }

    public InvalidCharacteristicException(Location location) {
        super("invalid characteristic");
        this.location = location;
    }

    public InvalidCharacteristicException(Resident resident) {
        super("invalid characteristic");
        this.resident = resident;
    }

    public InvalidCharacteristicException(String message) {
        super(message);
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
