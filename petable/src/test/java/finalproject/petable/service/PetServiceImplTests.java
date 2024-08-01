package finalproject.petable.service;

import finalproject.petable.model.dto.pets.PetAddDTO;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.impl.PetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetServiceImplTests {

    private static final String TEST_SHELTER_USERNAME = "Test_Shelter";

    private PetServiceImpl toTest;
    @Captor
    private ArgumentCaptor<Pet> petCaptor;
    @Mock
    private PetRepository mockPetRepository;
    @Mock
    private ClientRepository mockClientRepository;
    @Mock
    private ShelterRepository mockShelterRepository;
    @Spy
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        toTest = new PetServiceImpl(
                mockPetRepository,
                mockShelterRepository,
                mockClientRepository,
                mockModelMapper
        );
    }

    @Test
    void addPetTest() {
        PetAddDTO testPetDto = new PetAddDTO();
        testPetDto.setName("TestPet");
        testPetDto.setLocation("Plovdiv");
        testPetDto.setGender(Gender.FEMALE);
        testPetDto.setStatus(PetStatus.RESERVED.label);
        testPetDto.setType(PetType.DOG);
        testPetDto.setBirthdate(LocalDate.of(2020, 5, 5));
        testPetDto.setShortDescription("Test description");
        testPetDto.setStory("Test story");

        Pet testPet = mockModelMapper.map(testPetDto, Pet.class);
        testPet.setStatus(PetStatus.valueOfLabel(testPetDto.getStatus()));

        Shelter shelter = new Shelter();
        when(mockShelterRepository.findByUsername(TEST_SHELTER_USERNAME)).thenReturn(Optional.of(shelter));
        mockShelterRepository.findByUsername(TEST_SHELTER_USERNAME).ifPresent(testPet::setShelter);

        toTest.addPet(TEST_SHELTER_USERNAME, testPetDto);

        verify(mockPetRepository).save(petCaptor.capture());

        Pet actualPet = petCaptor.getValue();

        Assertions.assertNotNull(actualPet);
        Assertions.assertEquals(testPet.getName(), actualPet.getName());
        Assertions.assertEquals(testPet.getType(), actualPet.getType());
        Assertions.assertEquals(testPet.getStatus(), actualPet.getStatus());
        Assertions.assertEquals(testPet.getGender(), actualPet.getGender());
        Assertions.assertEquals(testPet.getLocation(), actualPet.getLocation());
        Assertions.assertEquals(testPet.getBirthdate(), actualPet.getBirthdate());
        Assertions.assertEquals(testPet.getShortDescription(), actualPet.getShortDescription());
        Assertions.assertEquals(testPet.getShelter(), actualPet.getShelter());
    }

}
