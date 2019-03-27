package hu.me.utils.impl;

import hu.me.core.User;
import hu.me.utils.Checker;
import hu.me.utils.ErrorMessage;
import hu.me.utils.ValidatorResponse;

public class NullCheckerPassword implements Checker {
    @Override
    public ValidatorResponse valid(User user) {
        ValidatorResponse validatorResponse;
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            validatorResponse = new ValidatorResponse(false, ErrorMessage.PASSWORDISNULL);
            return validatorResponse;
        } else {
            validatorResponse = new ValidatorResponse(true, ErrorMessage.VALID);
            return validatorResponse;
        }
    }
}
