package Modele.Exception;

public class InvalidGameException extends Exception {
    public InvalidGameException() {
        super();
    }

    public InvalidGameException(String error_msg) {
        super(error_msg);
    }

    public InvalidGameException(String error_msg, Throwable throwable) {
        super(error_msg, throwable);
    }
}
