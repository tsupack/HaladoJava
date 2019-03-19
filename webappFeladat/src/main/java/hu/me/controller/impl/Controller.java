package hu.me.controller.impl;

import hu.me.core.User;
import hu.me.controller.ControllerInterface;
import hu.me.utils.Checker;
import hu.me.utils.ErrorMessage;
import hu.me.utils.ValidatorResponse;

import java.util.ArrayList;
import java.util.Collection;

public class Controller implements ControllerInterface {

    private Collection<User> userStorage;
    private Collection<Checker> checkers;
    private Collection<ValidatorResponse> validatorResponses;
    private Collection<ErrorMessage> responses;

    public Controller(Collection<Checker> checkers) {
        this.userStorage = new ArrayList<>();
        this.checkers = checkers;
    }

    public Collection<ErrorMessage> storeUser(User user){
        validatorResponses = new ArrayList<>();
        responses = new ArrayList<>();

        if (checkers == null || checkers.isEmpty()) {
            responses.add(ErrorMessage.EMPTYCHECKERARRAY);
            return responses;
        }

        for (Checker checker : checkers) {
            validatorResponses.add(checker.valid(user));
        }
        boolean noErrors = true;
        for (ValidatorResponse validatorResponse : validatorResponses) {
            if (validatorResponse.isValid() == false){
                noErrors = false;
                responses.add(validatorResponse.getErrorMessage());
            }
        }
        if(noErrors){
            userStorage.add(user);
            return responses;
        } else {
            return responses;
        }
    }

    public Collection<User> getUserStorage() {
        return userStorage;
    }
}

