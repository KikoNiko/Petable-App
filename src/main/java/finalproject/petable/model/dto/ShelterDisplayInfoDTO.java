package finalproject.petable.model.dto;

import finalproject.petable.model.entity.Shelter;

public class ShelterDisplayInfoDTO {

    private String name;

    private String description;

    private String websiteUrl;
    
    private String logoUrl;

    public ShelterDisplayInfoDTO(Shelter shelter) {
        this.name = shelter.getName();
        this.description = shelter.getBio();
        this.websiteUrl = shelter.getWebsiteUrl();
        this.logoUrl = shelter.getLogoUrl();
    }

    public ShelterDisplayInfoDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
