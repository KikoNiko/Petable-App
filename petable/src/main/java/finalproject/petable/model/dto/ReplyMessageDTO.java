package finalproject.petable.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class ReplyMessageDTO {
    @NotEmpty(message = "Message body cannot be empty!")
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
