package finalproject.petable.web.controller;

import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.RegistrationDTO;
import finalproject.petable.model.dto.ShelterRegistrationDTO;
import finalproject.petable.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String viewRegister() {
        return "register";
    }

    @GetMapping("/register/client")
    public String viewRegisterClient() {
        return "register-user";
    }

    @GetMapping("/register/shelter")
    public String viewRegisterShelter() {
        return "register-shelter";
    }

    @PostMapping("/register/{userType}")
    public String registerUser(@PathVariable String userType,
                                 @Valid ClientRegistrationDTO clientRegistrationDTO,
                                 @Valid ShelterRegistrationDTO shelterRegistrationDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        RegistrationDTO registrationData = null;

        if (userType.equals("client")) {
            registrationData = clientRegistrationDTO;
        } else if (userType.equals("shelter")) {
            registrationData = shelterRegistrationDTO;
        } else {
            return "redirect:/users/register{userType}";
        }

        if (!registrationData.getPassword().equals(registrationData.getConfirmPassword())){
            bindingResult.addError(
                    new FieldError(
                            "differentPasswords",
                            "confirmPassword",
                            "Passwords don't match.")
            );
        }

        if (userService.isUsernameRegistered(registrationData.getUsername())){
            bindingResult.addError(
                    new FieldError(
                            "usernameTaken",
                            "username",
                            "Username is already taken!")
            );
        }
        if (userService.isEmailRegistered(registrationData.getEmail())){
            bindingResult.addError(
                    new FieldError(
                            "emailTaken",
                            "email",
                            "User with this email is already registered!")
            );
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("clientRegistrationData", registrationData);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.clientRegistrationData", bindingResult);
            return "redirect:/users/register/{userType}";
        }
        userService.registerUser(registrationData, userType);
        return "redirect:/users/login";
    }

    @ModelAttribute("clientRegistrationData")
    public ClientRegistrationDTO clientRegistrationData() {
        return new ClientRegistrationDTO();
    }

    @ModelAttribute("shelterRegistrationData")
    public ShelterRegistrationDTO shelterRegistrationData() {
        return new ShelterRegistrationDTO();
    }
}
