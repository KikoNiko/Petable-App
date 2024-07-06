package finalproject.petable.service.impl;

import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    public PetServiceImpl(PetRepository petRepository, ModelMapper modelMapper) {
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addPet(PetAddDTO petAddDTO) {

        Pet pet = new Pet();
        //TODO: validate and map correctly!
        modelMapper.map(petAddDTO, Pet.class);

        petRepository.save(pet);
    }
}
