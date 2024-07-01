package finalproject.petable.repository;

import finalproject.petable.model.entity.PetSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetSeekerRepository extends JpaRepository<PetSeeker, Long> {
}
