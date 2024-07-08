package finalproject.petable.service;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.dto.PetRegistryDisplayInfoDTO;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface PetService {

    void addPet(AppUserDetails userDetails, PetAddDTO petAddDTO);

    List<PetRegistryDisplayInfoDTO> getAllByType(PetType type);
}
