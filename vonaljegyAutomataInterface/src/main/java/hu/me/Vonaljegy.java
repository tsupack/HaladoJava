package hu.me;

import hu.me.exceptions.NotEnoughMoneyException;

public class Vonaljegy {
    private int db;
    private int ar;
    private boolean kiadva;

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) throws NotEnoughMoneyException {
        if(ar < (db*400)){
            throw new NotEnoughMoneyException();
        }
        this.ar = ar;
    }

    public boolean isKiadva() {
        return kiadva;
    }

    public void setKiadva(boolean kiadva) {
        this.kiadva = kiadva;
    }
}
