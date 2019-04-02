package hu.me.controller;

import hu.me.controller.dto.Input;
import hu.me.service.InputValidator;
import hu.me.service.Szerviz;
import hu.me.exceptions.DivisionByZeroException;
import hu.me.service.exceptions.NullOperatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
        return operatorok;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView initialize(){
        ModelAndView MAV = new ModelAndView();
        MAV.setViewName("index");
        MAV.addObject("input", new Input("osszead", 0, 0));
        MAV.addObject("eredmeny", 0);
        MAV.addObject("log", "Üres");
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
                MAV.addObject("eredmeny", szerviz.szamol(input));
            } catch (DivisionByZeroException e){
                MAV.addObject("log", szerviz.getLog());
                MAV.setViewName("index");
                return MAV;
            } catch (NullOperatorException e) {
                MAV.addObject("log", szerviz.getLog());
                MAV.setViewName("index");
                return MAV;
            }
            try {
                szerviz.log("{" + input.getA() + " " + input.getMuvelet() + " " + input.getB() + " = " + szerviz.szamol(input) + "}");
                MAV.addObject("log", szerviz.getLog());
                MAV.setViewName("index");
            } catch (DivisionByZeroException e) {
                MAV.addObject("log", szerviz.getLog());
                MAV.setViewName("index");
                return MAV;
            } catch (NullOperatorException e) {
                MAV.addObject("log", szerviz.getLog());
                MAV.setViewName("index");
                return MAV;
            }
        }
        return MAV;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/log")
    @ResponseBody
    public List<String> log() {
        return szerviz.getLog();
    }
}
