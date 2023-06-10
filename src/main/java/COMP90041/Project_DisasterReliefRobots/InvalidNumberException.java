package COMP90041.Project_DisasterReliefRobots;

public class InvalidNumberException extends NumberFormatException{
    public InvalidNumberException() {
        super("invalid number format");
    }

    public InvalidNumberException(String message) {
        super(message);
    }
}
