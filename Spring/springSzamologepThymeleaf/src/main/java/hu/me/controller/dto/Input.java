package hu.me.controller.dto;

import javax.validation.constraints.NotNull;

public class Input {
    @NotNull
    private String muvelet;
    @NotNull
    private Double a;
    @NotNull
    private Double b;
    @NotNull
    private int userID;
    @NotNull
    private String userName;
    @NotNull
    private int userAge;


    public Input (String muvelet, double a, double b, int userID, String userName, int userAge){
        this.muvelet = muvelet;
        this.a = a;
        this.b = b;
        this.userID = userID;
        this.userName = userName;
        this.userAge = userAge;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "Input{" +
                "muvelet=" + muvelet +
                ", operandus a=" + a +
                ", operandus b=" + b +
                ", azonosito=" + userID +
                ", nev=" + userName +
                ", eletkor=" + userAge +
                '}';
    }
}
