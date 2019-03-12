package hu.me.exceptions;

import hu.me.Checker;

public class LengthChecker implements Checker {
    @Override
    public boolean valid(String bemenet) {
        if(bemenet.length()<6){
            return false;
        }
        else {
            return true;
        }
    }
}
