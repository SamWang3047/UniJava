package COMP90041.Project_DisasterReliefRobots;

public class Human extends Resident{
    enum ageCategory {BABY, CHILD, ADULT, SENIOR} {
        String age;
    };
    String profession;
    Boolean isPregnant;

    String ageCategory;

    public Human(String gender, int age, String bodyType, String profession, Boolean isPregnant) {
        super(gender, age, bodyType);
        this.profession = profession;
        this.isPregnant = isPregnant;
        if (age >= 0 && age <= 4) {
            ageCategory = "baby";
        } else if (age >= 5 && age <= 16) {
            ageCategory = "child";
        } else if (age >= 17 && age <= 68) {
            ageCategory = "adult";
        } else {
            ageCategory = "senior";
        }
    }
    @Override
    public String getDescription() {
        String description = bodyType + " " + ageCategory;
        if (ageCategory.equals("adult")) {
            description += " " + profession;
        }

        description = description + " " + gender;

        if (gender.equals("female") && isPregnant) {
            description += " pregnant";
        }
        return description;
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
