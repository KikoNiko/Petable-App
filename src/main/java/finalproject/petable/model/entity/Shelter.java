package finalproject.petable.model.entity;

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
    private String location;
    @NotBlank
    @Column(unique = true)
    private String specialNumber;
    @Column(unique = true)
    private String websiteUrl;

    private String logoUrl;
    @Column(length = 1000)
    private String bio;
    @OneToMany
    private Set<Message> messages;

    public Shelter() {
        messages = new HashSet<>();
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String description) {
        this.bio = description;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
