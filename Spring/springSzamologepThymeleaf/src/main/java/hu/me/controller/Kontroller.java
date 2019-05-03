package hu.me.controller;

import hu.me.controller.dto.Input;
import hu.me.entity.Felhasznalo;
import hu.me.entity.Szamolas;
import hu.me.utils.InputValidator;
import hu.me.service.Szerviz;
import hu.me.exceptions.DivisionByZeroException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Kontroller {
    private Szerviz szerviz;
    private InputValidator inputValidator;

    //Mutató arra, hogy milyen forrásban vannak az üzeneteim.
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    //Jelölés arra, hogy az általam létrehozott hibaüzeneteket használja.
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Autowired
    public void setInputValidator(InputValidator inputValidator){
        this.inputValidator = inputValidator;
    }

    @Autowired
    public void setSzerviz(Szerviz szerviz){
        this.szerviz = szerviz;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(inputValidator);
    }

    //Selecthez RequestMapping előtt meghívódó attribútum
    @ModelAttribute("operatorok")
    public List<String> operatorok() {
        List<String> operatorok = new ArrayList<>();
        operatorok.add("osszead");
        operatorok.add("kivon");
        operatorok.add("szoroz");
        operatorok.add("oszt");
        operatorok.add("osszes");
        return operatorok;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView initialize(){
        ModelAndView MAV = new ModelAndView();
        MAV.setViewName("index");
        MAV.addObject("input", new Input("osszead", 0, 0, 0, "",0));
        MAV.addObject("eredmeny", 0);
        MAV.addObject("log", "Üres");
        MAV.addObject("userData", "Üres");
        return MAV;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView input(@Validated Input input, BindingResult bindingResult){

        System.out.println(input);
        System.out.println(bindingResult);

        ModelAndView MAV = new ModelAndView();

        if (bindingResult.hasErrors()) {
            MAV.setViewName("index");
        } else {
            try {
                MAV.addObject("eredmeny", szerviz.szamol(input, true));
                szerviz.log("{" + input.getA() +
                        " " + input.getMuvelet() +
                        " " + input.getB() +
                        " = " + szerviz.szamol(input, false) +
                        " , " + input.getUserID() +
                        " , " + input.getUserName() +
                        " , " + input.getUserAge() +
                        "}"
                );

                MAV.addObject("log", szerviz.getLog());
                MAV.addObject("userData", szerviz.getUserData(input.getUserID()));
                //Itt csak az éppen belépett felhasználó összes számolását adjuk vissza.
                //Az ID fixen létezik, mert már validált és mentett is!
                MAV.setViewName("index");
            } catch (DivisionByZeroException e) {
                MAV.setViewName("index");
                return MAV;
            }
        }
        return MAV;
    }

    //Művelet szerinti számolás listázás
    @RequestMapping(method = RequestMethod.POST, path = "/listByOperator")
    @ResponseBody
    public Iterable<Szamolas> output(String muvelet){

        System.out.println(muvelet);

        if ("osszes".equals(muvelet)) {
            return szerviz.getSzamolasok();
        } else {
            return szerviz.getByMuvelet(muvelet);
        }
    }

    //Alap log listázása
    @RequestMapping(method = RequestMethod.GET, path = "/log")
    @ResponseBody//JSON-be húzza a választ
    public List<String> log() {
        return szerviz.getLog();
    }

    //Adatbázis szerinti számolás listázás
    @RequestMapping(method = RequestMethod.GET, path = "/logH2Calculations")
    @ResponseBody//JSON-be húzza a választ
    public Iterable<Szamolas> logH2Calculations() {
        return szerviz.getSzamolasok();
    }

    //Adatbázis szerinti felhasználó listázás
    @RequestMapping(method = RequestMethod.GET, path = "/logH2Users")
    @ResponseBody//JSON-be húzza a választ
    public Iterable<Felhasznalo> logH2Users() {
        return szerviz.getFelhasznalok();
    }

    //Felhasználó azonosító szerinti listázás, védelmi ellenőrzéssel is (nincs külön validátor osztály rá)
    @RequestMapping(method = RequestMethod.POST, path = "/listByUser")
    @ResponseBody
    public List<String> logH2UserCalculations(int userID) {
        if (szerviz.isExisting(userID)) {
            return szerviz.getUserData(userID);
        } else {
            System.out.println("Non existent userID!");
            return null;
        }
    }
}
