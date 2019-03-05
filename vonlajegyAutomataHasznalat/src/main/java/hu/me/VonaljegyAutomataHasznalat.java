package hu.me;

import hu.me.exceptions.NoMoneyInputException;
import hu.me.exceptions.NotEnoughMoneyException;

public class VonaljegyAutomataHasznalat {
    private VonaljegyAutomataInterface vonaljegyAutomataInterface;

    public VonaljegyAutomataHasznalat(VonaljegyAutomataInterface vonaljegyAutomataInterface){
        this.vonaljegyAutomataInterface = vonaljegyAutomataInterface;
    }

    public Vonaljegy vetel (Vasarlas vasarlas) throws NoMoneyInputException, NotEnoughMoneyException {
        Vonaljegy vonaljegy = new Vonaljegy();

        if(vasarlas == null || vasarlas.getDb() == 0 || vasarlas.getFizetes() == 0){
            vonaljegy.setKiadva(false);
            return vonaljegy;
        }

        if(!vonaljegyAutomataInterface.penzBetolt(vasarlas.getFizetes())){
            try {
                throw new NoMoneyInputException();
            } catch (NoMoneyInputException e){
                vonaljegy.setKiadva(false);
                return vonaljegy;
            }
        }

        if(vasarlas.getDb() == 0){
            vonaljegy.setKiadva(false);
            return vonaljegy;
        } else {
            vonaljegy.setDb(vasarlas.getDb());
        }

        try {
            vonaljegy.setAr(vasarlas.getFizetes());
        } catch (NotEnoughMoneyException e){
            System.out.println("Nem eleg penz!");
            vonaljegy.setKiadva(false);
            return vonaljegy;
        }

        vonaljegy.setKiadva(true);
        return vonaljegy;
    }
}