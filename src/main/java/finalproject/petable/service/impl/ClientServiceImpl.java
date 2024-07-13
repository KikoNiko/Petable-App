package finalproject.petable.service.impl;

import finalproject.petable.model.dto.ClientProfileDTO;
import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.service.ClientService;
import finalproject.petable.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientProfileDTO getClientInfo(String username) {
        Client client = clientRepository.findByUsername(username).orElse(null);
        if (client == null) {
            throw new UserNotFoundException("User not found!");
        }
        return new ClientProfileDTO(client);
    }

    @Override
    public Set<PetDisplayInfoDTO> getAllFavoritePets(String username) {
        Optional<Client> byUsername = clientRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        return byUsername.get()
                .getFavoritePets()
                .stream()
                .map(PetDisplayInfoDTO::new)
                .collect(Collectors.toSet());
    }
}
