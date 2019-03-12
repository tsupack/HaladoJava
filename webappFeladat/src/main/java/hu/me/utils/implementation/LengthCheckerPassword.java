package hu.me.utils.implementation;

import hu.me.core.User;
import hu.me.utils.Checker;
import hu.me.utils.ValidatorResponse;

public class LengthCheckerPassword implements Checker {
    @Override
    public ValidatorResponse valid(User user) {
        ValidatorResponse validatorResponse;
        if (user.getPassword().length() < 6) {
            validatorResponse = new ValidatorResponse(false, "PASSWORDTOOSHORT");
            return validatorResponse;
        } else {
            validatorResponse = new ValidatorResponse(true, "OKAY");
            return validatorResponse;
        }
    }
}
