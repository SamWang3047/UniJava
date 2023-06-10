package COMP90041.Week10.Lab7Coin;

/**
 * An exception that is thrown when the coin file we are working with has
 * an invalid format.
 * @author TODO: Write your name here.
 *
 */
public class InvalidCoinFileException extends Exception {

	// TODO: Implement this class.
	public InvalidCoinFileException() {
        super("Invalid coin sequence!");
    }
    public InvalidCoinFileException(String message) {
        super(message);
    }

}

