package finalproject.petable.model.dto.users;

import finalproject.petable.validation.ValidCityName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class ShelterRegistrationDTO extends BaseUserRegistrationDTO {
    @NotBlank(message = "Shelter name cannot be empty!")
    @Size(min = 2, max = 60, message = "Length must be between 2 and 60 characters")
    private String name;
    @NotBlank(message = "Location cannot be empty!")
    @ValidCityName
    private String location;
    @NotBlank(message = "Special number cannot be empty!")
    private String specialNumber;
    @URL
    @Size(max = 1500, message = "Url is too long.")
    private String websiteUrl;

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

}
