package finalproject.petable.service;

import finalproject.petable.model.dto.users.ClientProfileDTO;
import finalproject.petable.model.dto.pets.PetDisplayInfoDTO;

import java.util.Set;

public interface ClientService {
    ClientProfileDTO getClientInfo(String username);
    Set<PetDisplayInfoDTO> getAllFavoritePets(String username);
    void addPetToFavorites(String username, Long petId);
    void removePetFromFavorites(String username, Long petId);
    void editClientInfo(ClientProfileDTO clientProfileInfo);
}
