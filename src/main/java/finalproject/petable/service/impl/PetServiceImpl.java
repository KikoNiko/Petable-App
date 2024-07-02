package finalproject.petable.service.impl;

import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.service.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void addPet(PetAddDTO petAddDTO) {

        Pet pet = new Pet();
        pet.setName(petAddDTO.getName());
        pet.setGender(petAddDTO.getGender());
        pet.setBirthdate(petAddDTO.getBirthdate());
        pet.setType(petAddDTO.getType());
        pet.setStatus(petAddDTO.getStatus());
        pet.setLocation(petAddDTO.getLocation());
        pet.setDescription(petAddDTO.getDescription());

        petRepository.save(pet);
    }
}
