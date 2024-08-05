package finalproject.petable.model.entity;

import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{
    @NotNull
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PetType type;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    private LocalDate birthdate;
    @NotNull
    private String location;
    @Size(max = 200)
    private String shortDescription;
    @Column(columnDefinition = "TEXT")
    private String story;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PetStatus status;
    @ManyToOne
    private Shelter shelter;
    @OneToMany
    private List<Image> images;

    public Pet() {
        this.images = new ArrayList<>();
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

    public void setShortDescription(String description) {
        this.shortDescription = description;
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

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(name, pet.name)
                && type == pet.type
                && gender == pet.gender
                && Objects.equals(birthdate, pet.birthdate)
                && Objects.equals(location, pet.location)
                && Objects.equals(shortDescription, pet.shortDescription)
                && status == pet.status
                && Objects.equals(shelter, pet.shelter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, gender, birthdate, location, shortDescription, status, shelter);
    }

    public String getAge(LocalDate birthdate) {
        StringBuilder sb = new StringBuilder();
        int result = 0;
        LocalDate currentDate = LocalDate.now();

        if (birthdate.getYear() == currentDate.getYear()) {
            if (birthdate.getMonthValue() == currentDate.getMonthValue()) {
                result = currentDate.getDayOfMonth() - birthdate.getDayOfMonth();
                return sb.append(result).append(" days").toString();
            }
            result = currentDate.getMonthValue() - birthdate.getMonthValue();
            return sb.append(result).append(" months").toString();
        }
        result = currentDate.getYear() - birthdate.getYear();
        return sb.append(result).append(" years").toString();
    }
}
