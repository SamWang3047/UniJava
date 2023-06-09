package COMP90041.Week9.Lab6TownSimulator;

public class Dog extends Resident{
    public Dog(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return "dog named " + super.name;
    }

    @Override
    public String interact() {
        return "woof woof";
    }
}
