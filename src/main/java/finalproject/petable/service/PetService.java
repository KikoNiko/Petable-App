package finalproject.petable.service;

import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.dto.PetProfileDTO;
import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.entity.Image;
import finalproject.petable.model.entity.enums.PetType;

import java.util.List;

public interface PetService {

    void addPet(String shelterUsername, PetAddDTO petAddDTO);
    void removePet(Long petId);
    List<PetDisplayInfoDTO> getAllByType(PetType type);
    PetProfileDTO getPetById(Long id);
    List<PetDisplayInfoDTO> getAllByShelterIdAndType(Long id, PetType type);

    void editPetInfo(PetProfileDTO petProfileInfo);
}
