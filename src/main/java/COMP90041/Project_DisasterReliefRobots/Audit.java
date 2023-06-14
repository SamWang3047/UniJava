package COMP90041.Project_DisasterReliefRobots;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Audit extends ScenarioService{
    public Audit(String userLogPath) {
        super(userLogPath);
    }

    public void audit(Scanner scanner, String scenariosFilePath) {
        ArrayList<Scenario> simulationScenarios = new ArrayList<>();
        ArrayList<Scenario> userScenarios = new ArrayList<>();
        ArrayList<Integer> simulationSavedHumanAge = new ArrayList<>();
        ArrayList<Integer> userSavedHumanAge = new ArrayList<>();
        // load file into two different scenario lists, representing the User and Simulation
        loadScenarios(scenariosFilePath, simulationScenarios, userScenarios);

        // Load simulation scenario's residents' all attributes
        HashMap<String, int[]> simulationAttributes = new HashMap<>();
        HashMap<String, int[]> userAttributes = new HashMap<>();

        addAttributes(simulationScenarios, simulationSavedHumanAge, simulationAttributes);
        addAttributes(userScenarios, userSavedHumanAge, userAttributes);


        // Print Statistics
        if (!simulationScenarios.isEmpty()) printStatistics(simulationScenarios.size(), true, simulationAttributes, simulationSavedHumanAge);
        if (!userScenarios.isEmpty()) {
            System.out.println("\n");
            printStatistics(userScenarios.size(), false, userAttributes, userSavedHumanAge);
        }
        System.out.println();
        System.out.println("That's all. Press Enter to return to main menu.");
        System.out.print("> ");
        scanner.nextLine();  // Wait for the user to press Enter
    }

    private void addAttributes(ArrayList<Scenario> simulationScenarios, ArrayList<Integer> simulationSavedHumanAge, HashMap<String, int[]> simulationAttributes) {
        for (Scenario scenario : simulationScenarios) {
            for (Location location : scenario.getLocations()) {
                for (Resident resident : location.getResidents()) {
                    addResidentAttributes(resident, simulationAttributes);
                    if (location.isSaved()) {
                        addSavedResidentAttributes(resident, simulationAttributes);
                        simulationSavedHumanAge.add(resident.getAge());
                    }
                }
            }
        }
    }

    private void loadScenarios(String scenariosFilePath, ArrayList<Scenario> simulationScenarios, ArrayList<Scenario> userScenarios) {
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
                if (!line.startsWith("s") && !line.startsWith("l") && !line.startsWith("a") && !line.startsWith("h")) {
                    continue;
                }
                Object object = parseLine(line, lineNumber);
                // Append object to relevant scenario or location
                if (object instanceof Scenario) {
                    currentScenario = (Scenario) object;
                    if (currentScenario.isSimulation()) {
                        simulationScenarios.add(currentScenario);
                    } else {
                        userScenarios.add(currentScenario);
                    }
                } else {
                    currentLocation = getLocationOrResident(currentScenario, currentLocation, object);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("java.io.FileNotFoundException: could not find scenarios file.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addResidentAttributes(Resident resident, HashMap<String, int[]> attributes) {
        if (resident.getTrespassing()) { //trespassing
            addAttribute("trespassing", attributes);
        } else {
            addAttribute("legal", attributes);
        }
        if (resident instanceof Human) {
            addAttribute(resident.getClass().getSimpleName().toLowerCase(), attributes);//human class type (human or animal)
            addAttribute(((Human) resident).getAgeCategory(), attributes); //age category
            addAttribute(resident.getGender(), attributes); //gender
            addAttribute(resident.getBodyType(), attributes); //body type
            if (!((Human) resident).getProfession().equalsIgnoreCase("none")) {
                addAttribute(((Human) resident).getProfession().toLowerCase(), attributes); //profession
            }
            if (((Human) resident).getPregnant()) {
                addAttribute("pregnant", attributes); //pregnancy
            }
        } else {
            addAttribute(resident.getClass().getSimpleName().toLowerCase(),attributes); //animal class type (human or animal)
            addAttribute(((Animal) resident).getSpecies(),attributes); //species
            if (((Animal) resident).getPet()) {
                addAttribute("pet",attributes); //pets
            }
        }
    }

    private void addSavedResidentAttributes(Resident resident, HashMap<String, int[]> attributes) {
        if (resident.getTrespassing()) { //trespassing
            addSavedAttribute("trespassing", attributes);
        } else {
            addSavedAttribute("legal", attributes);
        }
        if (resident instanceof Human) {
            addSavedAttribute(resident.getClass().getSimpleName().toLowerCase(), attributes);//human class type (human or animal)
            addSavedAttribute(((Human) resident).getAgeCategory(), attributes); //age category
            addSavedAttribute(resident.getGender(), attributes); //gender
            addSavedAttribute(resident.getBodyType(), attributes); //body type
            if (!((Human) resident).getProfession().equalsIgnoreCase("none")) {
                addSavedAttribute(((Human) resident).getProfession().toLowerCase(), attributes); //profession
            }
            if (((Human) resident).getPregnant()) {
                addSavedAttribute("pregnant", attributes); //pregnancy
            }
        } else {
            addSavedAttribute(resident.getClass().getSimpleName().toLowerCase(), attributes); //animal class type (human or animal)
            addSavedAttribute(((Animal) resident).getSpecies(), attributes); //species
            if (((Animal) resident).getPet()) {
                addSavedAttribute("pet", attributes); //pets
            }
        }
    }

    private void addAttribute(String attribute, HashMap<String, int[]> attributes) {
        if (attributes.containsKey(attribute)) {
            attributes.get(attribute)[0]++; //total number
        } else {
            int[] value = new int[]{1, 0}; //total number
            attributes.put(attribute, value);
        }
    }

    private void addSavedAttribute(String attribute, HashMap<String, int[]> attributes) {
        attributes.get(attribute)[1]++; //saved number
    }

}
