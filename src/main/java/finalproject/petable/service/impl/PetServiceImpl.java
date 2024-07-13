package finalproject.petable.service.impl;

import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.dto.PetProfileDTO;
import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Pet;
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
import java.util.Set;
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
        shelterRepository.findByUsername(shelterUsername).ifPresent(pet::setShelter);
        petRepository.save(pet);
    }

    @Override
    public void removePet(Long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet not found!");
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
        Pet pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new PetNotFoundException("Pet not found!");
        }
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
    public void addToFavorites(String username, Long petId) {
        Client client = getClientByUsername(username);
        Pet pet = getPet(petId);
        client.getFavoritePets().add(pet);
        clientRepository.save(client);
    }

    @Override
    public void removeFromFavorites(String username, Long petId) {
        Client client = getClientByUsername(username);
        Pet pet = getPet(petId);
        client.getFavoritePets().remove(pet);
        clientRepository.save(client);
    }

    private Client getClientByUsername(String username) {
        Optional<Client> byUsername = clientRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        return byUsername.get();
    }

    private Pet getPet(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet not found!");
        }
        return optionalPet.get();
    }

}
