package hu.me.utils.impl;

import hu.me.core.User;
import hu.me.utils.ErrorMessage;
import hu.me.utils.ValidatorResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LengthCheckerPasswordTest {
    private LengthCheckerPassword lengthCheckerPassword;
    private User validUser;
    private User invalidUser;
    private ValidatorResponse validatorResponse;
    private ValidatorResponse gotValidatorResponse;

    @Before
    public void setUp() {
        lengthCheckerPassword = new LengthCheckerPassword();

        validUser = new User();
        validUser.setUsername("Toth_Zoltan96");
        validUser.setPassword("ennyiMegEgyBambi");

        invalidUser = new User();
        invalidUser.setUsername("Toth_Zoltan96");
        invalidUser.setPassword("enyi");
    }

    @Test
    public void valid_userIsValid_thenTrue() {
        validatorResponse = new ValidatorResponse(true, ErrorMessage.VALID);
        gotValidatorResponse=lengthCheckerPassword.valid(validUser);

        Assert.assertEquals(gotValidatorResponse.isValid(), validatorResponse.isValid());
        Assert.assertEquals(gotValidatorResponse.getErrorMessage(), validatorResponse.getErrorMessage());
    }

    @Test
    public void valid_userPasswordIsInvalid_thenFalse() {
        validatorResponse = new ValidatorResponse(false, ErrorMessage.PASSWORDTOOSHORT);
        gotValidatorResponse=lengthCheckerPassword.valid(invalidUser);

        Assert.assertEquals(gotValidatorResponse.isValid(), validatorResponse.isValid());
        Assert.assertEquals(gotValidatorResponse.getErrorMessage(), validatorResponse.getErrorMessage());
    }
}