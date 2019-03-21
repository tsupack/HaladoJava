package hu.me.utils.impl;

import hu.me.core.User;
import hu.me.utils.Checker;
import hu.me.utils.ErrorMessage;
import hu.me.utils.ValidatorResponse;

public class NoSpaceCheckerPassword implements Checker {
    @Override
    public ValidatorResponse valid(User user) {
        ValidatorResponse validatorResponse;
        if (user.getPassword().contains(" ")) {
            validatorResponse = new ValidatorResponse(false, ErrorMessage.PASSWORDCONTAINSWHITESPACE);
            return validatorResponse;
        } else {
            validatorResponse = new ValidatorResponse(true, ErrorMessage.VALID);
            return validatorResponse;
        }
    }
}