package finalproject.petable.service.impl;

import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.dto.PetProfileDTO;
import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Image;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.ImageRepository;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.PetService;
import finalproject.petable.service.exception.PetNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    public PetServiceImpl(PetRepository petRepository, ShelterRepository shelterRepository, ClientRepository clientRepository, ModelMapper modelMapper, ImageRepository imageRepository) {
        this.petRepository = petRepository;
        this.shelterRepository = shelterRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
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
        Pet pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new PetNotFoundException("Pet not found!", id);
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
    public void editPetInfo(PetProfileDTO petProfileInfo) {
        Long petId = petProfileInfo.getId();
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet not found!", petId);
        }
        Pet pet = optionalPet.get();
        pet.setId(petId);
        pet.setName(petProfileInfo.getName());
        pet.setLocation(petProfileInfo.getLocation());
        pet.setImages(mapImages(petProfileInfo));
        petRepository.save(pet);
    }

    private List<Image> mapImages(PetProfileDTO petProfileDTO) {
        List<String> images = petProfileDTO.getImages();
        List<Image> petImages = new ArrayList<>();
        for (String imageUrl : images) {
            String randomName = UUID.randomUUID().toString();
            Image petImage = new Image(randomName, imageUrl);
            saveImage(petImage);
            petImages.add(petImage);
        }
        return petImages;
    }

    private void saveImage(Image image) {
        imageRepository.save(image);
    }

}
