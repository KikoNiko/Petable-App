package finalproject.petable.service;

import finalproject.petable.model.dto.users.ShelterDisplayInfoDTO;
import finalproject.petable.model.dto.users.ShelterProfileDTO;
import finalproject.petable.model.entity.Shelter;

import java.util.List;

public interface ShelterService {

    List<ShelterDisplayInfoDTO> getAllSheltersInfo();
    ShelterProfileDTO getInfoByUsername(String username);
    Shelter getByUsername(String username);
    void editShelterInfo(ShelterProfileDTO shelterProfileInfo);
    List<String> getAllSheltersNames();
}
