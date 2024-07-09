package finalproject.petable.service;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.dto.PetProfileDTO;
import finalproject.petable.model.dto.PetRegistryDisplayInfoDTO;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface PetService {

    void addPet(String shelterUsername, PetAddDTO petAddDTO);

    List<PetRegistryDisplayInfoDTO> getAllByType(PetType type);

    PetProfileDTO getPetById(Long id);

    List<PetRegistryDisplayInfoDTO> getAllByShelterIdAndType(Long id, PetType type);

}
