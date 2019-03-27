package hu.me.controller;

import hu.me.controller.dto.Input;
import hu.me.service.Szerviz;
import hu.me.exceptions.DivisionByZeroException;
import hu.me.service.exceptions.NullOperatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class Kontroller {
    private Szerviz szerviz;

    @Autowired
    public void setSzerviz(Szerviz szerviz){
        this.szerviz = szerviz;
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
    public ModelAndView input(@Valid Input input, BindingResult bindingResult){

        System.out.println(input);
        System.out.println(bindingResult);

        ModelAndView MAV = new ModelAndView();

        if (bindingResult.hasErrors()) {
            MAV.setViewName("index");
        } else {
            try {
                MAV.addObject("eredmeny", szerviz.szamol(input));
            } catch (DivisionByZeroException e){
                bindingResult.addError(new FieldError("input", "b", "0-val való osztás!"));
                MAV.addObject("log", szerviz.getLog());
                MAV.setViewName("index");
                return MAV;
            } catch (NullOperatorException e) {
                bindingResult.addError(new FieldError("input", "muvelet", "Kérem válasszon műveletet!"));
                MAV.addObject("log", szerviz.getLog());
                MAV.setViewName("index");
                return MAV;
            }
            szerviz.log("{" + input.getA() + " " + input.getMuvelet() + " "
                    + input.getB() + " = " + MAV.getModel().values().toString().replace("[", "").replace("]", "") + "}");
            MAV.addObject("log", szerviz.getLog());
            MAV.setViewName("index");
        }
        return MAV;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/log")
    @ResponseBody
    public List<String> log() {
        return szerviz.getLog();
    }
}
