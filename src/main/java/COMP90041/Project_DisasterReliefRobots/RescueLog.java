package COMP90041.Project_DisasterReliefRobots;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RescueLog {
    public void writeToCSV(String fileName, Scenario scenario, Boolean isSimulation) {
        try {
            FileWriter csvWriter = new FileWriter(fileName, true);
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            csvWriter.append("Time: ").append(ft.format(dNow)).append("\n");
            csvWriter.append("gender,age,bodyType,profession,pregnant,species,isPet\n");
            //simulation logfile set
            if (isSimulation) {
                csvWriter.append("scenario:").append(scenario.getDisaster()).append(",Simulation").append(",,,,,,\n");
            } else {
                csvWriter.append("scenario:").append(scenario.getDisaster()).append(",User").append(",,,,,,\n");
            }
            for (Location location : scenario.getLocations()) {
                if (location.isSaved()) {
                    csvWriter.append("location:" + location.getCoordinates() +
                            (location.isTrespassing() ? "trespassing" : "legal") + ",true" + ",,,,,,\n");
                } else {
                    csvWriter.append("location:" + location.getCoordinates() +
                            (location.isTrespassing() ? "trespassing" : "legal") + ",,,,,,,\n");
                }

                for (Resident resident : location.getResidents()) {
                    if (resident instanceof Human human) {
                        csvWriter.append("human," + human.getGender() + "," + human.getAge() + "," +
                                human.getBodyType() + "," + human.getProfession() + "," +
                                (human.getPregnant() ? "true" : "false") + ",,\n");
                    } else if (resident instanceof Animal animal) {
                        csvWriter.append("animal," + animal.getGender() + "," + animal.getAge() + "," +
                                animal.getBodyType() + ",,," + animal.getSpecies() + "," +
                                (animal.getPet() ? "true" : "false") + "\n");
                    }
                }
            }
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR: could not print results. Target directory does not exist.");
            System.exit(0);
        }
    }

}
