package COMP90041.Project_DisasterReliefRobots;

public abstract class Resident {
    String gender;
    int age;
    String bodyType;

    public Resident(String gender, int age, String bodyType) {
        this.gender = gender;
        this.age = age;
        this.bodyType = bodyType;
    }

    public abstract String getDescription();

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
