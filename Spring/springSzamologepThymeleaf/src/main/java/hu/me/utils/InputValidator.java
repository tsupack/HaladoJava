package hu.me.utils;

import hu.me.controller.dto.Input;
import hu.me.entity.Felhasznalo;
import hu.me.exceptions.DivisionByZeroException;

import hu.me.service.Szerviz;
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
        ValidationUtils.rejectIfEmpty(errors, "userID", "input.azonosito.ures");
        ValidationUtils.rejectIfEmpty(errors, "userName", "input.nev.ures");
        ValidationUtils.rejectIfEmpty(errors, "userAge", "input.eletkor.ures");

        Input input = (Input) object;

        //Ha nem írták át az alapértékeket, akkor dobjon hibát.
        if(input.getUserID() == 0 || ("").equals(input.getUserName())){
            errors.rejectValue("userID", "input.bemenet.alap");
            errors.rejectValue("userName", "input.bemenet.alap");
        }

        Iterable<Felhasznalo> lekeres = szerviz.getFelhasznalok();

        //Ha már a kapott ID-vel van felhasználó, de a többi adat nem egyezik, akkor dobjon hibát.
        for (Felhasznalo felhasznalo : lekeres) {
            if(input.getUserID() == felhasznalo.getUserID()){
                if(!(input.getUserName().equals(felhasznalo.getUserName()))){
                    errors.rejectValue("userID", "input.bemenet.letezik");
                } else {
                    break;
                }
            }
        }

        //Ha osztás művelet van és a második operandus 0, akkor dobjon hibát.
        if (("oszt").equals(input.getMuvelet()) && input.getB() == 0){
            errors.rejectValue("b", "input.nullavalValoOsztas");
        } else {
            //Ha már volt ilyen művelet, akkor dobon hibát.
            //Nem optimális, mert újraszámolja hozzá az eredményt, de ha van adatbázis,
            //akkor ennek hatására vissza lehet töltetni a korábbi eredményt.
            //Jelen megoldásban csak a logot kell átírni hozzá, hogy ne számoljon hozzá újra semmit.
            muveletek = szerviz.getLog();
            try{
                bemenet = ("{" + input.getA() + " " + input.getMuvelet() + " " + input.getB() + " = " + szerviz.szamol(input, false) + " , " + input.getUserID() + " , " + input.getUserName() + " , " + input.getUserAge() + "}");
            } catch (DivisionByZeroException e) { }

            for (String muvelet : muveletek) {
                System.out.println(muvelet);

                if( bemenet.equals(muvelet)){
                    errors.rejectValue("muvelet", "input.muvelet.ismetles");
                    errors.rejectValue("a", "input.muvelet.ismetles");
                    errors.rejectValue("b", "input.muvelet.ismetles");
                    errors.rejectValue("userID", "input.muvelet.ismetles");
                    errors.rejectValue("userName", "input.muvelet.ismetles");
                    errors.rejectValue("userAge", "input.muvelet.ismetles");
                }
            }
        }
    }
}
