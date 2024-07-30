package finalproject.petable.repository;

import finalproject.petable.model.entity.UserRole;
import finalproject.petable.model.entity.enums.UserRolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRole(UserRolesEnum role);
}
