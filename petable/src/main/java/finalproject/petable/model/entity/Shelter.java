package finalproject.petable.model.entity;

import finalproject.petable.validation.ValidCityName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shelters")
public class Shelter extends BaseUser {
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    @ValidCityName
    private String location;
    @NotBlank
    @Column(unique = true)
    private String specialNumber;
    @Column(unique = true)
    private String websiteUrl;
    @Column(length = 1000)
    private String bio;

    public Shelter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecialNumber() {
        return specialNumber;
    }

    public void setSpecialNumber(String identificationNumber) {
        this.specialNumber = identificationNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteLink) {
        this.websiteUrl = websiteLink;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String description) {
        this.bio = description;
    }
}
