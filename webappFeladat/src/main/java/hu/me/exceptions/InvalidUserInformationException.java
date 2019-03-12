package hu.me.exceptions;

public class InvalidUserInformationException extends Exception {
    public InvalidUserInformationException() {
    }

    public InvalidUserInformationException(String s) {
        super(s);
    }

    public InvalidUserInformationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidUserInformationException(Throwable throwable) {
        super(throwable);
    }

    public InvalidUserInformationException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}