package hu.me;

public class NoSpaceChecker implements Checker {
    @Override
    public boolean valid(String bemenet) {
        if (bemenet.contains(" ")){
            return false;
        }
        else {
            return true;
        }
    }
}
