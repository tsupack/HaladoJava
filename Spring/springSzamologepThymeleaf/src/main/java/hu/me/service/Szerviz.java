package hu.me.service;

import hu.me.controller.dto.Input;
import hu.me.SzamologepInterface;
import hu.me.entity.Felhasznalo;
import hu.me.entity.Szamolas;
import hu.me.repository.FelhasznaloRepository;
import hu.me.repository.SzamolasRepository;
import hu.me.exceptions.DivisionByZeroException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Szerviz {
    private SzamologepInterface szamologepInterface;
    private List<String> log = new ArrayList<>();
    private SzamolasRepository szamolasRepository;
    private FelhasznaloRepository felhasznaloRepository;

    @Autowired
    public void setSzamolasRepository(SzamolasRepository szamolasRepository){
        this.szamolasRepository = szamolasRepository;
    }

    @Autowired
    public void setFelhasznaloRepository(FelhasznaloRepository felhasznaloRepository){
        this.felhasznaloRepository = felhasznaloRepository;
    }

    @Autowired
    public Szerviz(SzamologepInterface szamologepInterface){
        this.szamologepInterface = szamologepInterface;
    }

    public Double szamol(Input input, Boolean mentes) throws DivisionByZeroException {
        Double eredmeny = null;
        if ("osszead".equals(input.getMuvelet())) {
            eredmeny = szamologepInterface.osszeadas(input.getA(), input.getB());
        } else if ("kivon".equals(input.getMuvelet())) {
            eredmeny = szamologepInterface.kivonas(input.getA(), input.getB());
        } else if ("szoroz".equals(input.getMuvelet())) {
            eredmeny = szamologepInterface.szorzas(input.getA(), input.getB());
        } else if ("oszt".equals(input.getMuvelet())) {
            if (input.getB() == 0) throw new DivisionByZeroException();
            eredmeny = szamologepInterface.osztas(input.getA(), input.getB());
        }
        if (mentes) {
            szamolasRepository.save(new Szamolas(input.getA(), input.getB(), input.getMuvelet(), eredmeny, input.getUserID()));//Mentés adatbázisba
            felhasznaloRepository.save(new Felhasznalo(input.getUserID(), input.getUserName(), input.getUserAge()));
            return eredmeny;
        } else {
            return eredmeny;
        }
    }

    public List<String> log(String s) {
        log.add(s);
        return log;
    }

    //Alap logoláshoz
    public List<String> getLog() {
        return log;
    }

    //Adatbázisból csak a felhasználók lekérése
    public Iterable<Felhasznalo> getFelhasznalok() {
        return felhasznaloRepository.findAll();
    }

    //Adatbázisból csak a számolások lekérése
    public Iterable<Szamolas> getSzamolasok() {
        return szamolasRepository.findAll();
    }

    //Adatbázisból csak a kiválasztott műveletnek megfelelő számolások lekérése
    public Iterable<Szamolas> getByMuvelet(String muvelet) {
        return szamolasRepository.findByMuvelet(muvelet);
    }
}