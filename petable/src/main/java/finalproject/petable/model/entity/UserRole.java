package finalproject.petable.model.entity;

import finalproject.petable.model.entity.enums.UserRolesEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRolesEnum role;

    public UserRole() {
    }

    public UserRole(UserRolesEnum role) {
        this.role = role;
    }

    public UserRolesEnum getRole() {
        return role;
    }

    public void setRole(UserRolesEnum role) {
        this.role = role;
    }
}
