package hu.me;

import hu.me.exceptions.DivisionByZeroException;

public interface SzamologepInterface {
    public double osszeadas(double a, double b);

    public double kivonas(double a, double b);

    public double szorzas(double a, double b);

    public double osztas(double a, double b) throws DivisionByZeroException;
}
