package hu.me;

import hu.me.exceptions.DivisionByZeroException;
import org.springframework.stereotype.Service;

@Service
public class Szerviz {
    private SzamologepInterface szamologepInterface;

    public Szerviz(SzamologepInterface szamologepInterface){
        this.szamologepInterface = szamologepInterface;
    }

    public Output szamol(Input input) throws NullPointerException {
        Output output = new Output();

        try {
            if (input.getMuvelet() == null && input.getOperandusok() == null || input.getMuvelet().isEmpty() && input.getOperandusok() == null) {
                output.setUzenet("Ervenytelen bemeneti parameterek!");
                output.setHibakod(Hibakod.ERVENYTELENBEMENET);
                return output;
            }

            if (input.getOperandusok() == null) {
                output.setUzenet("Ervenytelen operandusok!");
                output.setHibakod(Hibakod.ERVENYTELENOPREANDUS);
                return output;
            }

            if (input.getMuvelet() == null || input.getMuvelet().isEmpty()) {
                output.setUzenet("Ervenytelen bemeneti muvelet!");
                output.setHibakod(Hibakod.ERVENYTELENMUVELET);
                return output;
            } else if ("osszead".equals(input.getMuvelet())) {
                output.setEredmeny(szamologepInterface.osszeadas(input.getOperandus1(), input.getOperandus2()));
                output.setUzenet("Nincs hiba.");
                output.setHibakod(Hibakod.ERVENYES);
                return output;
            } else if ("kivon".equals(input.getMuvelet())) {
                output.setEredmeny(szamologepInterface.kivonas(input.getOperandus1(), input.getOperandus2()));
                output.setUzenet("Nincs hiba.");
                output.setHibakod(Hibakod.ERVENYES);
                return output;
            } else if ("szoroz".equals(input.getMuvelet())) {
                output.setEredmeny(szamologepInterface.szorzas(input.getOperandus1(), input.getOperandus2()));
                output.setUzenet("Nincs hiba.");
                output.setHibakod(Hibakod.ERVENYES);
                return output;
            } else if ("oszt".equals(input.getMuvelet())) {
                try {
                    if (input.getOperandus2() == 0) throw new DivisionByZeroException();
                    output.setEredmeny(szamologepInterface.osztas(input.getOperandus1(), input.getOperandus2()));
                } catch (DivisionByZeroException e) {
                    output.setHibakod(Hibakod.NULLAVALVALOOSZTAS);
                    output.setUzenet("Nem lehet 0-val osztani!");
                    return output;
                }
                output.setUzenet("Nincs hiba.");
                output.setHibakod(Hibakod.ERVENYES);
                return output;
            } else {
                output.setUzenet("Ervenytelen bemeneti muvelet!");
                output.setHibakod(Hibakod.ERVENYTELENMUVELET);
                return output;
            }
        }catch (NullPointerException e){
            output.setUzenet("Ervenytelen bemeneti parameterek!");
            output.setHibakod(Hibakod.ERVENYTELENBEMENET);
            return output;
        }
    }
}