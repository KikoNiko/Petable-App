package finalproject.petable.model.dto;

import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import java.time.LocalDate;

public class PetProfileDTO {
    private String name;

    private PetType type;

    private Gender gender;

    private LocalDate birthdate;

    private String location;

    private String shortDescription;

    private String story;

    private PetStatus status;

    private String shelterName;

    public PetProfileDTO(Pet pet) {
        this.name = pet.getName();
        this.type = pet.getType();
        this.gender = pet.getGender();
        this.birthdate = pet.getBirthdate();
        this.location = pet.getLocation();
        this.shortDescription = pet.getShortDescription();
        this.story = pet.getStory();
        this.status = pet.getStatus();
        this.shelterName = pet.getShelter().getName();
    }

    public PetProfileDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }
}
