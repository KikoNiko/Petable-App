package finalproject.petable.service.impl;

import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.ShelterRegistrationDTO;
import finalproject.petable.model.entity.Client;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.repository.ClientRepository;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ClientRepository clientRepository;
    private final ShelterRepository shelterRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(ClientRepository clientRepository, ShelterRepository shelterRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.shelterRepository = shelterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerClient(ClientRegistrationDTO clientRegistrationDTO) {
        Client mapped = modelMapper.map(clientRegistrationDTO, Client.class);
        clientRepository.save(mapped);
    }

    @Override
    public void registerShelter(ShelterRegistrationDTO shelterRegistrationDTO) {
        Shelter mapped = modelMapper.map(shelterRegistrationDTO, Shelter.class);
        shelterRepository.save(mapped);
    }
}
