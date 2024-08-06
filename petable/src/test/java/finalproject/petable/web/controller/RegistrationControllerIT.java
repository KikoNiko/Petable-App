package finalproject.petable.web.controller;

import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.ShelterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(properties = {"admin.username = admin", "admin.password = admin123"})
@AutoConfigureMockMvc
public class RegistrationControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ShelterRepository shelterRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegisterClient() throws Exception {
        mockMvc.perform(post("/users/register/client")
                .param("username", "testClient")
                .param("email", "test@test.com")
                .param("password", "test123")
                .param("confirmPassword", "test123")
                .param("firstName", "Pesho")
                .param("lastName", "Testov")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));

        Optional<Client> optClient = clientRepository.findByUsername("testClient");

        Assertions.assertTrue(optClient.isPresent());

        Client client = optClient.get();

        Assertions.assertEquals("test@test.com", client.getEmail());
        Assertions.assertEquals("Pesho", client.getFirstName());
        Assertions.assertEquals("Testov", client.getLastName());

        Assertions.assertTrue(passwordEncoder.matches("test123", client.getPassword()));
    }

    @Test
    void testRegisterShelter() throws Exception {
        mockMvc.perform(post("/users/register/shelter")
                        .param("username", "testShelter")
                        .param("email", "shelter@test.com")
                        .param("password", "test1234")
                        .param("confirmPassword", "test1234")
                        .param("name", "Test Shelter")
                        .param("location", "Sofia")
                        .param("specialNumber", "123asdtest")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));

        Optional<Shelter> optShelter = shelterRepository.findByUsername("testShelter");

        Assertions.assertTrue(optShelter.isPresent());

        Shelter shelter = optShelter.get();

        Assertions.assertEquals("shelter@test.com", shelter.getEmail());
        Assertions.assertEquals("Test Shelter", shelter.getName());
        Assertions.assertEquals("123asdtest", shelter.getSpecialNumber());

        Assertions.assertTrue(passwordEncoder.matches("test1234", shelter.getPassword()));
    }

}

