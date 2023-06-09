package COMP90041.Week9.Lab6TownSimulator;

/**
 * A class for representing an entity in a town.
 * Base class for every entity inside a town.
 * 
 * @author Dengke Sha
 */
public abstract class Entity {

	/**
	 * Return a description of this entity.
	 * @return a string describing this entity
	 */
	public abstract String describe();
	
	/**
	 * Return a description of interaction with this entity.
	 * @return a string describing the result of interacting with this entity
	 */
	public String interact() {
		return "(nothing happened)";
	}
}

