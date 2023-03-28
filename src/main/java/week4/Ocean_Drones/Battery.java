package week4.Ocean_Drones;

/**
 * A class for representing a battery used to power FLOAT-E drones.
 *
 * @author TODO: Fill out your name here
 */
public class Battery {
    private static final double BATTERY_COEEFICENT = 0.2;
    private static final int BATTERY_PERCENTAGE = 100;
    // TODO: Declare some instance variables here.
    private double maxBatteryCharge;
    private double currentBatteryCharge;
    private double batteryPercentage;



    // TODO: Declare some constructors here.
    public Battery (double maxBatteryCharge) {
        setMaxBatteryCharge(maxBatteryCharge);
        setCurrentBatteryCharge(maxBatteryCharge);
        setBatteryPercentage(BATTERY_PERCENTAGE);
    }


    /**
     * Drain the battery based on the number of filters the battery is powering.
     * @param numberOfFilters the number of filters this battery is powering on the drone
     */
    public void drain(int numberOfFilters) {

        // TODO: Implement this method.
        double decreasedBattery = BATTERY_COEEFICENT * numberOfFilters;

        double batteryTemp = currentBatteryCharge -  decreasedBattery;
        double percentTemp = decreasedBattery / getMaxBatteryCharge() * 100;

        currentBatteryCharge = batteryTemp;
        batteryPercentage -= percentTemp;
    }

    /**
     * Returns whether or not the battery is low.
     * The battery is considered to be low, when the current charge is less than
     * 5% of the maximum charge.
     * @return true if battery is low, false otherwise.
     */
    public boolean isLow() {
        // TODO: Optionally implement this method, or delete it.
        if (batteryPercentage >= 5) {
            return false;
        } else {
            return true;
        }
    }


    // TODO: Declare some getters and setters here.
    public double getMaxBatteryCharge() {
        return maxBatteryCharge;
    }

    public void setMaxBatteryCharge(double maxBatteryCharge) {
        this.maxBatteryCharge = maxBatteryCharge;
    }

    public double getCurrentBatteryCharge() {
        return currentBatteryCharge;
    }

    public void setCurrentBatteryCharge(double currentBatteryCharge) {
        this.currentBatteryCharge = currentBatteryCharge;
    }
    public double getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(double batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }
}
