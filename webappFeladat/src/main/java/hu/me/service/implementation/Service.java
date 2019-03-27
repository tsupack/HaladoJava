package hu.me.service.implementation;


import hu.me.core.User;
import hu.me.service.ServiceInterface;
import hu.me.service.exceptions.InvalidUserInformationException;
import hu.me.utils.Checker;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Service implements ServiceInterface {

    private Collection<User> userStorage;
    private Collection<Checker> checkers;
    private ObjectMapper objectMapper;

    public Service(Collection<Checker> checkers) {
        this.userStorage = new ArrayList<>();
        this.checkers = checkers;
    }

    public void storeUser(User user) throws InvalidUserInformationException {
        for (Checker checker : checkers) {
            if (!checker.valid(user)){
                throw new InvalidUserInformationException();
            }
        }
        userStorage.add(user);
        System.out.println("Felhasznalo ideiglenes tarban elmentve!");
        try {
            this.objectMapper = new ObjectMapper();
            objectMapper.writeValue(new FileOutputStream("src//main//resources//" + "output.json"), user);
        } catch (IOException e){
            System.out.println("Hibas fajlmuvelet!" + e.getMessage());
        }
    }

    public Collection<User> getUserStorage() {
        return userStorage;
    }

    public User load() throws IOException {
        try {
            this.objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File("src//main//resources//" + "output.json"), User.class);
        } catch (IOException e){
            System.out.println("Hibas fajlmuvelet!" + e.getMessage());
            return null;
        }
    }
}
