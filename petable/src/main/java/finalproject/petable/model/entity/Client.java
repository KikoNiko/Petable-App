package finalproject.petable.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client extends BaseUser {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @ManyToMany
    @JoinTable(
            name = "clients_favorite_pets",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private Set<Pet> favoritePets;

    public Client() {
        favoritePets = new HashSet<>();
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

    public Set<Pet> getFavoritePets() {
        return favoritePets;
    }

    public void setFavoritePets(Set<Pet> favoritePets) {
        this.favoritePets = favoritePets;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
