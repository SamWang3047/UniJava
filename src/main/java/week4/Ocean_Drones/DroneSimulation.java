package week4.Ocean_Drones;

import java.util.Scanner;

/**
 * A simulation program for simulating the cleaning of bodies of water by
 * FLOAT-E drones.
 *
 * @author TODO: Fill out your name here
 */
public class DroneSimulation {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Read in the configuration from the user.
        System.out.print("Battery size (units): ");
        double maxBattery = scanner.nextDouble();

        System.out.print("Number of filters: ");
        int numFilters = scanner.nextInt();

        System.out.print("Water body size (units): ");
        double waterBodySize = scanner.nextDouble();

        System.out.print("Plastic (%): ");
        double plasticBodyPercentage = scanner.nextDouble();

        System.out.println();

        // TODO: Create the Drone.
        Battery battery = new Battery(maxBattery);
        Drone drone = new Drone(battery, numFilters);

        // TODO: Create the WaterBody.
        WaterBody waterBody = new WaterBody(waterBodySize, plasticBodyPercentage);

        // TODO: Clean the body of water.
        drone.clean(waterBody,battery);

        scanner.close();

    }

}
