package COMP90041.Week9.Lab6TownSimulator;

/**
 * A class representing a building in the town.
 * @author TODO: Write your name here
 */
public class Building extends Entity {
	
	// TODO: Implement this class.
	private String color;
	private int height;

	public Building(String color, int height) {
		this.color = color;
		this.height = height;
	}

	@Override
	public String describe() {
		// TODO: Implement this method.
		return color + " building with " + height + " floor(s)";
	}

}

