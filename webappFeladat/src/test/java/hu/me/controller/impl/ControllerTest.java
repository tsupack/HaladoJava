package hu.me.controller.impl;

import hu.me.core.User;
import hu.me.utils.ErrorMessage;
import hu.me.utils.Checker;
import hu.me.utils.impl.LengthCheckerUsername;
import hu.me.utils.impl.LengthCheckerPassword;
import hu.me.utils.impl.NoSpaceCheckerUsername;
import hu.me.utils.impl.NoSpaceCheckerPassword;
import hu.me.utils.impl.NullCheckerUsername;
import hu.me.utils.impl.NullCheckerPassword;
import hu.me.utils.ValidatorResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ControllerTest {

    private Controller controller;
    private User user;
    private User tooShortUsernameWithWhitespaceAndPassword;
    private ArrayList<Checker> checkers;
    private Checker noSpaceCheckerUsername;
    private Checker noSpaceCheckerPassword;
    private Checker lengthCheckerUsername;
    private Checker lengthCheckerPassword;
    private Checker nullCheckerUsername;
    private Checker nullCheckerPassword;
    private ValidatorResponse validatorResponse1;
    private ValidatorResponse validatorResponse2;
    private ValidatorResponse validatorResponse3;
    private ValidatorResponse validatorResponse4;
    private ValidatorResponse validatorResponse5;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("Toth_Zoltan96");
        user.setPassword("ennyiMegEgyBambi");

        tooShortUsernameWithWhitespaceAndPassword = new User();
        tooShortUsernameWithWhitespaceAndPassword.setUsername("Kat b");
        tooShortUsernameWithWhitespaceAndPassword.setPassword("Kat b");

        noSpaceCheckerUsername = Mockito.mock(NoSpaceCheckerUsername.class);
        noSpaceCheckerPassword = Mockito.mock(NoSpaceCheckerPassword.class);
        lengthCheckerUsername = Mockito.mock(LengthCheckerUsername.class);
        lengthCheckerPassword = Mockito.mock(LengthCheckerPassword.class);
        nullCheckerUsername = Mockito.mock(NullCheckerUsername.class);
        nullCheckerPassword = Mockito.mock(NullCheckerPassword.class);

        checkers = new ArrayList<>();
        checkers.add(noSpaceCheckerUsername);
        checkers.add(noSpaceCheckerPassword);
        checkers.add(lengthCheckerUsername);
        checkers.add(lengthCheckerPassword);
        checkers.add(nullCheckerUsername);
        checkers.add(nullCheckerPassword);

        controller = new Controller(checkers);
    }

    @Test
    public void controller_storeUser_ValidUser_thenValidErrorMessage(){
        validatorResponse1 = new ValidatorResponse(true, ErrorMessage.VALID);

        Mockito.when(lengthCheckerUsername.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(lengthCheckerPassword.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(noSpaceCheckerUsername.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(noSpaceCheckerPassword.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(nullCheckerUsername.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(nullCheckerPassword.valid(user)).thenReturn(validatorResponse1);
        controller.storeUser(user);

        Mockito.verify(lengthCheckerUsername).valid(user);
        Mockito.verify(lengthCheckerPassword).valid(user);
        Mockito.verify(noSpaceCheckerUsername).valid(user);
        Mockito.verify(noSpaceCheckerPassword).valid(user);
        Mockito.verify(nullCheckerUsername).valid(user);
        Mockito.verify(nullCheckerPassword).valid(user);
    }

    @Test
    public void controller_saveUser_InvalidUser_thenErrorMessages(){
        validatorResponse1 = new ValidatorResponse(false, ErrorMessage.USERNAMETOOSHORT);
        validatorResponse2 = new ValidatorResponse(false, ErrorMessage.PASSWORDTOOSHORT);
        validatorResponse3 = new ValidatorResponse(false, ErrorMessage.USERNAMECONTAINSWHITESPACE);
        validatorResponse4 = new ValidatorResponse(false, ErrorMessage.PASSWORDCONTAINSWHITESPACE);
        validatorResponse5 = new ValidatorResponse(true, ErrorMessage.VALID);

        Mockito.when(lengthCheckerUsername.valid(tooShortUsernameWithWhitespaceAndPassword)).thenReturn(validatorResponse1);
        Mockito.when(lengthCheckerPassword.valid(tooShortUsernameWithWhitespaceAndPassword)).thenReturn(validatorResponse2);
        Mockito.when(noSpaceCheckerUsername.valid(tooShortUsernameWithWhitespaceAndPassword)).thenReturn(validatorResponse3);
        Mockito.when(noSpaceCheckerPassword.valid(tooShortUsernameWithWhitespaceAndPassword)).thenReturn(validatorResponse4);
        Mockito.when(nullCheckerUsername.valid(tooShortUsernameWithWhitespaceAndPassword)).thenReturn(validatorResponse5);
        Mockito.when(nullCheckerPassword.valid(tooShortUsernameWithWhitespaceAndPassword)).thenReturn(validatorResponse5);

        controller.storeUser(tooShortUsernameWithWhitespaceAndPassword);

        Mockito.verify(lengthCheckerUsername).valid(tooShortUsernameWithWhitespaceAndPassword);
        Mockito.verify(lengthCheckerPassword).valid(tooShortUsernameWithWhitespaceAndPassword);
        Mockito.verify(noSpaceCheckerUsername).valid(tooShortUsernameWithWhitespaceAndPassword);
        Mockito.verify(noSpaceCheckerPassword).valid(tooShortUsernameWithWhitespaceAndPassword);
        Mockito.verify(nullCheckerUsername).valid(tooShortUsernameWithWhitespaceAndPassword);
        Mockito.verify(nullCheckerPassword).valid(tooShortUsernameWithWhitespaceAndPassword);
    }

    @Test
    public void controller_saveUser_withEmptyCheckerArray(){
        checkers = new ArrayList<>();
        controller = new Controller(checkers);
        controller.storeUser(user);

        Assert.assertTrue(controller.getUserStorage().contains(user));
    }

    @Test(expected = NullPointerException.class)
    public void controller_saveUser_nullCheckerArray(){
        controller = new Controller(null);
        controller.storeUser(user);

        Assert.assertTrue(controller.getUserStorage().contains(user));
    }

    @Test
    public void controller_validatorArgumentSame_thenTrue(){
        validatorResponse1 = new ValidatorResponse(true, ErrorMessage.VALID);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        Mockito.when(lengthCheckerUsername.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(lengthCheckerPassword.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(noSpaceCheckerUsername.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(noSpaceCheckerPassword.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(nullCheckerUsername.valid(user)).thenReturn(validatorResponse1);
        Mockito.when(nullCheckerPassword.valid(user)).thenReturn(validatorResponse1);
        controller.storeUser(user);

        Mockito.verify(lengthCheckerUsername).valid(argument.capture());
        Mockito.verify(lengthCheckerPassword).valid(argument.capture());
        Mockito.verify(noSpaceCheckerUsername).valid(argument.capture());
        Mockito.verify(noSpaceCheckerPassword).valid(argument.capture());
        Mockito.verify(nullCheckerUsername).valid(argument.capture());
        Mockito.verify(nullCheckerPassword).valid(argument.capture());

        Assert.assertEquals(argument.getValue().getUsername(), "Toth_Zoltan96");
        Assert.assertEquals(argument.getValue().getPassword(), "ennyiMegEgyBambi");
    }
}
