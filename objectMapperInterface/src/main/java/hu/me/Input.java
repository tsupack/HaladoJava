package hu.me;

public class Input {
    private String muvelet;
    private double[] operandusok = new double[2];

    public String getMuvelet() {
        this.muvelet = muvelet.toLowerCase();
        return muvelet;
    }

    public void setMuvelet(String muvelet){
        this.muvelet = muvelet;
    }

    public double getOperandus1() {
        return operandusok[0];
    }

    public double getOperandus2() {
        return operandusok[1];
    }

    public void setOperandusok(double[] operandusok) {
        this.operandusok = operandusok;
    }

    public double[] getOperandusok() {
        return operandusok;
    }
}
