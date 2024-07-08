package finalproject.petable.service.impl;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;
    private final ModelMapper modelMapper;

    public PetServiceImpl(PetRepository petRepository, ShelterRepository shelterRepository, ModelMapper modelMapper) {
        this.petRepository = petRepository;
        this.shelterRepository = shelterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addPet(AppUserDetails userDetails, PetAddDTO petAddDTO) {
        Pet pet = modelMapper.map(petAddDTO, Pet.class);
        shelterRepository.findByUsername(userDetails.getUsername()).ifPresent(pet::setShelter);
        petRepository.save(pet);
    }
}
