package finalproject.petable.service;

import finalproject.petable.model.dto.users.ShelterDisplayInfoDTO;
import finalproject.petable.model.dto.users.ShelterProfileDTO;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.exception.UserNotFoundException;
import finalproject.petable.service.impl.ShelterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShelterServiceImplTests {
    private final String SHELTER_USERNAME = "Test_Shelter";
    private Shelter testShelter;

    private ShelterServiceImpl toTest;
    @Mock
    private ShelterRepository mockShelterRepository;


    @BeforeEach
    void setUp() {
        toTest = new ShelterServiceImpl(mockShelterRepository);
        testShelter = new Shelter();
        testShelter.setUsername(SHELTER_USERNAME);
        testShelter.setEmail("shelter@test.com");
        testShelter.setSpecialNumber("1234abcd");
        testShelter.setLocation("Test city");
    }

    @Test
    void testGetAllSheltersInfo() {
        when(mockShelterRepository.findAll()).thenReturn(List.of(testShelter));
        List<ShelterDisplayInfoDTO> allSheltersInfo = toTest.getAllSheltersInfo();
        Assertions.assertFalse(allSheltersInfo.isEmpty());
    }

    @Test
    void testGetInfoByUsername_ShouldThrow() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> toTest.getInfoByUsername(SHELTER_USERNAME));
    }

    @Test
    void testGetInfoByUsername() {
        when(mockShelterRepository.findByUsername(SHELTER_USERNAME))
                .thenReturn(Optional.of(testShelter));
        ShelterProfileDTO infoByUsername = toTest.getInfoByUsername(SHELTER_USERNAME);
        Assertions.assertNotNull(infoByUsername);
        Assertions.assertEquals(infoByUsername.getUsername(), testShelter.getUsername());
    }

    @Test
    void testGetByUsername_ShouldThrow() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> toTest.getByUsername(SHELTER_USERNAME));
    }

    @Test
    void testGetByUsername_ShouldReturnShelter() {
        when(mockShelterRepository.findByUsername(SHELTER_USERNAME))
                .thenReturn(Optional.of(testShelter));
        Shelter shelter = toTest.getByUsername(SHELTER_USERNAME);
        Assertions.assertNotNull(shelter);
    }

    @Test
    void testGetAllSheltersNames() {
        when(mockShelterRepository.findAll()).thenReturn(List.of(testShelter));
        List<String> allSheltersNames = toTest.getAllSheltersNames();
        Assertions.assertFalse(allSheltersNames.isEmpty());
        Assertions.assertTrue(allSheltersNames.contains(testShelter.getUsername()));
    }

    @Test
    void testEditShelterInfo() {
        when(mockShelterRepository.findById(testShelter.getId())).thenReturn(Optional.of(testShelter));
        ShelterProfileDTO dto = new ShelterProfileDTO();
        dto.setId(testShelter.getId());
        dto.setUsername(testShelter.getUsername());
        dto.setLocation("Plovdiv");
        dto.setEmail("changed@test.com");

        toTest.editShelterInfo(dto);
        Assertions.assertEquals(dto.getEmail(), testShelter.getEmail());
        Assertions.assertEquals(dto.getLocation(), testShelter.getLocation());
    }
}
