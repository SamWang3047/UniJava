package COMP90041.Week10.Lab7Renderable;

/**
 * An renderable AsciiCar.
 * @author Dengke Sha
 *
 */
public class AsciiCar implements Renderable {

	@Override
	public void render() {

		String car = "      ____        \n" + 
				" ____//_]|________\n" + 
				"(o _ |  -|   _  o|\n" + 
				" `(_)-------(_)--'";
		
		System.out.println(car);
		
	}

}
