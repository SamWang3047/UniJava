package COMP90041.Project_DisasterReliefRobots.Exceptions;

import COMP90041.Project_DisasterReliefRobots.Location;
import COMP90041.Project_DisasterReliefRobots.Resident;
import COMP90041.Project_DisasterReliefRobots.Scenario;

public class InvalidDataFormatException extends Exception{
    private Scenario scenario;
    private Location location;
    private Resident resident;
    public InvalidDataFormatException() {
        super("invalid data format");
    }

    public InvalidDataFormatException(int lineNumber) {
        super("invalid data format");
        System.out.println("WARNING: invalid data format in line " + lineNumber);
    }

    public InvalidDataFormatException(Scenario scenario) {
        super("invalid data format");
        this.scenario = scenario;
    }
    public InvalidDataFormatException(Location location) {
        super("invalid data format");
        this.location = location;
    }
    public InvalidDataFormatException(Resident resident) {
        super("invalid data format");
        this.resident = resident;
    }
    public InvalidDataFormatException(String message) {
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
