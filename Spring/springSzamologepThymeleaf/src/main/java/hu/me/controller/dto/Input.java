package hu.me.controller.dto;

import javax.validation.constraints.NotNull;

public class Input {
    private String muvelet;
    @NotNull
    private Double a;
    @NotNull
    private Double b;

    public Input (String muvelet, double a, double b){
        this.muvelet = muvelet;
        this.a = a;
        this.b = b;
    }

    public String getMuvelet() {
        this.muvelet = muvelet.toLowerCase();
        return muvelet;
    }

    public void setMuvelet(String muvelet){
        this.muvelet = muvelet;
    }

    public void setA(Double a){
        this.a = a;
    }

    public Double getA() {
        return a;
    }

    public void setB(Double b){
        this.b = b;
    }

    public Double getB() {
        return b;
    }

    @Override
    public String toString() {
        return "Input{" +
                "muvelet=" + muvelet +
                ", operandus a=" + a +
                ", operandus b='" + b +
                '}';
    }
}
