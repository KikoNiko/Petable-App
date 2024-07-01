package finalproject.petable.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pet_seekers")
public class PetSeeker extends BaseUser{
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @ManyToMany
    private Set<Pet> favoritePets;
    private String description;
    @OneToMany
    private Set<Message> messages;

    public PetSeeker() {
        favoritePets = new HashSet<>();
        messages = new HashSet<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Pet> getFavoritePets() {
        return favoritePets;
    }

    public void setFavoritePets(Set<Pet> favoritePets) {
        this.favoritePets = favoritePets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
