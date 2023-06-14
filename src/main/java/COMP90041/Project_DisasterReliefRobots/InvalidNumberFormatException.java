package COMP90041.Project_DisasterReliefRobots;

import COMP90041.Project_DisasterReliefRobots.Location;
import COMP90041.Project_DisasterReliefRobots.Resident;
import COMP90041.Project_DisasterReliefRobots.Scenario;

public class InvalidNumberFormatException extends NumberFormatException {
    private Scenario scenario;
    private Location location;
    private Resident resident;

    public InvalidNumberFormatException() {
        super("invalid number format");
    }

    public InvalidNumberFormatException(int lineNumber) {
        super("invalid number format");
        System.out.println("WARNING: invalid number format in scenarios file in line " + lineNumber);
    }

    public InvalidNumberFormatException(Scenario scenario) {
        super("invalid number format");
        this.scenario = scenario;
    }

    public InvalidNumberFormatException(Location location) {
        super("invalid number format");
        this.location = location;
    }

    public InvalidNumberFormatException(Resident resident) {
        super("invalid number format");
        this.resident = resident;
    }

    public InvalidNumberFormatException(String message) {
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
