package finalproject.petable;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"admin.username = admin", "admin.password = admin123"})
class PetableApplicationTests {

    @Test
    void contextLoads() {
    }

}
