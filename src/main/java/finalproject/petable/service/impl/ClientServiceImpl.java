package finalproject.petable.service.impl;

import finalproject.petable.model.dto.ClientProfileDTO;
import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.service.ClientService;
import finalproject.petable.service.exception.PetNotFoundException;
import finalproject.petable.service.exception.UserNotFoundException;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final PetRepository petRepository;

    public ClientServiceImpl(ClientRepository clientRepository, PetRepository petRepository) {
        this.clientRepository = clientRepository;
        this.petRepository = petRepository;
    }

    @Override
    public ClientProfileDTO getClientInfo(String username) {
        Client client = clientRepository.findByUsername(username).orElse(null);
        if (client == null) {
            throw new UserNotFoundException("User not found!", username);
        }
        return new ClientProfileDTO(client);
    }

    @Override
    public Set<PetDisplayInfoDTO> getAllFavoritePets(String username) {
        Optional<Client> byUsername = clientRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UserNotFoundException("User not found!", username);
        }
        return byUsername.get()
                .getFavoritePets()
                .stream()
                .map(PetDisplayInfoDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public void addPetToFavorites(String username, Long petId) {
        Client client = getClientByUsername(username);
        Pet pet = getPet(petId);
        client.getFavoritePets().add(pet);
        clientRepository.save(client);
    }

    @Override
    public void removePetFromFavorites(String username, Long petId) {
        Client client = getClientByUsername(username);
        Pet pet = getPet(petId);
        client.getFavoritePets().remove(pet);
        clientRepository.save(client);
    }

    @Override
    public Client findById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new UserNotFoundException("User not found", clientId));
    }

    @Override
    public void editClientInfo(ClientProfileDTO clientProfileInfo) {
        Optional<Client> optionalClient = clientRepository.findById(clientProfileInfo.getId());
        if (optionalClient.isEmpty()) {
            throw new UserNotFoundException("User not found!", clientProfileInfo.getId());
        }
        Client client = optionalClient.get();
        client.setFirstName(clientProfileInfo.getFullName().split(" ")[0]);
        client.setLastName(clientProfileInfo.getFullName().split(" ")[1]);
        client.setUsername(clientProfileInfo.getUsername());
        client.setEmail(clientProfileInfo.getEmail());
        clientRepository.save(client);
    }

    private Client getClientByUsername(String username) {
        Optional<Client> byUsername = clientRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UserNotFoundException("User not found!", username);
        }
        return byUsername.get();
    }

    private Pet getPet(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet not found!", id);
        }
        return optionalPet.get();
    }
}
