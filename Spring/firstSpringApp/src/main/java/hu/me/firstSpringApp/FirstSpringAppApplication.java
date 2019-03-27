package hu.me.firstSpringApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstSpringAppApplication implements CommandLineRunner {

	Message message;

	@Autowired
	public void setMessage(MessageImpl message) {
		this.message = message;
	}

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringAppApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		System.out.println(message.getMessage());
	}
}
