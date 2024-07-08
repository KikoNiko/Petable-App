package finalproject.petable.service;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.PetAddDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface PetService {

    void addPet(AppUserDetails userDetails, PetAddDTO petAddDTO);
}
