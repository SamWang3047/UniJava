package COMP90041.Project_DisasterReliefRobots;

public class AttributeSurvivalRatio implements Comparable<AttributeSurvivalRatio> {
    private String attributeName;
    private double survivalRatio;

    public AttributeSurvivalRatio(String attributeName, double survivalRatio) {
        this.attributeName = attributeName;
        this.survivalRatio = survivalRatio;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public double getSurvivalRatio() {
        return survivalRatio;
    }

    @Override
    public int compareTo(AttributeSurvivalRatio o) {
        if (this.survivalRatio != o.survivalRatio) {
            return Double.compare(o.survivalRatio, this.survivalRatio);
        } else {
            return this.attributeName.compareTo(o.attributeName);
        }
    }
}
