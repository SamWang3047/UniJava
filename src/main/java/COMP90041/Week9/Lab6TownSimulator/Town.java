package COMP90041.Week9.Lab6TownSimulator;

import java.util.ArrayList;

/**
 * A class representing a town. Entities can be added to the town for
 * this class to manage.
 * @author TODO: Write your name here
 *
 */
public class Town {

	// TODO: Add appropriate instance variables here.
	private String name;
	private ArrayList<Entity> entities;

	// TODO: Add appropriate constructors.


	public Town(String name) {
		this.name = name;
		this.entities = new ArrayList<>();
	}

	/**
	 * Adds an entity to the entities in this town.
	 * @param entity  the entity to add to the town
	 */
	public void add(Entity entity) {
		
		// TODO: Implement this method.
		entities.add(entity);
	}

	/**
	 * Prints out a description of the town and entities.
	 */
	public void displayDescription() {
		
		// TODO: Implement this method.
		System.out.println("The town '" + name + "' has " + entities.size() + " entities in it.");
		for (Entity entity : entities) {
			System.out.println("It has... a " + entity.describe());
		}
	}
	
	/**
	 * Prints out the result of interacting with every object inside the town.
	 */
	public void displayInteraction() {
		
		// TODO: Implement this method.
		for (Entity entity : entities) {
			System.out.println("You tried interacting with the " + entity.describe() + "... " + entity.interact());
		}
	}
	
}

