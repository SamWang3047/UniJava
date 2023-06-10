package COMP90041.Project_DisasterReliefRobots;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ScenarioService {

    public void loadScenariosFromFile(String scenariosFilePath) {
        ArrayList<Scenario> scenarios = new ArrayList<>();
        Scenario currentScenario = null;

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
                } catch (InvalidDataFormatException | InvalidNumberException | InvalidFieldValuesException e) {
                    System.out.println("WARNING: " + e.getMessage() + " in line " + lineNumber);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("java.io.FileNotFoundException: could not find scenarios file.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object parseLine(String line) throws InvalidDataFormatException, InvalidNumberException, InvalidFieldValuesException {
        String[] data = line.split(",");

        // Check for invalid data format
        if (data.length != 8) {
            throw new InvalidDataFormatException();
        }

        // Parse and validate data
        if (data[0].startsWith("scenario:")) {
            //start from the 9th character e.g. scenario:flood,,,,,,,
            String disaster = data[0].substring(9);
            if (disaster.equals("")) {
                throw new InvalidFieldValuesException();
                //Default disaster
                return new Scenario("Flood");
            }
            // Parse scenario
            return new Scenario(disaster);
        } else if (data[0].startsWith("location:")) {
            // Parse location
            //split location information e.g. location:13.7154 N;150.9094 W;trespassing,,,,,,,
            String[] locationInfo = data[0].split(";");
            //e.g. location:13.7154 N
            String[] latiduteInfo = locationInfo[0].split(":");
            //13.7154 N
            String[] latidute = latiduteInfo[1].split(" ");
            //150.9094 W
            String[] longitude = locationInfo[1].split(" ");

            Location location = new Location();
        } else {
            // Parse resident
        }
    }


}
