package finalproject.petable.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientRegistrationDTO {
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 2, max = 40)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;
    @NotBlank
    @Size(min = 5, max = 30, message = "Password length must be between 5 and 30 characters!")
    private String password;
    @NotBlank
    @Size(min = 5, max = 30)
    private String confirmPassword;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
