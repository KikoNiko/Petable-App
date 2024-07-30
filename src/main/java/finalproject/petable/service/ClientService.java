package finalproject.petable.service;

import finalproject.petable.model.dto.ClientProfileDTO;
import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.entity.Client;
import org.apache.catalina.User;

import java.util.Set;

public interface ClientService {
    ClientProfileDTO getClientInfo(String username);
    Set<PetDisplayInfoDTO> getAllFavoritePets(String username);
    void addPetToFavorites(String username, Long petId);
    void removePetFromFavorites(String username, Long petId);
    Client findById(Long clientId);
    void editClientInfo(ClientProfileDTO clientProfileInfo);
}
