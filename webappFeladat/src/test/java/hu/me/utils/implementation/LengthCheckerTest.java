package hu.me.utils.implementation;

import hu.me.core.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LengthCheckerTest {

    private LengthChecker lengthChecker;
    private User userValid;
    private User userNameInvalid;
    private User userPassInvalid;
    private User userBothInvalid;

    @Before
    public void setUp() {
        lengthChecker = new LengthChecker();
        userValid = new User();
        userValid.setUsername("TothZoltan_93");
        userValid.setPassword("ennyiMegEgyBambi");

        userNameInvalid = new User();
        userNameInvalid.setUsername("nem");
        userNameInvalid.setPassword("ennyiMegEgyBambi");

        userPassInvalid = new User();
        userPassInvalid.setUsername("TothZoltan_93");
        userPassInvalid.setPassword("nem");

        userBothInvalid = new User();
        userBothInvalid.setUsername("nem");
        userBothInvalid.setPassword("nem");
    }

    @Test
    public void valid_userIsValid_thenTrue() {
        Assert.assertTrue(lengthChecker.valid(userValid));
    }

    @Test
    public void valid_userNameIsInvalid_thenFalse() {
        Assert.assertFalse(lengthChecker.valid(userNameInvalid));
    }

    @Test
    public void valid_userPassIsInvalid_thenFalse() {
        Assert.assertFalse(lengthChecker.valid(userPassInvalid));
    }

    @Test
    public void valid_userIsInvalid_thenFalse() {
        Assert.assertFalse(lengthChecker.valid(userBothInvalid));
    }
}