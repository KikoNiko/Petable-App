package finalproject.petable.web.controller;

import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.ShelterRegistrationDTO;
import finalproject.petable.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/register-user")
    public String viewRegisterClient() {
        return "register-user";
    }

    @ModelAttribute("clientRegistrationData")
    public ClientRegistrationDTO clientRegistrationData() {
        return new ClientRegistrationDTO();
    }

    @ModelAttribute("shelterRegistrationData")
    public ShelterRegistrationDTO shelterRegistrationData() {
        return new ShelterRegistrationDTO();
    }

    @PostMapping("/register-user")
    public String registerClient(@Valid ClientRegistrationDTO clientRegistrationData,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (!clientRegistrationData.getPassword().equals(clientRegistrationData.getConfirmPassword())){
            bindingResult.addError(
                    new FieldError(
                            "differentPasswords",
                            "confirmPassword",
                            "Passwords don't match.")
            );
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("clientRegistrationData", clientRegistrationData);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.clientRegistrationData", bindingResult);
            return "redirect:/users/register";
        }
        userService.registerClient(clientRegistrationData);
        return "redirect:/users/login";
    }

    @GetMapping("/register-shelter")
    public String viewRegisterShelter() {
        return "register-shelter";
    }

    @PostMapping("/register-shelter")
    public String registerShelter(@Valid ShelterRegistrationDTO shelterRegistrationData,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (!shelterRegistrationData.getPassword().equals(shelterRegistrationData.getConfirmPassword())){
            bindingResult.addError(
                    new FieldError(
                            "differentPasswords",
                            "confirmPassword",
                            "Passwords don't match.")
            );
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shelterRegistrationData", shelterRegistrationData);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.shelterRegistrationData", bindingResult);
            return "redirect:/users/register";
        }
        userService.registerShelter(shelterRegistrationData);
        return "redirect:/users/login";
    }
}
