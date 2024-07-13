package finalproject.petable.model.dto;

import finalproject.petable.model.entity.Shelter;

public class ShelterProfileDTO {
    private String username;
    private String email;
    private String name;
    private String location;
    private String specialNumber;
    private String websiteUrl;
    private String logoUrl;
    private String description;

    public ShelterProfileDTO(Shelter shelter) {
        this.username = shelter.getUsername();
        this.email = shelter.getEmail();
        this.name = shelter.getName();
        this.location = shelter.getLocation();
        this.specialNumber = shelter.getSpecialNumber();
        this.websiteUrl = shelter.getWebsiteUrl();
        this.logoUrl = shelter.getLogoUrl();
        this.description = shelter.getBio();
    }

    public ShelterProfileDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setSpecialNumber(String specialNumber) {
        this.specialNumber = specialNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
