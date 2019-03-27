package hu.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Futtathato implements CommandLineRunner {
    private Kontroller kontroller;

    @Autowired
    public void setKontroller(Kontroller kontroller){
        this.kontroller = kontroller;
    }
    public static void main(String[] args) {
        SpringApplication.run(Futtathato.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Kontroller kontroller = new Kontroller();
        kontroller.fut();
    }
}
