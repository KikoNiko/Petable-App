package finalproject.petable.model.dto;

import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetType;

public class PetRegistryDisplayInfoDTO {

    private String name;

    private String shortDescription;

    private PetType petType;

    public PetRegistryDisplayInfoDTO(Pet pet) {
        this.name = pet.getName();
        this.shortDescription = pet.getShortDescription();
        this.petType = pet.getType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

}
