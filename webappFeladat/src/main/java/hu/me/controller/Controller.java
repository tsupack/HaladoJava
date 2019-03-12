package hu.me.controller;

import hu.me.core.User;
import hu.me.service.ServiceInterface;
import hu.me.service.exceptions.InvalidUserInformationException;

public class Controller {

    private ServiceInterface serviceInterface;

    public Controller(ServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public void saveUser(User user) throws InvalidUserInformationException {
        serviceInterface.storeUser(user);
    }

}
