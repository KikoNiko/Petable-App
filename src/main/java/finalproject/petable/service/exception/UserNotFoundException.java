package finalproject.petable.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User was not found.")
public class UserNotFoundException extends RuntimeException{
    private Long id;
    private String username;
    public UserNotFoundException(String message, String username) {
        super(message);
        this.username = username;
    }

    public UserNotFoundException(String message, Long id){
        super(message);
        this.id = id;
    }

    public UserNotFoundException(String message){
        super(message);
    }


    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
}
