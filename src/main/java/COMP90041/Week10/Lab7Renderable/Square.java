package COMP90041.Week10.Lab7Renderable;

/**
 * An renderable square shape.
 * @author Dengke Sha
 *
 */
public class Square implements Renderable {

	private int width;

	/**
	 * Create a square.
	 * @param width  the width (and length) of the square
	 */
	public Square(int width) {
		super();
		this.width = width;
	}

	@Override
	public void render() {

		for(int i=0; i < width; i++) {
			for(int j=0; j < width; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}

}
