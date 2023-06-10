package COMP90041.Week10.Lab7Renderable;

import java.util.ArrayList;

/**
 * A program that renders things.
 * @author TODO: Write your name here.
 *
 */
public class Render {

	public static void main(String[] args) {

		// A collection for managing all of the renderable items together.
		ArrayList<Renderable> renderableItems = new ArrayList<Renderable>();
		
		renderableItems.add(new Square(2));
		renderableItems.add(new AsciiCar());
		renderableItems.add(new Square(4));
		
		// TODO: Add some of your own renderable objects here.
		renderableItems.add(new BorderedText("Hello"));
		renderableItems.add(new Puppy());
		
		// Render all renderable items.
		for(Renderable renderable : renderableItems) {
			renderable.render();
			System.out.println();
		}

	}

}
