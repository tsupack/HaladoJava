package hu.me.utils.implementation;

import hu.me.core.User;
import hu.me.utils.Checker;

public class LengthChecker implements Checker {
    @Override
    public boolean valid(User user) {
        if(user.getUsername().length() <6 || user.getPassword().length() < 6){
            return false;
        }
        else {
            return true;
        }
    }
}
