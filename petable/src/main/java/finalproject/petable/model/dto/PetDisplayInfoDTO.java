package finalproject.petable.model.dto;

import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.enums.PetType;

public class PetDisplayInfoDTO {

    private Long id;

    private String name;

    private String shortDescription;

    private PetType petType;

    private String profileImageUrl;

    public PetDisplayInfoDTO(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.shortDescription = pet.getShortDescription();
        this.petType = pet.getType();
        if (!pet.getImages().isEmpty()) {
            this.profileImageUrl = pet.getImages().get(0).getUrl();
        } else if (petType.equals(PetType.CAT)){
            this.profileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT53hL5qjY5qzvc-8HDatMuH9mm3Tol2Dl1OQ&s";
        } else if (petType.equals(PetType.DOG)) {
            this.profileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3hTQwsrGuYW0XGXbIB4d2noVL1ZhL7llERA&s";
        }
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

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
