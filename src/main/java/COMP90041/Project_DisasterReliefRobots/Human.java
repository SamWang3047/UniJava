package COMP90041.Project_DisasterReliefRobots;

public class Human extends Resident{
    String profession;
    Boolean isPregnant;

    String ageCategory;

    public Human(String gender, int age, String bodyType, String profession, Boolean isPregnant) {
        super(gender, age, bodyType);
        this.profession = profession;
        this.isPregnant = isPregnant;
        if (age >= 0 && age <= 4) {
            ageCategory = "BABY";
        } else if (age >=5 && age <= 16) {
            ageCategory = "CHILD";
        } else if (age >= 17 && age <= 68) {
            ageCategory = "ADULT";
        } else {
            ageCategory = "SENIOR";
        }
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

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }
}
