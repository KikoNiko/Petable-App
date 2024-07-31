package finalproject.petable.service;

import finalproject.petable.model.dto.pets.PetAddDTO;
import finalproject.petable.model.dto.pets.PetProfileDTO;
import finalproject.petable.model.dto.pets.PetDisplayInfoDTO;
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
    void assignImageToPet(Image image, Long id);

}
