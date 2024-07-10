package finalproject.petable.service;

import finalproject.petable.model.dto.ClientProfileDTO;

public interface ClientService {

    ClientProfileDTO getClientInfo(String username);
}
