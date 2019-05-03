package hu.me.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Szamolas {
    @Id
    @GeneratedValue
    private int id;
    private double a;
    private double b;
    private String muvelet;
    private double eredmeny;
    private int userID;

    public Szamolas(){}

    public Szamolas(double a, double b, String muvelet, double eredmeny, int userID) {
        this.a = a;
        this.b = b;
        this.muvelet = muvelet;
        this.eredmeny = eredmeny;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public String getMuvelet() {
        return muvelet;
    }

    public void setMuvelet(String muvelet) {
        this.muvelet = muvelet;
    }

    public double getEredmeny() {
        return eredmeny;
    }

    public void setEredmeny(double eredmeny) {
        this.eredmeny = eredmeny;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}