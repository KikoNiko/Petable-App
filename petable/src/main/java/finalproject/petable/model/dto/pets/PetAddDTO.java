package finalproject.petable.model.dto.pets;

import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.validation.ValidPetName;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PetAddDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50 characters!")
    private String name;
    @NotNull(message = "You must select a gender!")
    private Gender gender;
    @NotNull(message = "You must select pet type!")
    private PetType type;
    @NotNull
    @Past(message = "Birthdate cannot be in the present or future!")
    private LocalDate birthdate;
    @NotEmpty
    @Size(min = 2, max = 50, message = "Length must be between 2 and 50 characters!")
    private String location;
    @NotEmpty(message = "You must select pet status!")
    private String status;
    @NotEmpty
    @Size(max = 200, message = "Short description cannot be longer than 200 characters.")
    private String shortDescription;
    private String story;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
