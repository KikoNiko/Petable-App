package finalproject.petable.repository;

import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.entity.Client;
import org.modelmapper.TypeMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);
    Optional<Client> findByFirstNameAndLastName(String firstName, String lastName);
}
