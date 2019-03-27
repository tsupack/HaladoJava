package hu.me.service.exceptions;

public class NullOperatorException extends Exception {

    public NullOperatorException() {
    }

    public NullOperatorException(String s) {
        super(s);
    }

    public NullOperatorException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NullOperatorException(Throwable throwable) {
        super(throwable);
    }

    public NullOperatorException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}