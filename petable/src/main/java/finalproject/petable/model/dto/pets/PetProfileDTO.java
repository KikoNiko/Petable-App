package finalproject.petable.model.dto.pets;

import finalproject.petable.model.entity.Image;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PetProfileDTO {
    private Long id;
    private String name;
    private PetType type;
    private Gender gender;
    private String age;
    private String location;
    private String shortDescription;
    private String story;
    private String status;
    private String shelterName;
    private String shelterLink;
    private List<Image> images;

    public PetProfileDTO(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.type = pet.getType();
        this.gender = pet.getGender();
        this.age = pet.getAge(pet.getBirthdate());
        this.location = pet.getLocation();
        this.shortDescription = pet.getShortDescription();
        this.story = pet.getStory();
        this.status = pet.getStatus().label;
        this.shelterName = pet.getShelter().getName();
        this.shelterLink = pet.getShelter().getWebsiteUrl();
        this.images = pet.getImages();
    }

    public PetProfileDTO() {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getShelterLink() {
        return shelterLink;
    }

    public void setShelterLink(String shelterLink) {
        this.shelterLink = shelterLink;
    }

    public List<Image> getAllImages() {
        return images;
    }
    public List<Image> getImages() {
        return images.subList(1, images.size());
    }

}
