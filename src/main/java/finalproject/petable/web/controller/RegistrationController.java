package finalproject.petable.web.controller;

import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.BaseUserRegistrationDTO;
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

    @PostMapping("/register/client")
    public String registerClient(@Valid ClientRegistrationDTO registrationData,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        validateCommonData(registrationData, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("clientRegistrationData", registrationData);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.clientRegistrationData", bindingResult);
            return "redirect:/users/register/client";
        }
        userService.registerClient(registrationData);
        return "redirect:/users/login";
    }

    @PostMapping("/register/shelter")
    public String registerShelter(@Valid ShelterRegistrationDTO registrationData,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        validateCommonData(registrationData, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shelterRegistrationData", registrationData);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.shelterRegistrationData", bindingResult);
            return "redirect:/users/register/shelter";
        }
        userService.registerShelter(registrationData);
        return "redirect:/users/login";
    }

    private void validateCommonData(BaseUserRegistrationDTO registrationData, BindingResult bindingResult) {
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
