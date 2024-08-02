package finalproject.petable.service;

import finalproject.petable.model.dto.users.ClientRegistrationDTO;
import finalproject.petable.model.dto.users.ShelterRegistrationDTO;
import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.model.entity.UserRole;
import finalproject.petable.model.entity.enums.UserRolesEnum;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.repository.UserRoleRepository;
import finalproject.petable.service.exception.UserNotFoundException;
import finalproject.petable.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {
    private static final String TEST_USERNAME = "test_username";
    private static final String NOT_EXISTENT_USERNAME = "fake_username";
    private static final String TEST_EMAIL = "test@mail.com";
    private static final String TEST_PASSWORD = "test_password123";
    private UserServiceImpl toTest;
    @Captor
    private ArgumentCaptor<Client> clientCaptor;
    @Captor
    private ArgumentCaptor<Shelter> shelterCaptor;
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
        ClientRegistrationDTO cliRegDto = new ClientRegistrationDTO();
        cliRegDto.setUsername("test");
        cliRegDto.setPassword("test123");
        cliRegDto.setConfirmPassword("test123");
        cliRegDto.setEmail("test@mail.com");
        cliRegDto.setFirstName("Test4o");
        cliRegDto.setLastName("Testov");

        when(mockPasswordEncoder.encode(cliRegDto.getPassword()))
                .thenReturn(cliRegDto.getPassword() + "encoded");

        UserRole clientRole = new UserRole(UserRolesEnum.CLIENT);
        when(mockUserRoleRepository.findByRole(UserRolesEnum.CLIENT)).thenReturn(Optional.of(clientRole));

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

    @Test
    void testRegisterShelter() {
        ShelterRegistrationDTO sheRegDto = new ShelterRegistrationDTO();
        sheRegDto.setUsername("testShelter");
        sheRegDto.setPassword("test123");
        sheRegDto.setConfirmPassword("test123");
        sheRegDto.setEmail("test-shelter@mail.com");
        sheRegDto.setName("Test Shelter");
        sheRegDto.setSpecialNumber("12345asd");
        sheRegDto.setLocation("Sofia");
        sheRegDto.setWebsiteUrl("test.com");

        when(mockPasswordEncoder.encode(sheRegDto.getPassword()))
                .thenReturn(sheRegDto.getPassword() + "encoded");

        UserRole shelterRole = new UserRole(UserRolesEnum.SHELTER);
        when(mockUserRoleRepository.findByRole(UserRolesEnum.SHELTER)).thenReturn(Optional.of(shelterRole));

        toTest.registerShelter(sheRegDto);

        verify(mockShelterRepository).save(shelterCaptor.capture());
        Shelter savedShelter = shelterCaptor.getValue();

        Assertions.assertNotNull(savedShelter);
        Assertions.assertEquals(sheRegDto.getUsername(), savedShelter.getUsername());
        Assertions.assertEquals(sheRegDto.getEmail(), savedShelter.getEmail());
        Assertions.assertEquals(sheRegDto.getName(), savedShelter.getName());
        Assertions.assertEquals(sheRegDto.getSpecialNumber(), savedShelter.getSpecialNumber());
        Assertions.assertEquals(sheRegDto.getPassword() + "encoded", savedShelter.getPassword());
        Assertions.assertEquals(sheRegDto.getLocation(), savedShelter.getLocation());
        Assertions.assertEquals(sheRegDto.getWebsiteUrl(), savedShelter.getWebsiteUrl());
    }

    @Test
    void testGetByUsername_ShouldReturnUser() {
        BaseUser testUser = new BaseUser();
        UserRole adminRole = new UserRole(UserRolesEnum.ADMIN);
        testUser.setUsername(TEST_USERNAME);
        testUser.setEmail(TEST_EMAIL);
        testUser.setRoles(List.of(adminRole));
        testUser.setPassword(TEST_PASSWORD);
        when(mockUserRepository.findByUsername(TEST_USERNAME)).thenReturn(Optional.of(testUser));

        BaseUser byUsername = toTest.getByUsername(TEST_USERNAME);
        Assertions.assertInstanceOf(BaseUser.class, byUsername);
        Assertions.assertEquals(TEST_USERNAME, byUsername.getUsername());
        Assertions.assertEquals(TEST_EMAIL, byUsername.getEmail());
        Assertions.assertEquals(TEST_PASSWORD, byUsername.getPassword());
        Assertions.assertEquals(List.of(adminRole), byUsername.getRoles());
    }

    @Test
    void testGetByUsername_ShouldThrow() {
        Assertions.assertThrows(
                UserNotFoundException.class,
                () -> toTest.getByUsername(NOT_EXISTENT_USERNAME)
        );
    }

    @Test
    void testIsEmailRegistered() {
        BaseUser testUser = new BaseUser();
        UserRole adminRole = new UserRole(UserRolesEnum.ADMIN);
        testUser.setUsername(TEST_USERNAME);
        testUser.setEmail(TEST_EMAIL);
        testUser.setRoles(List.of(adminRole));
        testUser.setPassword(TEST_PASSWORD);

        when(mockUserRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(testUser));

        Assertions.assertTrue(toTest.isEmailRegistered(TEST_EMAIL));
        Assertions.assertFalse(toTest.isEmailRegistered("fake@fake.com"));
    }
}
