package COMP90041.Project_DisasterReliefRobots;

public class InvalidDataFormatException extends Exception{
    public InvalidDataFormatException() {
        super("invalid data format");
    }
    public InvalidDataFormatException(String message) {
        super(message);
    }
}
