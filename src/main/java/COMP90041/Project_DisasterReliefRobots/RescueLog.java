package COMP90041.Project_DisasterReliefRobots;

import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RescueLog {
    public void writeToCSV(String fileName, @NotNull Scenario scenario) {
        try {
            FileWriter csvWriter = new FileWriter(fileName, true);
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            csvWriter.append("Time: ").append(ft.format(dNow)).append("\n");
            csvWriter.append("gender,age,bodyType,profession,pregnant,species,isPet\n");
            csvWriter.append("scenario:").append(scenario.getDisaster()).append(",,,,,,\n");
            for (Location location : scenario.getLocations()) {
                if (location.isSaved()) {
                    csvWriter.append("location:" + location.getCoordinates() +
                            (location.isTrespassing() ? "trespassing" : "legal") + ",true" + ",,,,,\n");
                } else {
                    csvWriter.append("location:" + location.getCoordinates() +
                            (location.isTrespassing() ? "trespassing" : "legal") + ",,,,,,\n");
                }

                for (Resident resident : location.getResidents()) {
                    if (resident instanceof Human human) {
                        csvWriter.append("human," + human.getGender() + "," + human.getAge() + "," +
                                human.getBodyType() + "," + human.getProfession() + "," +
                                (human.getPregnant() ? "true" : "false") + ",,\n");
                    } else if (resident instanceof Animal animal) {
                        csvWriter.append("animal," + animal.getGender() + "," + animal.getAge() + "," +
                                animal.getBodyType() + ",," + animal.getSpecies() + "," +
                                (animal.getPet() ? "true" : "false") + "\n");
                    }
                }
            }
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printStatistics(int runNumber, @NotNull HashMap<String, int[]> attributes, ArrayList<Integer> savedHumanAge, String logPath) {
        // Create a list to hold the survival ratio of each attribute
        List<AttributeSurvivalRatio> survivalRatios = new ArrayList<>();
        // Iterate over the HashMap
        for (Map.Entry<String, int[]> entry : attributes.entrySet()) {
            String attributeName = entry.getKey();
            int[] values = entry.getValue();
            // Calculate the survival ratio
            double survivalRatio = (double) values[1] / values[0];
            // Add the survival ratio to the list
            survivalRatios.add(new AttributeSurvivalRatio(attributeName, survivalRatio));
        }

        // Sort the list
        Collections.sort(survivalRatios);
        saveStatisticsToFile(logPath, runNumber, survivalRatios, savedHumanAge);
    }

    public void saveStatisticsToFile(String logPath, int runNumber, List<AttributeSurvivalRatio> survivalRatios, ArrayList<Integer> savedHumanAge) {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        try {
            FileWriter writer = new FileWriter(logPath, true); // Set append to true
            writer.write("Time: " + ft.format(dNow) + "\n");
            writer.write("======================================\n");
            writer.write("# Statistic\n");
            writer.write("======================================\n");
            writer.write("- % SAVED AFTER " + runNumber + " RUNS\n");

            for (AttributeSurvivalRatio ratio : survivalRatios) {
                writer.write(String.format("%s: %.2f\n", ratio.getAttributeName(), ratio.getSurvivalRatio()));
            }

            // write average age and other statistics
            writer.write("--\n");
            writer.write(String.format("average age: %.2f", getAvgAge(savedHumanAge)));
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }
    private double getAvgAge(ArrayList<Integer> savedHumanAge) {
        int totalAge = 0;
        for (Integer age : savedHumanAge) {
            totalAge += age;
        }
        return (double) (totalAge / savedHumanAge.size());
    }

}
