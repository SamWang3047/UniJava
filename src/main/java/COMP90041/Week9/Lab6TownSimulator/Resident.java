package COMP90041.Week9.Lab6TownSimulator;

public class Resident extends Entity{
    public String name;

    public Resident(String name) {
        this.name = name;
    }

    @Override
    public String describe() {
        return "person named " + name;
    }
}
