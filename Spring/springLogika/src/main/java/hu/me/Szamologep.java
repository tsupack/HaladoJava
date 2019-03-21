package hu.me;

import hu.me.exceptions.DivisionByZeroException;
import org.springframework.stereotype.Service;

@Service
public class Szamologep implements SzamologepInterface {
    public double osszeadas(double a, double b){
        return a + b;
    }

    public double kivonas(double a, double b){
        return a - b;
    }

    public double szorzas(double a, double b){
        return a * b;
    }

    public double osztas(double a, double b) throws DivisionByZeroException {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        return a / b;
    }
}
