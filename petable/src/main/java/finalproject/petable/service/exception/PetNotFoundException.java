package finalproject.petable.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pet was not found.")
public class PetNotFoundException extends RuntimeException{
    private final Object id;

    public PetNotFoundException(String message, Object id) {
        super(message);
        this.id = id;
    }
    public Object getId() {
        return id;
    }
}
