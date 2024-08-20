package finalproject.petable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PetableApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetableApplication.class, args);
    }

}
