package finalproject.petable.model.dto;

import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.validation.ValidPetName;
import finalproject.petable.validation.ValidUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SendMessageDTO {
    @ValidUsername
    private String senderUsername;
    @Email
    private String senderEmail;
    @ValidPetName
    private String petName;
    @NotNull(message = "Pet type cannot be null.")
    private PetType petType;
    @ValidUsername
    private String receiverUsername;
    @NotEmpty
    @Size(min = 10, message = "Size of the message must be at least 10 characters.")
    private String body;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
