package hu.me;

import hu.me.exceptions.InvalidProcedureException;

public class Input {
    private String muvelet;
    private int[] operandusok;

    public String getMuvelet() {
        this.muvelet = muvelet.toLowerCase();
        return muvelet;
    }

    public void setMuvelet(String muvelet) throws InvalidProcedureException {
        if (!(muvelet.toLowerCase().equals("osszead") || muvelet.toLowerCase().equals("kivon") || muvelet.toLowerCase().equals("szoroz") || muvelet.toLowerCase().equals("oszt")))
            throw new InvalidProcedureException();
        this.muvelet = muvelet.toLowerCase();
    }

    public int[] getOperandusok() {
        return operandusok;
    }

    public void setOperandusok(int[] operandusok) {
        this.operandusok = operandusok;
    }
}
