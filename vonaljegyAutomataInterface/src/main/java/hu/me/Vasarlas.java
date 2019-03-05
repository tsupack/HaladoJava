package hu.me;

import hu.me.exceptions.NotEnoughMoneyException;

public class Vasarlas {
    private int db;
    private int fizetes;
    private int visszajaro;

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public int getFizetes() {
        return fizetes;
    }

    public void setFizetes(int fizetes) {
        this.fizetes = fizetes;
    }

    public int getVisszajaro()  throws NotEnoughMoneyException{
        visszajaro = visszajaroSzamol();
        return visszajaro;
    }

    public void setVisszajaro() throws NotEnoughMoneyException{
        visszajaro = visszajaroSzamol();
    }

    public int visszajaroSzamol() throws NotEnoughMoneyException{
        int fizetendo = db*400;
        if((fizetes - fizetendo)>0){
            return fizetes-fizetendo;
        }
        else if((fizetes-fizetendo) == 0){
            return fizetes-fizetendo;
        }
        else {
            throw new NotEnoughMoneyException();
        }
    }
}
