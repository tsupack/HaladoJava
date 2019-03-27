package hu.me.utils.impl;

import hu.me.core.User;
import hu.me.utils.Checker;
import hu.me.utils.ErrorMessage;
import hu.me.utils.ValidatorResponse;

public class NoSpaceCheckerUsername implements Checker {
    @Override
    public ValidatorResponse valid(User user) {
        ValidatorResponse validatorResponse;
        if (user.getUsername().contains(" ")) {
            validatorResponse = new ValidatorResponse(false, ErrorMessage.USERNAMECONTAINSWHITESPACE);
            return validatorResponse;
        } else {
            validatorResponse = new ValidatorResponse(true, ErrorMessage.VALID);
            return validatorResponse;
        }
    }
}