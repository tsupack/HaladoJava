package hu.me.service;

import hu.me.controller.dto.Input;

import hu.me.exceptions.DivisionByZeroException;
import hu.me.service.exceptions.NullOperatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Component
public class InputValidator implements Validator {
    private Szerviz szerviz;
    private List<String> muveletek = new ArrayList<>();
    private String bemenet;

    @Autowired
    public void setSzerviz (Szerviz szerviz){
        this.szerviz = szerviz;
    }

    @Override
    public boolean supports(Class Input){
        return Input.class.equals(Input);
    }

    @Override
    public void validate(Object object, Errors errors) {
        //Ha üres bemenet van bármelyik paraméterre, akkor dobjon hibát.
        ValidationUtils.rejectIfEmpty(errors, "muvelet", "input.muvelet.ures");
        ValidationUtils.rejectIfEmpty(errors, "a", "input.elsoOperandus.ures");
        ValidationUtils.rejectIfEmpty(errors, "b", "input.masodikOperandus.ures");

        Input input = (Input) object;

        //Ha osztás művelet van és a második operandus 0, akkor dobjon hibát.
        if (("oszt").equals(input.getMuvelet()) && input.getB() == 0){
            errors.rejectValue("b", "input.nullavalValoOsztas");
        } else {
            //Ha már volt ilyen művelet, akkor dobon hibát.
            //Nem optimális, mert újraszámolja hozzá az eredményt, de ha van adatbázis,
            //akkor ennek hatására vissza lehet töltetni a korábbi eredményt.
            //Jelen megoldásban csak a logot kell átírni hozzá, hogy ne számoljon hozzá újra semmit.
            muveletek = szerviz.getLog();

            try {
                bemenet = ("{" + input.getA() + " " + input.getMuvelet() + " " + input.getB() + " = " + szerviz.szamol(input) + "}");
            } catch (DivisionByZeroException e) { } catch (NullOperatorException e) { }

            for (String muvelet : muveletek) {
                System.out.println(muvelet);

                if( bemenet.equals(muvelet)){
                    errors.rejectValue("muvelet", "input.muvelet.ismetles");
                    errors.rejectValue("a", "input.muvelet.ismetles");
                    errors.rejectValue("b", "input.muvelet.ismetles");
                }
            }
        }
    }
}
