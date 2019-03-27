package hu.me.controller;

import hu.me.core.User;
import hu.me.utils.ErrorMessage;

import java.util.Collection;

public interface ControllerInterface {
    public Collection<ErrorMessage> storeUser(User user);
    public Collection<User> getUserStorage();
}
