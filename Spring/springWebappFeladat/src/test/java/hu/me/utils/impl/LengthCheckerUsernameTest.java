package hu.me.utils.impl;

import hu.me.core.User;
import hu.me.utils.ErrorMessage;
import hu.me.utils.ValidatorResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LengthCheckerUsernameTest {
    private LengthCheckerUsername lengthCheckerUsername;
    private User validUser;
    private User invalidUser;
    private ValidatorResponse validatorResponse;
    private ValidatorResponse gotValidatorResponse;

    @Before
    public void setUp() {
        lengthCheckerUsername = new LengthCheckerUsername();

        validUser = new User();
        validUser.setUsername("Toth_Zoltan96");
        validUser.setPassword("ennyiMegEgyBambi");

        invalidUser = new User();
        invalidUser.setUsername("enyi");
        invalidUser.setPassword("ennyiMegEgyBambi");
    }

    @Test
    public void valid_userIsValid_thenTrue() {
        validatorResponse = new ValidatorResponse(true, ErrorMessage.VALID);
        gotValidatorResponse=lengthCheckerUsername.valid(validUser);

        Assert.assertEquals(gotValidatorResponse.isValid(), validatorResponse.isValid());
        Assert.assertEquals(gotValidatorResponse.getErrorMessage(), validatorResponse.getErrorMessage());
    }

    @Test
    public void valid_usernameIsInvalid_thenFalse() {
        validatorResponse = new ValidatorResponse(false, ErrorMessage.USERNAMETOOSHORT);
        gotValidatorResponse=lengthCheckerUsername.valid(invalidUser);

        Assert.assertEquals(gotValidatorResponse.isValid(), validatorResponse.isValid());
        Assert.assertEquals(gotValidatorResponse.getErrorMessage(), validatorResponse.getErrorMessage());
    }
}