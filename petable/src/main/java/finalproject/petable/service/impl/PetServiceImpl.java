package finalproject.petable.service.impl;

import finalproject.petable.model.dto.pets.PetAddDTO;
import finalproject.petable.model.dto.pets.PetProfileDTO;
import finalproject.petable.model.dto.pets.PetDisplayInfoDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Image;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.PetService;
import finalproject.petable.service.exception.PetNotFoundException;
import finalproject.petable.service.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public PetServiceImpl(PetRepository petRepository, ShelterRepository shelterRepository, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.petRepository = petRepository;
        this.shelterRepository = shelterRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addPet(String shelterUsername, PetAddDTO petAddDTO) {
        Pet pet = modelMapper.map(petAddDTO, Pet.class);
        pet.setStatus(PetStatus.valueOfLabel(petAddDTO.getStatus()));
        shelterRepository.findByUsername(shelterUsername).ifPresent(pet::setShelter);
        petRepository.save(pet);
    }

    @Override
    public void removePet(Long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet not found!", petId);
        }
        Pet pet = optionalPet.get();
        clientRepository.findAll()
                .stream()
                .map(Client::getFavoritePets)
                .filter(fav -> fav.contains(pet))
                .forEach(fav -> fav.remove(pet));
        petRepository.delete(pet);
    }

    @Override
    public List<PetDisplayInfoDTO> getAllByType(PetType petType) {
        return petRepository.findAllByType(petType)
                .stream()
                .map(PetDisplayInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public PetProfileDTO getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found!", id));
        return new PetProfileDTO(pet);
    }

    @Override
    public List<PetDisplayInfoDTO> getAllByShelterIdAndType(Long id, PetType type) {
        return petRepository.findAllByShelterIdAndType(id, type)
                .stream()
                .map(PetDisplayInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void editPetInfo(PetProfileDTO petProfileInfo) {
        Long petId = petProfileInfo.getId();
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet not found!", petId);
        }
        Pet pet = optionalPet.get();
        pet.setName(petProfileInfo.getName());
        pet.setLocation(petProfileInfo.getLocation());
        pet.setStatus(PetStatus.valueOfLabel(petProfileInfo.getStatus()));
        pet.setStory(petProfileInfo.getStory());
        petRepository.save(pet);
    }

    @Override
    public void assignImageToPet(Image image, Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet with id " + id + " not found", id);
        }
        Pet pet = optionalPet.get();
        pet.getImages().add(image);
        petRepository.save(pet);
    }

    @Override
    public boolean isUserAuthorized(Long petId, String username) {
        if (username.equals("admin")) {
            return true;
        }
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet with id " + petId + " not found", petId);
        }
        Pet pet = optionalPet.get();
        return pet.getShelter().getUsername().equals(username);
    }
}
