package finalproject.petable.model.dto.users;

import finalproject.petable.model.entity.Shelter;

public class ShelterDisplayInfoDTO {

    private String name;

    private String bio;

    private String websiteUrl;
    
    private String logoUrl;

    public ShelterDisplayInfoDTO(Shelter shelter) {
        this.name = shelter.getName();
        this.bio = shelter.getBio();
        this.websiteUrl = shelter.getWebsiteUrl();
        this.logoUrl = shelter.getProfileImageUrl();
    }

    public ShelterDisplayInfoDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
