package COMP90041.Project_DisasterReliefRobots;

public class Animal extends Resident{
    String species;
    Boolean isPet;

    public Animal(String gender, int age, String bodyType, String species, Boolean isPet) {
        super(gender, age, bodyType);
        this.species = species;
        this.isPet = isPet;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Boolean getPet() {
        return isPet;
    }

    public void setPet(Boolean pet) {
        isPet = pet;
    }
}
