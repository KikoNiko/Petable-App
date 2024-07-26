package finalproject.petable.model.dto;

import finalproject.petable.model.entity.Client;

public class ClientProfileDTO {

    private String username;

    private String fullName;

    private String email;

    private String imageUrl;

    public ClientProfileDTO(Client client) {
        this.username = client.getUsername();
        this.fullName = client.getFirstName() + " " + client.getLastName();
        this.email = client.getEmail();
        this.imageUrl = client.getProfileImageUrl();
    }

    public ClientProfileDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
