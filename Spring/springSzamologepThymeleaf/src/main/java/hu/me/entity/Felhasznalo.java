package hu.me.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Felhasznalo {
    @Id
    private int userID;
    private String userName;
    private int userAge;

    public Felhasznalo() {
    }

    public Felhasznalo(int userID, String userName, int userAge) {
        this.userID = userID;
        this.userName = userName;
        this.userAge = userAge;
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
}
