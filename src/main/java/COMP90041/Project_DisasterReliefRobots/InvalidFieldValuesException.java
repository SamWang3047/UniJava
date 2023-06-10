package COMP90041.Project_DisasterReliefRobots;

public class InvalidFieldValuesException extends Exception{
    public InvalidFieldValuesException() {
        super("invalid characteristic");
    }

    public InvalidFieldValuesException(String message) {
        super(message);
    }

}
