package finalproject.petable.service.impl;

import finalproject.petable.model.dto.ShelterDisplayInfoDTO;
import finalproject.petable.model.dto.ShelterProfileDTO;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.ShelterService;
import finalproject.petable.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }


    @Override
    public List<ShelterDisplayInfoDTO> getAllSheltersInfo() {
        return shelterRepository.findAll()
                .stream().map(ShelterDisplayInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ShelterProfileDTO getInfoByUsername(String username) {
        Shelter shelter = shelterRepository.findByUsername(username).orElse(null);
        if (shelter == null) {
            throw new UserNotFoundException("Shelter not found!");
        }
        return new ShelterProfileDTO(shelter);
    }
}
