package week4.Ocean_Drones;

/**
 * A class for representing a body of water that is to be cleaned.
 *
 * @author TODO: Fill out your name here
 */
public class WaterBody {

    // TODO: Declare some instance variables here.
    private double waterBodySize;
    private double plasticBodySize;


    // TODO: Declare some constructors here.
    public WaterBody(double waterBodySize, double plasticBodyPercentage) {
        setWaterBodySize(waterBodySize);
        setPlasticBodySize(plasticBodyPercentage * waterBodySize / 100);
    }


    /**
     * Takes a step to clean the body of water, for a single filter.
     */
    public void clean() {
        // TODO: Implement this method.
        double decreasedPlastic = 0.5 + (plasticBodySize / waterBodySize);
        plasticBodySize -= decreasedPlastic;
    }

    /**
     * Returns whether or not the body of water is now considered clean.
     * A body of water is considered clean, when the plastic covers
     * less than 0.05 of the water surface.
     * @return true if the water body is clean, false otherwise
     */
    public boolean isClean() {

        // TODO: Optionally implement this method, or delete it.
        if (plasticBodySize / waterBodySize >= 0.05) {
            return false;
        }else {
            return true;
        }
    }

    // TODO: Declare some getters and setters here.

    public double getWaterBodySize() {
        return waterBodySize;
    }

    public void setWaterBodySize(double waterBodySize) {
        this.waterBodySize = waterBodySize;
    }

    public double getPlasticBodySize() {
        return plasticBodySize;
    }

    public void setPlasticBodySize(double plasticBodySize) {
        this.plasticBodySize = plasticBodySize;
    }


}
