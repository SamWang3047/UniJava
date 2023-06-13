package COMP90041.Project_DisasterReliefRobots;

import java.io.IOException;
import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * COMP90041, Sem1, 2023: Final Project
 *
 * @author: student id: 1406985
 * student email: zhiyuanw6@student.unimelb.edu.au
 * personal website: www.hellosam.top
 */
public class RescueBot {

    /**
     * Decides whether to save the passengers or the pedestrians
     * @param scenario : the ethical dilemma
     * @return Decision: which group to save
     */
    public static Location decide(Scenario scenario) {
        // a very simple decision engine
        // TODO: take into account at least 5 characteristics

        // 50/50
        if (Math.random() > 0.5) {
            return scenario.getLocation(1);
        } else {
            return scenario.getLocation(2);
        }
    }

    private static void displayMainMenu(Scanner scanner, ScenarioService scenarioService, String scenariosFilePath) {

        // Load scenarios and display the number of imported scenarios
        if (scenariosFilePath == null) {
            // Generate random scenarios
            int randomScenarioNumber = 0;
            scenarioService.randomScenarioGeneration(randomScenarioNumber);
        } else {
            // Load scenarios from file
            scenarioService.loadScenariosFromFile(scenariosFilePath);
        }

        System.out.println(scenarioService.getScenarios().size() + " scenarios imported.");

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
