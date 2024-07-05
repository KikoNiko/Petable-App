package finalproject.petable.service.impl;

import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.ShelterRegistrationDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.model.entity.enums.UserRolesEnum;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.repository.UserRoleRepository;
import finalproject.petable.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final ClientRepository clientRepository;
    private final ShelterRepository shelterRepository;
    private final UserRoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ClientRepository clientRepository, ShelterRepository shelterRepository, UserRoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.shelterRepository = shelterRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerClient(ClientRegistrationDTO clientRegistrationDTO) {
        Client mapped = modelMapper.map(clientRegistrationDTO, Client.class);
        mapped.setPassword(passwordEncoder.encode(clientRegistrationDTO.getPassword()));
        mapped.setRoles(List.of(roleRepository.findByRole(UserRolesEnum.CLIENT).get()));
        clientRepository.save(mapped);
    }

    @Override
    public void registerShelter(ShelterRegistrationDTO shelterRegistrationDTO) {
        Shelter mapped = modelMapper.map(shelterRegistrationDTO, Shelter.class);
        mapped.setPassword(passwordEncoder.encode(shelterRegistrationDTO.getPassword()));
        mapped.setRoles(List.of(roleRepository.findByRole(UserRolesEnum.SHELTER).get()));
        shelterRepository.save(mapped);
    }
}
