package hu.me.utils.implementation;

import hu.me.core.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NoSpaceCheckerTest {

    private NoSpaceChecker noSpaceChecker;
    private User userValid;
    private User userNameInvalid;
    private User userPassInvalid;
    private User userBothInvalid;

    @Before
    public void setUp() {
        noSpaceChecker = new NoSpaceChecker();
        userValid = new User();
        userValid.setUsername("TothZoltan_93");
        userValid.setPassword("ennyiMegEgyBambi");

        userNameInvalid = new User();
        userNameInvalid.setUsername("nem nem");
        userNameInvalid.setPassword("ennyiMegEgyBambi");

        userPassInvalid = new User();
        userPassInvalid.setUsername("TothZoltan_93");
        userPassInvalid.setPassword("nem nem");

        userBothInvalid = new User();
        userBothInvalid.setUsername("nem nem");
        userBothInvalid.setPassword("nem nem");
    }

    @Test
    public void valid_userIsValid_thenTrue() {
        Assert.assertTrue(noSpaceChecker.valid(userValid));
    }

    @Test
    public void valid_userNameIsInvalid_thenFalse() {
        Assert.assertFalse(noSpaceChecker.valid(userNameInvalid));
    }

    @Test
    public void valid_userPassIsInvalid_thenFalse() {
        Assert.assertFalse(noSpaceChecker.valid(userPassInvalid));
    }

    @Test
    public void valid_userIsInvalid_thenFalse() {
        Assert.assertFalse(noSpaceChecker.valid(userBothInvalid));
    }
}