package finalproject.petable.service;

import finalproject.petable.model.dto.pets.PetDisplayInfoDTO;
import finalproject.petable.model.dto.users.ClientProfileDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.service.exception.UserNotFoundException;
import finalproject.petable.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTests {
    private static final String CLIENT_USERNAME = "Test_Client";
    private ClientServiceImpl toTest;
    private Client testClient;
    private Pet testPet;
    @Mock
    private ClientRepository mockClientRepository;
    @Mock
    private PetRepository mockPetRepository;

    @BeforeEach
    void setUp() {
        toTest = new ClientServiceImpl(mockClientRepository, mockPetRepository);

        testClient = new Client();
        testClient.setUsername(CLIENT_USERNAME);
        testClient.setEmail("test@client.com");
        testClient.setFirstName("Gosho");
        testClient.setLastName("Testov");

        testPet = new Pet();
        testPet.setId(1L);
        testPet.setName("Test_Pet");
        testPet.setType(PetType.DOG);
        testPet.setGender(Gender.MALE);
        testPet.setStatus(PetStatus.LOOKING_FOR_A_HOME);
    }

    @Test
    void testGetClientInfo_ShouldReturnDto() {
        when(mockClientRepository.findByUsername(CLIENT_USERNAME)).thenReturn(Optional.of(testClient));
        ClientProfileDTO clientProfileDto = toTest.getClientInfo(CLIENT_USERNAME);
        Assertions.assertNotNull(clientProfileDto);
        Assertions.assertEquals(clientProfileDto.getEmail(), testClient.getEmail());
    }

    @Test
    void testGetClientInfo_ShouldThrow() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> toTest.getClientInfo(CLIENT_USERNAME));
    }

    @Test
    void testAddPetToFavorites() {
        when(mockClientRepository.findByUsername(CLIENT_USERNAME)).thenReturn(Optional.of(testClient));
        when(mockPetRepository.findById(testPet.getId())).thenReturn(Optional.of(testPet));
        toTest.addPetToFavorites(CLIENT_USERNAME, testPet.getId());
        Assertions.assertTrue(testClient.getFavoritePets().contains(testPet));
        Assertions.assertFalse(testClient.getFavoritePets().isEmpty());
    }

    @Test
    void testRemovePetFromFavorites() {
        when(mockClientRepository.findByUsername(CLIENT_USERNAME)).thenReturn(Optional.of(testClient));
        when(mockPetRepository.findById(testPet.getId())).thenReturn(Optional.of(testPet));
        toTest.addPetToFavorites(CLIENT_USERNAME, testPet.getId());
        Assertions.assertTrue(testClient.getFavoritePets().contains(testPet));

        toTest.removePetFromFavorites(CLIENT_USERNAME, testPet.getId());
        Assertions.assertFalse(testClient.getFavoritePets().contains(testPet));
        Assertions.assertTrue(testClient.getFavoritePets().isEmpty());
    }

    @Test
    void testGetAllFavoritePets() {
        when(mockClientRepository.findByUsername(CLIENT_USERNAME)).thenReturn(Optional.of(testClient));
        when(mockPetRepository.findById(testPet.getId())).thenReturn(Optional.of(testPet));
        toTest.addPetToFavorites(CLIENT_USERNAME, testPet.getId());
        Set<PetDisplayInfoDTO> allFavoritePets = toTest.getAllFavoritePets(CLIENT_USERNAME);
        Assertions.assertEquals(1, allFavoritePets.size());
    }

    @Test
    void testEditClientInfo() {
        when(mockClientRepository.findById(testClient.getId())).thenReturn(Optional.of(testClient));
        ClientProfileDTO dto = new ClientProfileDTO();
        dto.setId(testClient.getId());
        dto.setUsername(testClient.getUsername());
        //changed first name and email
        dto.setFullName("Pesho Testov");
        dto.setEmail("changed@test.com");
        dto.setImageUrl(testClient.getProfileImageUrl());

        toTest.editClientInfo(dto);
        Assertions.assertEquals(dto.getFullName().split(" ")[0], testClient.getFirstName());
        Assertions.assertEquals(dto.getEmail(), testClient.getEmail());
    }
}
