package finalproject.petable.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(PetNotFoundException.class)
    public ModelAndView handlePetNotFound(PetNotFoundException pnfe) {
        ModelAndView modelAndView = new ModelAndView("pet-not-found");
        modelAndView.addObject("petId", pnfe.getId());

        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFound(UserNotFoundException unfe) {
        ModelAndView modelAndView = new ModelAndView("user-not-found");
        modelAndView.addObject("userId", unfe.getId());

        return modelAndView;
    }
}
