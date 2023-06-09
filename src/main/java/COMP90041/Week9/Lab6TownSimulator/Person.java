package COMP90041.Week9.Lab6TownSimulator;

public class Person extends Resident{
    public Person(String name) {
        super(name);
    }

    @Override
    public String interact() {
        return "Hi, my name is " + super.name + ", it's nice to meet you!";
    }
}
