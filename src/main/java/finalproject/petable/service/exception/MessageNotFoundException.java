package finalproject.petable.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Message was not found.")
public class MessageNotFoundException extends RuntimeException{

    private final Object id;
    public MessageNotFoundException(String message, Object id) {
        super(message);
        this.id = id;
    }

    public Object getId() {
        return id;
    }
}
