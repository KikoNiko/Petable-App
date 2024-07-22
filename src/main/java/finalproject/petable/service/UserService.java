package finalproject.petable.service;

import finalproject.petable.model.dto.BaseUserDisplayInfoDTO;
import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.ShelterRegistrationDTO;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean isUsernameRegistered(String username);
    boolean isEmailRegistered(String email);
    void registerClient(ClientRegistrationDTO clientRegistrationDTO);
    void registerShelter(ShelterRegistrationDTO shelterRegistrationDTO);
    List<BaseUserDisplayInfoDTO> getAllUsersInfo();

}
