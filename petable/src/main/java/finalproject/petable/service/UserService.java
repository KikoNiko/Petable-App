package finalproject.petable.service;

import finalproject.petable.model.dto.users.BaseUserDisplayInfoDTO;
import finalproject.petable.model.dto.users.ClientRegistrationDTO;
import finalproject.petable.model.dto.users.ShelterRegistrationDTO;
import finalproject.petable.model.entity.BaseUser;

import java.util.List;

public interface UserService {
    boolean isUsernameRegistered(String username);
    boolean isEmailRegistered(String email);
    void registerClient(ClientRegistrationDTO clientRegistrationDTO);
    void registerShelter(ShelterRegistrationDTO shelterRegistrationDTO);
    List<BaseUserDisplayInfoDTO> getAllUsersInfo();
    BaseUser getByUsername(String username);
    BaseUser getById(Long id);
    void changeUserRoles(BaseUserDisplayInfoDTO dto);
}
