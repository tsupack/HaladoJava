package hu.me.service;

import hu.me.controller.dto.Input;
import hu.me.SzamologepInterface;
import hu.me.exceptions.DivisionByZeroException;
import hu.me.service.exceptions.NullOperatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Szerviz {
    private SzamologepInterface szamologepInterface;
    private List<String> log = new ArrayList<>();

    @Autowired
    public Szerviz(SzamologepInterface szamologepInterface){
        this.szamologepInterface = szamologepInterface;
    }

    public Double szamol(Input input) throws NullOperatorException, DivisionByZeroException {
        Double eredmeny = null;
        if (input.getMuvelet() == null || input.getMuvelet().isEmpty() || "".equals(input.getMuvelet())) {
            throw new NullOperatorException();
        } else if ("osszead".equals(input.getMuvelet())) {
            eredmeny = szamologepInterface.osszeadas(input.getA(), input.getB());
            return eredmeny;
        } else if ("kivon".equals(input.getMuvelet())) {
            eredmeny = szamologepInterface.kivonas(input.getA(), input.getB());
            return eredmeny;
        } else if ("szoroz".equals(input.getMuvelet())) {
            eredmeny = szamologepInterface.szorzas(input.getA(), input.getB());
            return eredmeny;
        } else if ("oszt".equals(input.getMuvelet())) {
            if (input.getB() == 0) throw new DivisionByZeroException();
            eredmeny = szamologepInterface.osztas(input.getA(), input.getB());
            return eredmeny;
        } else {
            return eredmeny;
        }
    }

    public List<String> log(String s) {
        log.add(s);
        return log;
    }

    public List<String> getLog() {
        return log;
    }
}