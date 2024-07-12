package finalproject.petable.model.dto;

import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.enums.PetType;

public class PetDisplayInfoDTO {

    private Long id;

    private String name;

    private String shortDescription;

    private PetType petType;

    public PetDisplayInfoDTO(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.shortDescription = pet.getShortDescription();
        this.petType = pet.getType();
    }

    public PetDisplayInfoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
