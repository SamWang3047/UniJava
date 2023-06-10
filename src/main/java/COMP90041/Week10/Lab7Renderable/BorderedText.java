package COMP90041.Week10.Lab7Renderable;

/**
 * A renderable bordered text.
 * @author TODO: Write your name here.
 *
 */
public class BorderedText implements Renderable{
	private String str;
	// TODO: Implement this class.
	public BorderedText(String str) {
		super();
		this.str = str;
	}


	@Override
	public void render() {
		System.out.print("+");
		for (int i = 0; i < str.length(); i++) {
			System.out.print("-");
		}
		System.out.println("+");
		System.out.println("|" + str + "|");
		System.out.print("+");
		for (int i = 0; i < str.length(); i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}
}
