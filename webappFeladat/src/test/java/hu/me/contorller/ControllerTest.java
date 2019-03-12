package hu.me.contorller;

import hu.me.controller.Controller;
import hu.me.core.User;
import hu.me.service.exceptions.InvalidUserInformationException;
import hu.me.service.implementation.Service;
import hu.me.utils.Checker;
import hu.me.utils.implementation.LengthChecker;
import hu.me.utils.implementation.NoSpaceChecker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;

public class ControllerTest {

    private Controller controller;
    private Service service;
    private User user;
    private ArrayList<Checker> checkers;
    private Checker noSpaceChecker;
    private Checker lengthChecker;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("TothZoltan_93");
        user.setPassword("ennyiMegEgyBambi");

        noSpaceChecker = Mockito.mock(NoSpaceChecker.class);
        lengthChecker = Mockito.mock(LengthChecker.class);

        checkers = new ArrayList<>();
        checkers.add(noSpaceChecker);
        checkers.add(lengthChecker);

        service = new Service(checkers);
        controller = new Controller(service);
    }

    @Test
    public void controller_saveUser_ValidUser_thenNoException() throws InvalidUserInformationException {
        Mockito.when(lengthChecker.valid(user)).thenReturn(true);
        Mockito.when(noSpaceChecker.valid(user)).thenReturn(true);
        controller.saveUser(user);

        Mockito.verify(noSpaceChecker).valid(user);
        Mockito.verify(lengthChecker).valid(user);
    }

    @Test(expected = InvalidUserInformationException.class)
    public void controller_saveUser_InvalidUser_thenException() throws InvalidUserInformationException {
        Mockito.when(lengthChecker.valid(user)).thenReturn(false);
        Mockito.when(noSpaceChecker.valid(user)).thenReturn(false);
        controller.saveUser(user);

        Mockito.verify(noSpaceChecker).valid(user);
        Mockito.verify(lengthChecker).valid(user);
    }
}
