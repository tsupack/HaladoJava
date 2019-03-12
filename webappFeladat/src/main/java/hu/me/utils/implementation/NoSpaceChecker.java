package hu.me.utils.implementation;

import hu.me.core.User;
import hu.me.utils.Checker;

public class NoSpaceChecker implements Checker {
    @Override
    public boolean valid(User user) {
        if (user.getUsername().contains(" ") || user.getPassword().contains(" ")){
            return false;
        }
        else {
            return true;
        }
    }
}
