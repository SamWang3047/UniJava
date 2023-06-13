package COMP90041.Project_DisasterReliefRobots;

import java.io.IOException;
import java.lang.Math;
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

    private static final double IS_TRESPASSING= 2.0/3;
    private static final double HUMAN_SCORE = 2.0;

    /**
     * Decides whether to save the passengers or the pedestrians
     * @param scenario : the ethical dilemma
     * @return Decision: which group to save
     */
    public static Location decide(Scenario scenario) {
        // a very simple decision engine
        // TODO: take into account at least 5 characteristics
        // Location resident number; isTrespassing; human number; pregnant human number; animal number;
        Location bestLocation = null;
        double bestScore = Integer.MIN_VALUE;
        // Iterate through all locations in the scenario
        for (Location location : scenario.getLocations()) {
            double score = 0;

            // Consider number of residents: more residents mean higher score
            score += location.getResidents().size();

            // Consider trespassing: if residents are trespassing, reduce the score
            if (location.isTrespassing()) {
                score *= IS_TRESPASSING;
            }

            // Consider number of humans: more humans mean higher score
            for (Resident resident : location.getResidents()) {
                if (resident instanceof Human) {
                    score += HUMAN_SCORE;
                    // If the human is pregnant, further increase the score
                    if (((Human) resident).getPregnant()) {
                        score++;
                    }
                }
            }

            // Consider number of animals: more animals mean higher score
            for (Resident resident : location.getResidents()) {
                if (resident instanceof Animal) {
                    score++;
                }
            }

            // Update the best location if this location's score is higher
            if (score > bestScore) {
                bestScore = score;
                bestLocation = location;
            }
        }
        return bestLocation;
    }

    private static void displayMainMenu(Scanner scanner, ScenarioService scenarioService, String scenariosFilePath) {
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
                    scenarioService.setScenarios(new ArrayList<>());
                    scenarioService.randomScenarioGeneration(getScenarioCount(scanner));
                    simulation(scenarioService);
                    break;
                case "audit":
                case "a":
                    // TODO: Implement audit history
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
                if(scenarioCount < 0){
                    System.out.println("Invalid input! The number of scenarios should be positive.");
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! How many scenarios should be run?");
            }
        }
        return scenarioCount;
    }

    private static void simulation(ScenarioService scenarioService) {
        for (Scenario scenario : scenarioService.getScenarios()) {
            scenarioService.runSimulation(scenario, decide(scenario));
        }
        scenarioService.printStatistics(scenarioService.getScenarios().size());
        scenarioService.getRescueLog(scenarioService.getScenarios().size());
    }

    /**
     * Program entry
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String scenariosFilePath = null;
        String logPath = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                case "--scenarios":
                    if (i + 1 < args.length) {
                        scenariosFilePath = args[i + 1];
                        i++;  // skip next arg
                    } else {
                        scenariosFilePath = null;
                    }
                    break;
                case "-l":
                case "--log":
                    if (i + 1 < args.length) {
                        logPath = args[i + 1];
                        i++;  // skip next arg
                    } else {
                        logPath = null;
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

        ScenarioService scenarioService = new ScenarioService(logPath);

        // Display the welcome message
        try {
            Path filePath = Paths.get("welcome.ascii");
            Files.lines(filePath).forEachOrdered(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading welcome.ascii file: " + e.getMessage());
            System.exit(1);
        }
        // Display the main menu
        displayMainMenu(scanner, scenarioService, scenariosFilePath);
    }

}
