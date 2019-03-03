package hu.me.exceptions;

public class InvalidProcedureException extends Exception {
    public InvalidProcedureException() {
    }

    public InvalidProcedureException(String s) {
        super(s);
    }

    public InvalidProcedureException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidProcedureException(Throwable throwable) {
        super(throwable);
    }

    public InvalidProcedureException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}