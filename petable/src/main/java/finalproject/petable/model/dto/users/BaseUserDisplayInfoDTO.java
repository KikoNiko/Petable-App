package finalproject.petable.model.dto.users;

import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.UserRole;

import java.util.List;
import java.util.stream.Collectors;

public class BaseUserDisplayInfoDTO {

    private String username;
    private String email;
    private List<String> roles;

    public BaseUserDisplayInfoDTO(BaseUser user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles()
                .stream()
                .map(UserRole::getRole)
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    public BaseUserDisplayInfoDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
