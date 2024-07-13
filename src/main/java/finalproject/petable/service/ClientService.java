package finalproject.petable.service;

import finalproject.petable.model.dto.ClientProfileDTO;
import finalproject.petable.model.dto.PetDisplayInfoDTO;

import java.util.Set;

public interface ClientService {

    ClientProfileDTO getClientInfo(String username);
    Set<PetDisplayInfoDTO> getAllFavoritePets(String username);
}
