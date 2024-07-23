package finalproject.petable.service.impl;

import finalproject.petable.model.dto.BaseUserDisplayInfoDTO;
import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.ShelterRegistrationDTO;
import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.model.entity.UserRole;
import finalproject.petable.model.entity.enums.UserRolesEnum;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.repository.UserRoleRepository;
import finalproject.petable.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final ShelterRepository shelterRepository;
    private final UserRoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           ClientRepository clientRepository,
                           ShelterRepository shelterRepository,
                           UserRoleRepository roleRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.shelterRepository = shelterRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isUsernameRegistered(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void registerClient(ClientRegistrationDTO clientRegistrationDTO) {
        Client client = modelMapper.map(clientRegistrationDTO, Client.class);
        client.setPassword(passwordEncoder.encode(clientRegistrationDTO.getPassword()));
        Optional<UserRole> byRole = roleRepository.findByRole(UserRolesEnum.CLIENT);
        if (byRole.isEmpty()) {
            throw new NullPointerException();
        }
        client.setRoles(List.of(byRole.get()));
        clientRepository.save(client);
    }

    @Override
    public void registerShelter(ShelterRegistrationDTO shelterRegistrationDTO) {
        Shelter shelter = modelMapper.map(shelterRegistrationDTO, Shelter.class);
        shelter.setPassword(passwordEncoder.encode(shelterRegistrationDTO.getPassword()));
        Optional<UserRole> byRole = roleRepository.findByRole(UserRolesEnum.SHELTER);
        if (byRole.isEmpty()) {
            throw new NullPointerException();
        }
        shelter.setRoles(List.of(byRole.get()));
        shelterRepository.save(shelter);
    }

    @Override
    public List<BaseUserDisplayInfoDTO> getAllUsersInfo() {
        return userRepository.findAll()
                .stream()
                .map(BaseUserDisplayInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public BaseUser getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
