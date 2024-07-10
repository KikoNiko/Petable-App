package finalproject.petable.service;

import finalproject.petable.model.dto.ShelterDisplayInfoDTO;
import finalproject.petable.model.dto.ShelterProfileDTO;

import java.util.List;

public interface ShelterService {

    List<ShelterDisplayInfoDTO> getAllSheltersInfo();
    ShelterProfileDTO getInfoByUsername(String username);
}
