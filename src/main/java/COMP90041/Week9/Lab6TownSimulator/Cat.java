package COMP90041.Week9.Lab6TownSimulator;

public class Cat extends Resident{
    public Cat(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return "cat named " + super.name;
    }

    @Override
    public String interact() {
        return "meowwww";
    }
}
