package hu.me.exceptions;

public class NoMoneyInputException extends Exception{
    public NoMoneyInputException() {
    }

    public NoMoneyInputException(String message) {
        super(message);
    }

    public NoMoneyInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoneyInputException(Throwable cause) {
        super(cause);
    }

    public NoMoneyInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}