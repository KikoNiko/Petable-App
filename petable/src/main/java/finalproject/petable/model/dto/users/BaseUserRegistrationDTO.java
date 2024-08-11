package finalproject.petable.model.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BaseUserRegistrationDTO{

    @NotBlank(message = "Username cannot be empty!")
    @Size(min = 4, max = 20, message = "Username length must be between 4 and 20 characters")
    private String username;
    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Not a valid email")
    private String email;
    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 5, max = 30, message = "Password length must be between 5 and 30 characters!")
    private String password;
    @NotBlank
    @Size(min = 5, max = 30, message = "Password length must be between 5 and 30 characters!")
    private String confirmPassword;
    private String profileImageUrl;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
