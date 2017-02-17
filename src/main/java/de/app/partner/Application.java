package de.app.partner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*@Bean
    public CommandLineRunner runner(final CrudRepository repository) {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                System.err.println(repository.findAll());
            }

        };
    }*/
}
