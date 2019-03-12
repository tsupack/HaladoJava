package hu.me;

import hu.me.exceptions.InvalidUserInformationException;
import hu.me.exceptions.LengthChecker;

import java.util.ArrayList;

public class Controller {
    private User user = new User();

    public void setCheckers(ArrayList<Checker> checkers) {
        this.checkers = checkers;
    }

    private ArrayList<Checker> checkers;



    public User check (String username, String password) throws InvalidUserInformationException{
        for(int i = 0; i < checkers.size(); i++)
        {
            checkers
        }

        if(!(noSpaceChecker.valid(username) && lengthChecker.valid(username))){
            throw new InvalidUserInformationException();
        }
        else if (!(noSpaceChecker.valid(password) && lengthChecker.valid(password))){
            throw new InvalidUserInformationException();
        }
        else{
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
    }
}
