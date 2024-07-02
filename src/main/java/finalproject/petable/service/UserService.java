package finalproject.petable.service;

import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.ShelterRegistrationDTO;

public interface UserService {
    void registerClient(ClientRegistrationDTO clientRegistrationDTO);
    void registerShelter(ShelterRegistrationDTO shelterRegistrationDTO);
}
