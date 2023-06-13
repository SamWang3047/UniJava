package COMP90041.Project_DisasterReliefRobots;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * COMP90041, Sem1, 2023: Final Project
 *
 * @author: student id: 1406985
 * student email: zhiyuanw6@student.unimelb.edu.au
 * personal website: www.hellosam.top
 */
public class RescueBot {

    private static final double IS_TRESPASSING = 2.0 / 3;
    private static final double HUMAN_SCORE = 5.0;

    private static final String USER_LOG_PATH = "userRescueBot.csv";
    private static final String SIMULATION_LOG_PATH = "simulationRescueBot.csv";

    /**
     * Decides whether to save the passengers or the pedestrians
     *
     * @param scenario : the ethical dilemma
     * @return Decision: which group to save
     */
    public static Location decide(Scenario scenario) {
        // a very simple decision engine
        // TODO: take into account at least 5 characteristics
        // Location resident number; isTrespassing; human number; pregnant human number; animal number
        Location bestLocation = null;
        double bestScore = Integer.MIN_VALUE;
        // Iterate through all locations in the scenario
        int locationIndex = 0, savedLocationIndex = 0;
        for (Location location : scenario.getLocations()) {
            double score = 0;
            // Consider number of residents: more residents mean higher score
            score += location.getResidents().size();

            // Consider number of humans: more humans mean higher score
            for (Resident resident : location.getResidents()) {
                if (resident instanceof Human) {
                    score += HUMAN_SCORE;
                    // If the human is pregnant, further increase the score
                    if (((Human) resident).getPregnant()) {
                        score++;
                    }
                } else if (resident instanceof Animal) {
                    score++;
                }
            }

            // Consider trespassing: if residents are trespassing, reduce the score
            if (location.isTrespassing()) {
                score *= IS_TRESPASSING;
            }

            // Update the best location if this location's score is higher
            if (score > bestScore) {
                bestScore = score;
                bestLocation = location;
                savedLocationIndex = locationIndex;
            }
            locationIndex++;
        }
        scenario.getLocation(savedLocationIndex).setSaved(true);
        return bestLocation;
    }

    private static void displayMainMenu(Scanner scanner, ScenarioService scenarioService, String scenariosFilePath, String userLogFile, String simulationLogFile) {
        // Load scenarios and display the number of imported scenarios
        if (scenariosFilePath != null) {
            // Load scenarios from file
            scenarioService.loadScenariosFromFile(scenariosFilePath);
            System.out.println(scenarioService.getScenarios().size() + " scenarios imported.");
        } else {
            // Generate random scenarios
            scenarioService.randomScenarioGeneration();
        }

        String command = "";
        while (!command.equals("quit") && !command.equals("q")) {
            System.out.println("Please enter one of the following commands to continue:");
            System.out.println("- judge scenarios: [judge] or [j]");
            System.out.println("- run simulations with the in-built decision algorithm: [run] or [r]");
            System.out.println("- show audit from history: [audit] or [a]");
            System.out.println("- quit the program: [quit] or [q]");
            System.out.print("> ");

            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "judge":
                case "j":
                    // TODO: Implement judging scenarios
                    scenarioService.collectUserConsent(scanner);
                    scenarioService.presentScenarios(scanner);
                    break;
                case "run":
                case "r":
                    // TODO: Implement running simulations
                    scenarioService.setScenarios(new ArrayList<>()); //clear current scenarios
                    scenarioService.randomScenarioGeneration(getScenarioCount(scanner));
                    simulation(scenarioService, simulationLogFile);
                    break;
                case "audit":
                case "a":
                    // TODO: Implement audit history
                    scenarioService.setScenarios(new ArrayList<>());//clear current scenarios
                    scenarioService.audit(scanner, userLogFile);
                    break;
                case "quit":
                case "q":
                    // Exit the program
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
        scanner.close();
    }

    private static void printHelpAndExit() {
        System.out.println("RescueBot - COMP90041 - Final Project");
        System.out.println();
        System.out.println("Usage: java RescueBot [arguments]");
        System.out.println();
        System.out.println("Arguments:");
        System.out.println("-s or --scenarios    Optional: path to scenario file");
        System.out.println("-h or --help         Optional: Print Help (this message) and exit");
        System.out.println("-l or --log          Optional: path to data log file");
        System.out.println();
        System.exit(0);
    }

    private static int getScenarioCount(Scanner scanner) {
        Integer scenarioCount = null;
        System.out.println("How many scenarios should be run?");
        while (scenarioCount == null) {
            System.out.print("> ");
            String input = scanner.nextLine();
            try {
                scenarioCount = Integer.parseInt(input);
                if (scenarioCount < 0) {
                    System.out.println("Invalid input! The number of scenarios should be positive.");
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! How many scenarios should be run?");
            }
        }
        return scenarioCount;
    }

    private static void simulation(ScenarioService scenarioService, String simulationLogPath) {
        RescueLog rescueLog = new RescueLog();
        for (Scenario scenario : scenarioService.getScenarios()) {
            scenarioService.runSimulation(scenario, decide(scenario));
            rescueLog.writeToCSV(simulationLogPath, scenario ,true);
        }
        scenarioService.printStatistics(scenarioService.getScenarios().size());
    }

    /**
     * Program entry
     */
    public static void main(String @NotNull [] args) {
        Scanner scanner = new Scanner(System.in);
        String scenariosFilePath = null;
        String userLogPath = null;
        String simulationLogPath = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                case "--scenarios":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        scenariosFilePath = args[i + 1];
                        i++;  // skip next arg
                    } else {
                        scenariosFilePath = null;
                    }
                    break;
                case "-l":
                case "--log":
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        userLogPath = args[i + 1];
                        simulationLogPath = args[i + 1];
                        i++;  // skip next arg
                    }
                    break;
                case "-h":
                case "--help":
                    printHelpAndExit();
                    break;
                default:
                    System.out.println("Invalid argument: " + args[i]);
                    printHelpAndExit();
                    break;
            }
        }
        if (userLogPath == null) {
            userLogPath = USER_LOG_PATH;
        }
        if (simulationLogPath == null) {
            simulationLogPath = SIMULATION_LOG_PATH;
        }

        ScenarioService scenarioService = new ScenarioService(userLogPath, simulationLogPath);

        // Display the welcome message
        try {
            Path filePath = Paths.get("welcome.ascii");
            Files.lines(filePath).forEachOrdered(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading welcome.ascii file: " + e.getMessage());
            System.exit(1);
        }
        // Display the main menu
        displayMainMenu(scanner, scenarioService, scenariosFilePath, userLogPath, simulationLogPath);
    }

}
