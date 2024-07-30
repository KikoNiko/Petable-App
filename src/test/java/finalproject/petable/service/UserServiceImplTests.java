package finalproject.petable.service;

import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.UserRole;
import finalproject.petable.model.entity.enums.UserRolesEnum;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.repository.UserRoleRepository;
import finalproject.petable.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {
    private UserServiceImpl toTest;
    @Captor
    private ArgumentCaptor<Client> clientCaptor;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private ClientRepository mockClientRepository;
    @Mock
    private ShelterRepository mockShelterRepository;
    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        toTest = new UserServiceImpl(
                mockUserRepository,
                mockClientRepository,
                mockShelterRepository,
                mockUserRoleRepository,
                new ModelMapper(),
                mockPasswordEncoder
        );
    }

    @Test
    void testRegisterClient() {
        mockUserRoleRepository.save(new UserRole(UserRolesEnum.CLIENT));

        ClientRegistrationDTO cliRegDto = new ClientRegistrationDTO();
        cliRegDto.setUsername("test");
        cliRegDto.setPassword("test123");
        cliRegDto.setConfirmPassword("test123");
        cliRegDto.setEmail("test@mail.com");
        cliRegDto.setFirstName("Test4o");
        cliRegDto.setLastName("Testov");

        when(mockPasswordEncoder.encode(cliRegDto.getPassword()))
                .thenReturn(cliRegDto.getPassword() + "encoded");

        toTest.registerClient(cliRegDto);

        verify(mockClientRepository).save(clientCaptor.capture());

        Client savedClient = clientCaptor.getValue();

        Assertions.assertNotNull(savedClient);
        Assertions.assertEquals(cliRegDto.getUsername(), savedClient.getUsername());
        Assertions.assertEquals(cliRegDto.getEmail(), savedClient.getEmail());
        Assertions.assertEquals(cliRegDto.getFirstName(), savedClient.getFirstName());
        Assertions.assertEquals(cliRegDto.getLastName(), savedClient.getLastName());
        Assertions.assertEquals(cliRegDto.getPassword() + "encoded", savedClient.getPassword());
    }

}
