package hu.me;

import hu.me.exceptions.DivisionByZeroException;
import hu.me.exceptions.InvalidProcedureException;

public class Szerviz {

    public double szamol(Input input) throws DivisionByZeroException, InvalidProcedureException {
        Szamologep szamologep = new Szamologep();
        String eredmeny;

        if (input.getMuvelet().equals("osszead")) {
            int[] operandusok = input.getOperandusok();
            eredmeny = szamologep.osszeadas(operandusok[0], operandusok[1]) + "";
            return Double.parseDouble(eredmeny);
        }
        else if (input.getMuvelet().equals("kivon")) {
            int[] operandusok = input.getOperandusok();
            eredmeny = szamologep.kivonas(operandusok[0], operandusok[1]) + "";
            return Double.parseDouble(eredmeny);
        }
        else if (input.getMuvelet().equals("szoroz")) {
            int[] operandusok = input.getOperandusok();
            eredmeny = szamologep.szorzas(operandusok[0], operandusok[1]) + "";
            return Double.parseDouble(eredmeny);
        }
        else if (input.getMuvelet().equals("osztas")) {
            int[] operandusok = input.getOperandusok();
            eredmeny = szamologep.osztas(operandusok[0], operandusok[1]) + "";
            return Double.parseDouble(eredmeny);
        }
        else throw new InvalidProcedureException();
    }
}