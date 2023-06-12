package COMP90041.Project_DisasterReliefRobots;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
     *
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

    private static void displayMainMenu(Scanner scanner) {
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

    /**
     * Program entry
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String scenariosFilePath = null;

        ScenarioService scenarioService = new ScenarioService();

        if (args[0].equals("--help") || args[0].equals("-h")) {
            System.out.println("RescueBot - COMP90041 - Final Project\n" +
                    "\n" +
                    "Usage: java RescueBot [arguments]\n" +
                    "\n" +
                    "Arguments:\n" +
                    "-s or --scenarios    Optional: path to scenario file\n" +
                    "-h or --help        Optional: Print Help (this message) and exit\n" +
                    "-l or --log        Optional: path to data log file");
        } else if (args.length > 1) {
            if (args[0].equals("--scenarios") || args[0].equals("-s")) {
                scenariosFilePath = args[1];
            }
        }

        // Display the welcome message
        try {
            Path filePath = Paths.get("welcome.ascii");
            Files.lines(filePath).forEachOrdered(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading welcome.ascii file: " + e.getMessage());
            System.exit(1);
        }

        // Load scenarios and display the number of imported scenarios
        ArrayList<Scenario> scenarios = null;
        if (scenariosFilePath == null) {
            // Generate random scenarios
        } else {
            // Load scenarios from file
            scenarios = scenarioService.loadScenariosFromFile(scenariosFilePath);
        }
        System.out.println(scenarios.size() + " scenarios imported.");

        // Display the main menu
        displayMainMenu(scanner);
    }
}
