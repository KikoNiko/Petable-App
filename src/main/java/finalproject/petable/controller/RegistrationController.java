package finalproject.petable.controller;

import finalproject.petable.model.dto.ClientRegistrationDTO;
import finalproject.petable.model.dto.ShelterRegistrationDTO;
import finalproject.petable.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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
        return "register-client";
    }

    @ModelAttribute("clientRegistrationData")
    public ClientRegistrationDTO clientRegistrationData() {
        return new ClientRegistrationDTO();
    }

    @ModelAttribute("shelterRegistrationData")
    public ShelterRegistrationDTO shelterRegistrationData() {
        return new ShelterRegistrationDTO();
    }

    @PostMapping("/register-client")
    public String registerClient(ClientRegistrationDTO clientRegistrationData) {
        userService.registerClient(clientRegistrationData);
        return "redirect:/login";
    }

    @GetMapping("/register-shelter")
    public String viewRegisterShelter() {
        return "register-shelter";
    }

    @PostMapping("/register-shelter")
    public String registerShelter(ShelterRegistrationDTO shelterRegistrationData) {
        userService.registerShelter(shelterRegistrationData);
        return "redirect:/login";
    }
}
