package hu.me.service;

import hu.me.core.User;
import hu.me.service.exceptions.InvalidUserInformationException;

import java.util.Collection;

public interface ServiceInterface {
    void storeUser(User user) throws InvalidUserInformationException;

    Collection<User> getUserStorage();
}
