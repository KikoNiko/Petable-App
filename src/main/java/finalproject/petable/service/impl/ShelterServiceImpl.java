package finalproject.petable.service.impl;

import finalproject.petable.model.dto.ShelterDisplayInfoDTO;
import finalproject.petable.model.dto.ShelterProfileDTO;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.ShelterService;
import finalproject.petable.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
            throw new UserNotFoundException("Shelter not found!", username);
        }
        return new ShelterProfileDTO(shelter);
    }

    @Override
    public Shelter getByUsername(String username) {
        Optional<Shelter> optionalShelter = shelterRepository.findByUsername(username);
        if (optionalShelter.isEmpty()) {
            throw new UserNotFoundException("Shelter not found!", username);
        }
        return optionalShelter.get();
    }

    @Override
    public void editShelterInfo(ShelterProfileDTO shelterProfileInfo) {
        Optional<Shelter> optionalShelter = shelterRepository.findById(shelterProfileInfo.getId());
        if (optionalShelter.isEmpty()) {
            throw new UserNotFoundException("Shelter not found!", shelterProfileInfo.getId());
        }
        Shelter shelter = optionalShelter.get();
        shelter.setName(shelterProfileInfo.getName());
        shelter.setUsername(shelterProfileInfo.getUsername());
        shelter.setEmail(shelterProfileInfo.getEmail());
        shelter.setLocation(shelterProfileInfo.getLocation());
        shelter.setSpecialNumber(shelterProfileInfo.getSpecialNumber());
        shelter.setBio(shelterProfileInfo.getBio());
        shelter.setProfileImageUrl(shelterProfileInfo.getLogoUrl());
        shelter.setWebsiteUrl(shelterProfileInfo.getWebsiteUrl());
        shelterRepository.save(shelter);
    }

    @Override
    public Shelter findById(Long shelterId) {
        return shelterRepository.
                findById(shelterId)
                .orElseThrow(() -> new UserNotFoundException("Shelter not found!", shelterId));
    }

    @Override
    public List<String> getAllSheltersNames() {
        return shelterRepository.findAll()
                .stream()
                .map(Shelter::getName)
                .collect(Collectors.toList());
    }
}
