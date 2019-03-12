package hu.me.controller;

import hu.me.core.User;

import java.util.Collection;

public interface ControllerInterface {
    public Collection<String> storeUser(User user);
    public Collection<User> getUserStorage();
}
