package finalproject.petable.repository;

import finalproject.petable.model.entity.Pet;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByType(PetType petType);
    List<Pet> findAllByShelterIdAndType(Long id, PetType type);
    Optional<Pet> findByName(String name);
    List<Pet> findAllByStatus(PetStatus status);
    List<Pet> findByShelterName(String name);
}
