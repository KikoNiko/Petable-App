package finalproject.petable.model.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientRegistrationDTO extends BaseUserRegistrationDTO {
    @NotBlank(message = "First name cannot be empty!")
    @Size(min = 2, max = 40)
    private String firstName;
    @NotBlank(message = "Last name cannot be empty!")
    @Size(min = 2, max = 40)
    private String lastName;


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

}
