package finalproject.petable.service.impl;

import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.RegistrationDTO;
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
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
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
    public void registerUser(RegistrationDTO registrationData, String userType) {
        BaseUser user = modelMapper.map(registrationData, BaseUser.class);
        user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
        Optional<UserRole> byRole = roleRepository.findByRole(UserRolesEnum.valueOf(userType.toUpperCase()));
        if (byRole.isEmpty()) {
            throw new NullPointerException();
        }
        user.setRoles(List.of(byRole.get()));
        userRepository.save(user);
    }
}
