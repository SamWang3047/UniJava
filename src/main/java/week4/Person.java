package week4;

public class Person {

    public Person (String name, int age, String gender){
        setName(name);
        setAge(age);
        setGender(gender);
    }
    public Person (int age, String gender) {
        setName("");
    }
    private String name;
    private int age;
    private String gender;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public int getSurfaceSize() {
        return surfaceSize;
    }

    public void setSurfaceSize(int surfaceSize) {
        this.surfaceSize = surfaceSize;
    }

    public int getPlasticBodyPercentage() {
        return plasticBodyPercentage;
    }

    public void setPlasticBodyPercentage(int plasticBodyPercentage) {
        this.plasticBodyPercentage = plasticBodyPercentage;
    }

    private int surfaceSize;
    private int plasticBodyPercentage;

    public int getMaxBatteryCharge() {
        return maxBatteryCharge;
    }

    public void setMaxBatteryCharge(int maxBatteryCharge) {
        this.maxBatteryCharge = maxBatteryCharge;
    }

    public int getCurrentBatteryCharge() {
        return currentBatteryCharge;
    }

    public void setCurrentBatteryCharge(int currentBatteryCharge) {
        this.currentBatteryCharge = currentBatteryCharge;
    }

    private int maxBatteryCharge;
    private int currentBatteryCharge;

    public int getConfigBattery() {
        return configBattery;
    }

    public void setConfigBattery(int configBattery) {
        this.configBattery = configBattery;
    }

    public int getConfigFilters() {
        return configFilters;
    }

    public void setConfigFilters(int configFilters) {
        this.configFilters = configFilters;
    }

    private int configBattery;
    private int configFilters;

}
