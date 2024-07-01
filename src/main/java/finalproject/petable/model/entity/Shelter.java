package finalproject.petable.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shelters")
public class Shelter extends BaseUser{
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false, unique = true)
    private String identificationNumber;
    @OneToMany(mappedBy = "shelter")
    private List<Pet> petList;
    @OneToMany
    private Set<Message> messages;

    public Shelter() {
        petList = new ArrayList<>();
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

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
