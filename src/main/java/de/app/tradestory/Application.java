package de.app.tradestory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
