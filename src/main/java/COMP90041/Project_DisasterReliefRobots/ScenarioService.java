package COMP90041.Project_DisasterReliefRobots;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ScenarioService {

    public ArrayList<Scenario> loadScenariosFromFile(String scenariosFilePath) {
        ArrayList<Scenario> scenarios = new ArrayList<>();
        Scenario currentScenario = null;
        int currentLocationNum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(scenariosFilePath))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) {
                    continue; // Skip headers
                }

                try {
                    Object object = parseLine(line);

                    // Append object to relevant scenario or location
                    if (object instanceof Scenario) {
                        currentScenario = (Scenario) object;
                        scenarios.add(currentScenario);
                    } else if (object instanceof Location) {
                        if (currentScenario != null) {
                            currentScenario.getLocations()[currentLocationNum] = (Location) object;
                            currentLocationNum++;
                            if (currentLocationNum == 2) currentLocationNum = 0;
                        }
                    } else {
                        if (currentScenario != null) {
                            currentScenario.getLocations()[currentLocationNum].getResidents().add((Resident) object);
                        }
                    }

                } catch (InvalidDataFormatException e) {
                    System.out.println("WARNING: " + e.getMessage() + " in line " + lineNumber);
                } catch (InvalidNumberFormatException e) {
                    System.out.println("WARNING: " + e.getMessage() + " in line " + lineNumber);
                } catch (InvalidCharacteristicException e) {
                    System.out.println("WARNING: " + e.getMessage() + " in line " + lineNumber);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("java.io.FileNotFoundException: could not find scenarios file.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scenarios;
    }

    private Object parseLine(String line) throws InvalidDataFormatException, InvalidNumberFormatException, InvalidCharacteristicException {
        String[] data = line.split(",");

        // Check for invalid data format
        if (data.length != 8) {
            throw new InvalidDataFormatException();
        }

        // Parse and validate data
        if (data[0].startsWith("scenario:")) {
            return parseScenario(data); // Parse scenario
        } else if (data[0].startsWith("location:")) {
            return parseLocation(data); // Parse location
        } else {
            return parseCharacter(data); //Parse Resident
        }
    }
    private Scenario parseScenario(String[] data) throws InvalidCharacteristicException {
        // Parse scenario
        //start from the 9th character e.g. scenario:flood,,,,,,,
        String disaster = data[0].substring(9);
        Scenario scenario = new Scenario(disaster);
        if (disaster.equals("")) {
            disaster = "Flood";//Default disaster
            scenario.setDisaster(disaster);
            throw new InvalidCharacteristicException(scenario);
        }
        return scenario;
    }

    private Location parseLocation(String[] data) throws InvalidCharacteristicException, InvalidDataFormatException {
        // Parse location
        String[] locationInfo = data[0].split(";");//split location information e.g. location:13.7154 N;150.9094 W;trespassing,,,,,,,
        if (locationInfo.length != 3) {
            throw new InvalidDataFormatException();
        }

        //latitude[0,90] and longitude[0,180]
        String[] latitudeInfo = locationInfo[0].split(":");//e.g. location:13.7154 N
        String[] latitude = latitudeInfo[1].split(" ");//13.7154 N
        String[] longitude = locationInfo[1].split(" ");//150.9094 W
        double locationLatitude = 45;
        double locationLongitude = 90;
        try {
            locationLatitude = Double.parseDouble(latitude[0]);
        } catch (NumberFormatException e) {
            locationLatitude = 45;
        }

        try {
            locationLongitude = Double.parseDouble(longitude[0]);
        } catch (NumberFormatException e) {
            locationLatitude = 90;
        }

        char locationLatitudeDirection = latitude[1].charAt(0);
        char locationLongitudeDirection = longitude[1].charAt(0);
        //validation
        if (locationLatitude < 0 || locationLatitude > 90) {
            locationLatitude = 45;//Default latitude
            throw new InvalidCharacteristicException();
        }
        if (locationLongitude < 0 || locationLongitude > 180) {
            locationLongitude = 90;//Default latitude
            throw new InvalidCharacteristicException();
        }
        if (locationLatitudeDirection != 'N' && locationLatitudeDirection != 'M') {
            locationLatitudeDirection = 'N';
            throw new InvalidCharacteristicException();
        }
        if (locationLongitudeDirection != 'E' && locationLongitudeDirection != 'W') {
            locationLongitudeDirection = 'E';
            throw new InvalidCharacteristicException();
        }

        //status - trespassing or legal
        String status = locationInfo[2].split(",")[0];
        if (status == null) {
            status = "trespassing";
            throw new InvalidCharacteristicException();
        } else if (!status.equals("trespassing") && !status.equals("legal")) {
            status = "trespassing";
            throw new InvalidCharacteristicException();
        }
        return new Location(locationLatitude, locationLatitude, locationLatitudeDirection, locationLongitudeDirection, status);
    }

    private Resident parseCharacter(String[] data) throws NumberFormatException, InvalidCharacteristicException {
        //Parse Resident
        String resident = data[0];
        String gender = data[1];
        if (!Arrays.asList("MALE", "FEMALE").contains(gender.toUpperCase())) {
            gender = "NONE"; // default value
        }
        int age;
        try {
            age = Integer.parseInt(data[2]);
        } catch (NumberFormatException e) {
            age = 18;
            throw new InvalidCharacteristicException();
        }
        String bodyType = data[3];
        String profession = data[4];
        boolean isPregnant = Boolean.parseBoolean(data[5]);
        String species = data[6];
        boolean isPet = Boolean.parseBoolean(data[7]);

        // Validate bodyType
        if (!Arrays.asList("OVERWEIGHT", "AVERAGE", "ATHLETIC").contains(bodyType.toUpperCase())) {
            System.out.println("WARNING: invalid body type. Defaulting to 'average'.");
            bodyType = "UNSPECIFIED"; // default value
            throw new InvalidCharacteristicException();
        }

        // Validate profession for human
        if (resident.equalsIgnoreCase("human")) {
            if (age < 0) {
                age = 18;
                throw new InvalidCharacteristicException();
            } else if ((age < 17 || age > 68) && !profession.isEmpty()) {
                profession = "NONE"; // default value
                throw new InvalidCharacteristicException();
            } else if (!Arrays.asList("DOCTOR", "CEO", "CRIMINAL", "HOMELESS", "UNEMPLOYED").contains(profession.toUpperCase())) {
                System.out.println("WARNING: invalid profession. Defaulting to 'NONE'.");
                profession = "NONE"; // default value
                throw new InvalidCharacteristicException();
            }

            if (gender.toUpperCase().equals("MALE") && isPregnant) {
                isPregnant = false;
                throw new InvalidCharacteristicException();
            }
        }

        // Create Human or Animal Resident based on 'resident' field
        if (resident.equalsIgnoreCase("human")) {
            Human human = new Human(gender, age, bodyType, profession, isPregnant);
            return human;
        } else {
            Animal animal = new Animal(gender, age, bodyType, species, isPet);
            return animal;
        }
    }



}
