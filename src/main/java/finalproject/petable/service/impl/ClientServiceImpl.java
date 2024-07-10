package finalproject.petable.service.impl;

import finalproject.petable.model.dto.ClientProfileDTO;
import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.Client;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.service.ClientService;
import finalproject.petable.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final UserRepository userRepository;

    public ClientServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ClientProfileDTO getClientInfo(String username) {
        BaseUser client = userRepository.findByUsername(username).orElse(null);
        if (client == null) {
            throw new UserNotFoundException("User not found!");
        }
        return new ClientProfileDTO((Client) client);
    }
}
