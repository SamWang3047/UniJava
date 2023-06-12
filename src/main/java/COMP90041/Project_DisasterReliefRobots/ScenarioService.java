package COMP90041.Project_DisasterReliefRobots;

import COMP90041.Project_DisasterReliefRobots.Exceptions.InvalidCharacteristicException;
import COMP90041.Project_DisasterReliefRobots.Exceptions.InvalidDataFormatException;
import COMP90041.Project_DisasterReliefRobots.Exceptions.InvalidInputException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ScenarioService {
    private static final String[] BODYTYPE = {"OVERWEIGHT", "AVERAGE", "ATHLETIC"};

    private static final String[] GENDER = {"MALE", "FEMALE"};

    private static final String[] STATUS = {"TRESPASSING", "LEGAL"};

    private static final String[] AUTO_GENERATED_SPECIES = {"PUPPY", "CAT", "KOALA", "WALLABY", "SNAKE", "LION", "DOG", "DINGO", "PLATYPUS"};
    private static final String[] AUTO_GENERATED_PROFESSION = {"DOCTOR", "CEO", "CRIMINAL", "HOMELESS", "UNEMPLOYED", "ATHLETIC", "STUDENT", "PROFESSOR", "NONE"};
    private static final String[] AUTO_GENERATED_DISASTER = {"CYCLONE", "FLOOD", "EARTHQUAKE", "BUSHFIRE", "METEORITE"};
    private static final Character[] LATITUDE_DIRECTION = {'N', 'S'};
    private static final Character[] LONGITUDE_DIRECTION = {'E', 'W'};

    private ArrayList<Scenario> scenarios;

    private boolean consent;

    public ScenarioService() {
        scenarios = new ArrayList<>();
        consent = false;
    }

    public void loadScenariosFromFile(String scenariosFilePath) {
        Scenario currentScenario = null;
        Location currentLocation = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(scenariosFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) {
                    continue; // Skip headers
                }
                Object object = parseLine(line, lineNumber);
                // Append object to relevant scenario or location
                if (object instanceof Scenario) {
                    currentScenario = (Scenario) object;
                    scenarios.add(currentScenario);
                } else if (object instanceof Location) {
                    currentLocation = (Location) object;
                    if (currentScenario != null) {
                        currentScenario.getLocations().add(currentLocation);
                    }
                } else {
                    if (currentScenario != null && currentLocation != null) {
                        currentLocation.getResidents().add((Resident) object);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("java.io.FileNotFoundException: could not find scenarios file.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object parseLine(String line, int lineNumber) {
        String[] data = line.split(",", -1);
        // Check for invalid data format
        try {
            if (data.length != 8) {
                throw new InvalidDataFormatException(lineNumber);
            }
        } catch (InvalidDataFormatException ignored) {
        }
        // Parse and validate data
        if (data[0].startsWith("scenario:")) {
            return parseScenario(data, lineNumber); // Parse scenario
        } else if (data[0].startsWith("location:")) {
            return parseLocation(data, lineNumber); // Parse location
        } else {
            return parseCharacter(data, lineNumber); //Parse Resident
        }
    }

    private Scenario parseScenario(String[] data, int lineNumber) {
        // Parse scenario
        //start from the 9th character e.g. scenario:flood,,,,,,,
        String disaster = data[0].substring(9);
        Scenario scenario = new Scenario(disaster);
        try {
            if (disaster.equals("")) {
                throw new InvalidCharacteristicException(lineNumber);
            }
        } catch (InvalidCharacteristicException e) {
            disaster = "flood";//Default disaster
            scenario.setDisaster(disaster);
        }
        return scenario;
    }

    private Location parseLocation(String[] data, int lineNumber) {
        // Parse location
        String[] locationInfo = data[0].split(";");//split location information e.g. location:13.7154 N;150.9094 W;trespassing,,,,,,,
        try {
            if (locationInfo.length != 3) {
                throw new InvalidDataFormatException(lineNumber);
            }
        } catch (InvalidDataFormatException ignored) {
        }
        //latitude[0,90] and longitude[0,180]
        String[] latitudeInfo = locationInfo[0].split(":");//e.g. location:13.7154 N
        String[] latitude = latitudeInfo[1].split(" ");//13.7154 N
        String[] longitude = locationInfo[1].split(" ");//150.9094 W
        double locationLatitude = 0;
        double locationLongitude = 0;

        try {
            locationLatitude = Double.parseDouble(latitude[0]);
        } catch (NumberFormatException e) {
            System.out.println("WARNING: invalid number format in line " + lineNumber);
            locationLatitude = 45;
        }

        try {
            locationLongitude = Double.parseDouble(longitude[0]);
        } catch (NumberFormatException e) {
            System.out.println("WARNING: invalid number format in line " + lineNumber);
            locationLatitude = 90;
        }

        char locationLatitudeDirection = latitude[1].charAt(0);
        char locationLongitudeDirection = longitude[1].charAt(0);
        //Validation
        try { //Latitude field validation
            if (locationLatitude < 0 || locationLatitude > 90) {
                throw new InvalidCharacteristicException(lineNumber);
            }
        } catch (InvalidCharacteristicException e) {
            locationLatitude = 45; //Default latitude
        }
        try { //Longitude field validation
            if (locationLongitude < 0 || locationLongitude > 180) {
                throw new InvalidCharacteristicException(lineNumber);
            }
        } catch (InvalidCharacteristicException e) {
            locationLongitude = 90; //Default latitude
        }

        try { //Latitude direction validation
            if (!Arrays.stream(LATITUDE_DIRECTION).toList().contains(locationLatitudeDirection)) {
                throw new InvalidCharacteristicException(lineNumber);
            }
        } catch (InvalidCharacteristicException e) {
            locationLatitudeDirection = 'N';
        }
        try { //Longitude direction validation
            if (!Arrays.stream(LONGITUDE_DIRECTION).toList().contains(locationLongitudeDirection)) {
                throw new InvalidCharacteristicException(lineNumber);
            }
        } catch (InvalidCharacteristicException e) {
            locationLongitudeDirection = 'E';
        }

        //status - trespassing or legal
        String status = locationInfo[2].split(",")[0];
        try { //Longitude direction validation
            if (status == null) {
                throw new InvalidCharacteristicException(lineNumber);
            } else if (!Arrays.stream(STATUS).toList().contains(status.toUpperCase())) {
                throw new InvalidCharacteristicException(lineNumber);
            }
        } catch (InvalidCharacteristicException e) {
            status = "trespassing";
        }
        boolean isTrespassing = status.equals("trespassing");
        return new Location(locationLatitude, locationLongitude, locationLatitudeDirection, locationLongitudeDirection, isTrespassing);
    }

    private Resident parseCharacter(String[] data, int lineNumber) {
        //Parse Resident
        String resident = data[0];
        String gender = data[1];
        try { //Gender validation
            if (!Arrays.stream(GENDER).toList().contains(gender.toUpperCase())) { //Gender validation
                throw new InvalidCharacteristicException(lineNumber);
            }
        } catch (InvalidCharacteristicException e) {
            gender = "NONE"; // default value
        }

        int age;
        try {
            age = Integer.parseInt(data[2]);
        } catch (NumberFormatException e) {
            System.out.println("WARNING: invalid number format in line " + lineNumber);
            age = 18;
        }
        String bodyType = data[3];
        String profession = data[4];
        boolean isPregnant = Boolean.parseBoolean(data[5]);
        String species = data[6];
        boolean isPet = Boolean.parseBoolean(data[7]);

        try { // Validate bodyType
            if (!Arrays.stream(BODYTYPE).toList().contains(bodyType.toUpperCase())) {
                System.out.println("WARNING: invalid body type. Defaulting to 'average'.");
                throw new InvalidCharacteristicException(lineNumber);
            }
        } catch (InvalidCharacteristicException e) {
            bodyType = "UNSPECIFIED"; // default value
        }


        // Validate profession for human
        if (resident.equalsIgnoreCase("human")) {
            try { // Validate age
                if (age < 0) {
                    throw new InvalidCharacteristicException(lineNumber);
                }
            } catch (InvalidCharacteristicException e) {
                age = 18; // default value
            }

            try { // Validate profession
                if ((age < 17 || age > 68) && !profession.toUpperCase().equals("NONE")) {
                    throw new InvalidCharacteristicException(lineNumber);
                }
            } catch (InvalidCharacteristicException e) {
                profession = "NONE"; // default value
            }

            try { // Validate pregnant
                if (gender.toUpperCase().equals("MALE") && isPregnant) {
                    throw new InvalidCharacteristicException(lineNumber);
                }
            } catch (InvalidCharacteristicException e) {
                isPregnant = false;// default value
            }
        }

        // Create Human or Animal Resident based on 'resident' field
        if (resident.equalsIgnoreCase("human")) {
            return new Human(gender, age, bodyType, profession, isPregnant);
        } else {
            return new Animal(gender, age, bodyType, species, isPet);
        }
    }

    public void collectUserConsent(Scanner scanner) {
        System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
        boolean notConsent = true;
        while (notConsent) {
            try {
                System.out.print("> ");
                String response = scanner.nextLine().toLowerCase();
                switch (response) {
                    case "yes" -> {
                        consent = true;
                        notConsent = false;
                    }
                    case "no" -> {
                        consent = false;
                        notConsent = false;
                    }
                    default -> throw new InvalidInputException();
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void presentScenarios(Scanner scanner) {
        for (int i = 0; i < scenarios.size(); i++) {
            scenarios.get(i).presentScenario();
            deployRescueBot(scanner, scenarios.get(i));
        }
    }

    public void deployRescueBot(Scanner scanner, Scenario scenario) {
        System.out.println("To which location should RescueBot be deployed?");
        while (true) {
            System.out.print("> ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= scenario.getLocations().size()) {
                    break;
                } else {
                    System.out.println("Invalid response! To which location should RescueBot be deployed?");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid response! To which location should RescueBot be deployed?");
                scanner.next();
            }
        }

    }

    public void randomScenarioGeneration() {
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
        char latDirec = 'S'; if (lat < 0) latDirec = 'N';
        double lon = r.nextDouble() * 360 - 180;  // longitude [-180, 180]
        char lonDirec = 'E'; if (lon < 0) latDirec = 'W';
        boolean isTrespassing = r.nextBoolean();

        int numCharacters = r.nextInt(7) + 1;  // Number of characters [1, 8]
        ArrayList<Resident> residents = new ArrayList<>();
        for (int i = 0; i < numCharacters; i++) {
            residents.add(randomCharacter());
        }

        return new Location(lat, lon, latDirec, lonDirec, isTrespassing, residents);
    }


    public Resident randomCharacter() {
        Random r = new Random();
        if (r.nextBoolean()) {
            // Generate a human
            int age = r.nextInt(100);
            String gender = GENDER[r.nextInt(GENDER.length)].toLowerCase();
            String bodyType = BODYTYPE[r.nextInt(BODYTYPE.length)].toLowerCase();
            String profession = AUTO_GENERATED_PROFESSION[r.nextInt(AUTO_GENERATED_PROFESSION.length)].toLowerCase();
            boolean isPregnant = r.nextBoolean();
            return new Human(gender, age, bodyType, profession, isPregnant);
        } else {
            // Generate an animal
            int age = r.nextInt(15);
            String gender = GENDER[r.nextInt(GENDER.length)].toLowerCase();
            String bodyType = BODYTYPE[r.nextInt(BODYTYPE.length)].toLowerCase();
            String species = AUTO_GENERATED_SPECIES[r.nextInt(AUTO_GENERATED_SPECIES.length)].toLowerCase();
            boolean isPet = r.nextBoolean();
            return new Animal(gender, age, bodyType, species, isPet);
        }
    }


    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(ArrayList<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }
}
