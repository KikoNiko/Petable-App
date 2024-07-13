package finalproject.petable.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PetNotFoundException extends RuntimeException{

    public PetNotFoundException(String message) {
        super(message);
    }

}
