package week4.Ocean_Drones;

/**
 * A class for representing a FLOAT-E Drone.
 *
 * @author TODO: Fill out your name here
 */
public class Drone {

    private int configFilters;
    Battery battery;

    // TODO: Declare some constructors here.
    public Drone (Battery battery, int configFilters) {
        setBattery(battery);
        setConfigFilters(configFilters);
    }


    /**
     * Triggers the drone to start cleaning the body of water.
     * Cleaning continues until the drone is low on battery or the water body is cleaned.
     * Output is printed by this method to show the status of the clean.
     *
     * @param waterBody the body of water to be cleaned by the drone
     */
    public void clean(WaterBody waterBody, Battery battery) {

        // TODO: Implement the output with correct values.
        System.out.println("Clean initiated for a drone with " + configFilters +
                " filters, and battery with charge of " + battery.getCurrentBatteryCharge() + " units.");
        System.out.println("Water body has a surface size of " + waterBody.getWaterBodySize() + " units.");
        System.out.println();

        int numCleanStepsTaken = 0;
        String batteryString = "";
        String waterBodyString = "";

        // Keep cleaning the water while we have battery, and the water is not yet clean.
        while(!battery.isLow() && !waterBody.isClean()) { // TODO: Implement a correct guard here.
            batteryString = String.format("%.1f", battery.getBatteryPercentage());
            waterBodyString = String.format("%.3f", waterBody.getPlasticBodySize() / waterBody.getWaterBodySize());

//            System.out.println("Battery: " + battery.getBatteryPercentage() +
//                                "% | Plastic: " + waterBody.getPlasticBodyPercentage());
            System.out.println("Battery: " + batteryString + "% | Plastic: " + waterBodyString);

            // TODO: Each cleaning step drains some battery.
            battery.drain(configFilters);

            // TODO: Clean the water body once per filter.
            for (int i = 0; i < configFilters; i++) {
                waterBody.clean();
            }

            numCleanStepsTaken++;
        }
        batteryString = String.format("%.1f", battery.getBatteryPercentage());
        waterBodyString = String.format("%.3f", waterBody.getPlasticBodySize() / waterBody.getWaterBodySize());
        System.out.println("Battery: " + batteryString + "% | Plastic: " + waterBodyString);

        // TODO: Implement the correct output.
        System.out.println();
        System.out.println("Cleaning stopped after " + numCleanStepsTaken + " steps.");
        if (battery.isLow()){
            System.out.println("Reason: Battery was low.");
        }else {
            System.out.println("Reason: Cleaning complete.");
        }


    }

    public double getBattery(Battery battery) {
        return battery.getCurrentBatteryCharge();
    }

    public void setBattery(Battery battery) {
        // TODO: Declare some instance variables here.
        double configBattery = battery.getMaxBatteryCharge();
    }

    public int getConfigFilters() {
        return configFilters;
    }

    public void setConfigFilters(int configFilters) {
        this.configFilters = configFilters;
    }

}
