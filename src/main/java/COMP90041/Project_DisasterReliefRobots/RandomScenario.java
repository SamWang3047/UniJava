package COMP90041.Project_DisasterReliefRobots;

import java.util.ArrayList;
import java.util.Random;

public class RandomScenario {
    private static final String[] AUTO_GENERATED_BODYTYPE = {"OVERWEIGHT", "AVERAGE", "ATHLETIC"};
    private static final String[] AUTO_GENERATED_GENDER = {"MALE", "FEMALE"};
    private static final String[] AUTO_GENERATED_SPECIES = {"PUPPY", "CAT", "KOALA", "WALLABY", "SNAKE", "LION", "DOG", "DINGO", "PLATYPUS"};
    private static final String[] AUTO_GENERATED_PROFESSION = {"DOCTOR", "CEO", "CRIMINAL", "HOMELESS", "UNEMPLOYED", "ATHLETIC", "STUDENT", "PROFESSOR", "NONE"};
    private static final String[] AUTO_GENERATED_DISASTER = {"CYCLONE", "FLOOD", "EARTHQUAKE", "BUSHFIRE", "METEORITE"};
    private static final Character[] LATITUDE_DIRECTION = {'N', 'S'};
    private static final Character[] LONGITUDE_DIRECTION = {'E', 'W'};

    private int randomScenarioNumber;

    public RandomScenario(int randomScenarioNumber) {
        this.randomScenarioNumber = randomScenarioNumber;
    }
    public void randomScenarioGeneration(ArrayList<Scenario> scenarios) {
        Random r = new Random();
        String disaster;
        int scenarioNum = r.nextInt(7) + 3; // Number of scenarios [3, 10]
        for (int i = 0; i < scenarioNum; i++) {
            int locationNum = r.nextInt(4) + 2; // Number of locations [2, 6]
            ArrayList<Location> locations = new ArrayList<>();
            for (int j = 0; j < locationNum; j++) {
                locations.add(randomLocation());
            }
            disaster = AUTO_GENERATED_DISASTER[r.nextInt(AUTO_GENERATED_DISASTER.length)].toLowerCase();
            scenarios.add(new Scenario(disaster, locations));
        }
    }

    public Location randomLocation() {
        Random r = new Random();
        double lat = r.nextDouble() * 180 - 90;  // latitude [-90, 90]
        char latDirect = LATITUDE_DIRECTION[0];
        if (lat < 0) latDirect = LATITUDE_DIRECTION[1];
        double lon = r.nextDouble() * 360 - 180;  // longitude [-180, 180]
        char lonDirect = LONGITUDE_DIRECTION[0];
        if (lon < 0) latDirect = LONGITUDE_DIRECTION[1];
        boolean isTrespassing = r.nextBoolean();

        int numCharacters = r.nextInt(7) + 1;  // Number of characters [1, 8]
        ArrayList<Resident> residents = new ArrayList<>();
        for (int i = 0; i < numCharacters; i++) {
            residents.add(randomCharacter());
        }

        return new Location(lat, lon, latDirect, lonDirect, isTrespassing, residents);
    }


    public Resident randomCharacter() {
        Random r = new Random();
        if (r.nextBoolean()) {
            // Generate a human
            int age = r.nextInt(100);
            String gender = AUTO_GENERATED_GENDER[r.nextInt(AUTO_GENERATED_GENDER.length)].toLowerCase();
            String bodyType = AUTO_GENERATED_BODYTYPE[r.nextInt(AUTO_GENERATED_BODYTYPE.length)].toLowerCase();
            String profession = AUTO_GENERATED_PROFESSION[r.nextInt(AUTO_GENERATED_PROFESSION.length)].toLowerCase();
            boolean isPregnant = false;
            //Only female can get pregnant
            if (gender.equals(AUTO_GENERATED_GENDER[1])) {
                isPregnant = r.nextBoolean();
            }
            return new Human(gender, age, bodyType, profession, isPregnant);
        } else {
            // Generate an animal
            int age = r.nextInt(15);
            String gender = AUTO_GENERATED_GENDER[r.nextInt(AUTO_GENERATED_GENDER.length)].toLowerCase();
            String bodyType = AUTO_GENERATED_BODYTYPE[r.nextInt(AUTO_GENERATED_BODYTYPE.length)].toLowerCase();
            String species = AUTO_GENERATED_SPECIES[r.nextInt(AUTO_GENERATED_SPECIES.length)].toLowerCase();
            boolean isPet = r.nextBoolean();
            return new Animal(gender, age, bodyType, species, isPet);
        }
    }

    public int getRandomScenarioNumber() {
        return randomScenarioNumber;
    }

    public void setRandomScenarioNumber(int randomScenarioNumber) {
        this.randomScenarioNumber = randomScenarioNumber;
    }
}
