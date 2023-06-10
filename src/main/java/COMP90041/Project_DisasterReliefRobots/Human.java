package COMP90041.Project_DisasterReliefRobots;

public class Human extends Resident{
    String profession;
    Boolean isPregnant;

    public Human(String gender, int age, String bodyType, String profession, Boolean isPregnant) {
        super(gender, age, bodyType);
        this.profession = profession;
        this.isPregnant = isPregnant;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Boolean getPregnant() {
        return isPregnant;
    }

    public void setPregnant(Boolean pregnant) {
        isPregnant = pregnant;
    }
}
