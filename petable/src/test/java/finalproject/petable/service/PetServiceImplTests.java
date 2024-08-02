package finalproject.petable.service;

import finalproject.petable.model.dto.pets.PetAddDTO;
import finalproject.petable.model.dto.pets.PetDisplayInfoDTO;
import finalproject.petable.model.dto.pets.PetProfileDTO;
import finalproject.petable.model.entity.Image;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.exception.PetNotFoundException;
import finalproject.petable.service.impl.PetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetServiceImplTests {

    private static final String TEST_SHELTER_USERNAME = "Test_Shelter";
    private static final String TEST_IMAGE_URL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3hTQwsrGuYW0XGXbIB4d2noVL1ZhL7llERA&s";

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

    private PetAddDTO testPetAddDto;
    private Pet testPet;

    @BeforeEach
    void setUp() {
        toTest = new PetServiceImpl(
                mockPetRepository,
                mockShelterRepository,
                mockClientRepository,
                mockModelMapper
        );
        testPetAddDto = createPetAddDTO();
        testPet = createPet();
    }

    @Test
    void testAddPet() {

        toTest.addPet(TEST_SHELTER_USERNAME, testPetAddDto);

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

    @Test
    void testRemovePet() {

        Assertions.assertThrows(PetNotFoundException.class, () -> toTest.removePet(testPet.getId()));
        when(mockPetRepository.findById(testPet.getId())).thenReturn(Optional.of(testPet));

        when(mockPetRepository.save(Mockito.any(Pet.class))).thenReturn(testPet);
        mockPetRepository.save(testPet);
        Assertions.assertNotNull(mockPetRepository.findById(testPet.getId()));

        toTest.removePet(testPet.getId());
        verify(mockPetRepository).delete(petCaptor.capture());

        Assertions.assertEquals(0, mockPetRepository.count());

    }

    @Test
    void testGetAllByType() {
        when(mockPetRepository.findAllByType(testPet.getType())).thenReturn(List.of(testPet));
        List<PetDisplayInfoDTO> allByType = toTest.getAllByType(testPet.getType());
        Assertions.assertFalse(allByType.isEmpty());
    }

    @Test
    void testGetPetById() {
        Long id = 1L;
        when(mockPetRepository.findById(testPet.getId())).thenReturn(Optional.of(testPet));
        PetProfileDTO petById = toTest.getPetById(id);
        Assertions.assertNotNull(petById);
    }

    @Test
    void testGetAllByShelterIdAndType() {
        Long shelterId = testPet.getShelter().getId();
        when(mockPetRepository.findAllByShelterIdAndType(shelterId, testPet.getType()))
                .thenReturn(List.of(testPet));
        List<PetDisplayInfoDTO> allByShelterIdAndType = toTest.getAllByShelterIdAndType(shelterId, testPet.getType());
        Assertions.assertFalse(allByShelterIdAndType.isEmpty());
    }

    @Test
    void testEditPetInfo_ShouldThrow() {
        PetProfileDTO petProfileInfo = new PetProfileDTO(testPet);
        petProfileInfo.setId(4L);
        Assertions.assertThrows(PetNotFoundException.class,
                () -> toTest.editPetInfo(petProfileInfo));
    }

    private PetAddDTO createPetAddDTO() {
        PetAddDTO testPetDto = new PetAddDTO();
        testPetDto.setName("TestPet");
        testPetDto.setLocation("Plovdiv");
        testPetDto.setGender(Gender.FEMALE);
        testPetDto.setStatus(PetStatus.RESERVED.label);
        testPetDto.setType(PetType.DOG);
        testPetDto.setBirthdate(LocalDate.of(2020, 5, 5));
        testPetDto.setShortDescription("Test description");
        testPetDto.setStory("Test story");
        return testPetDto;
    }

    private Pet createPet() {
        PetAddDTO testPetDto = createPetAddDTO();
        Pet testPet = mockModelMapper.map(testPetDto, Pet.class);
        testPet.setId(1L);
        testPet.setStatus(PetStatus.valueOfLabel(testPetDto.getStatus()));
        testPet.setImages(List.of(new Image("test", TEST_IMAGE_URL)));
        Shelter shelter = new Shelter();
        when(mockShelterRepository.findByUsername(TEST_SHELTER_USERNAME)).thenReturn(Optional.of(shelter));
        mockShelterRepository.findByUsername(TEST_SHELTER_USERNAME).ifPresent(testPet::setShelter);
        return testPet;
    }

}
