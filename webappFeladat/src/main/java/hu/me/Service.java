package hu.me;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Service {
    private ObjectMapper objectMapper;

    public boolean save(User user) throws IOException {
        try {
            this.objectMapper = new ObjectMapper();
            objectMapper.writeValue(new FileOutputStream("src//main//resources//" + "output.json"), user);
            return true;
        } catch (IOException e){
            System.out.println("Hibas fajlmuvelet!" + e.getMessage());
            return false;
        }
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
