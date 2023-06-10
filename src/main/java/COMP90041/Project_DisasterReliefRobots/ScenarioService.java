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
            throw new InvalidDataFormatException("invalid data format in scenarios file");
        }

        // Parse and validate data
        if (data[0].startsWith("scenario:")) {
            Scenario scenario = new Scenario(data[0]);
            // Parse scenario
            return scenario;
        } else if (data[0].startsWith("location:")) {
            // Parse location
            String[]
            Location location = new Location();
        } else {
            // Parse resident
        }
    }


}
