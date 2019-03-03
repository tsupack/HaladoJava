package hu.me;

import hu.me.exceptions.DivisionByZeroException;
import hu.me.exceptions.InvalidProcedureException;

import java.io.IOException;

public class Futtathato {
    public static void main(String[] args) {

        Marshaller marshaller = new Marshaller();
        Szerviz szerviz = new Szerviz();

        try {
            marshaller.keszitsInputJsont("JsonInput", "osszead", 2, 3);
            marshaller.keszitsInputYamlt("YamlInput", "kivon", 10, 7);
            double jsonEredmeny = szerviz.szamol(marshaller.unMarshalJson("JsonInput.json"));
            double yamlEredmeny = szerviz.szamol(marshaller.unMarshalYaml("YamlInput.yaml"));
            marshaller.marshalJson(jsonEredmeny, "Minden rendben!", 100);
            marshaller.marshalYaml(yamlEredmeny, "Minden rendben!", 100);
        } catch (InvalidProcedureException | DivisionByZeroException | IOException e) {
            e.printStackTrace();
        }
    }
}