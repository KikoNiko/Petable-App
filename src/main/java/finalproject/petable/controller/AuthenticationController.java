package finalproject.petable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/register")
    public String viewRegister() {
        return "register";
    }

    @GetMapping("/register-user")
    public String viewRegisterUser() {
        return "register-user";
    }

    @GetMapping("/register-shelter")
    public String viewRegisterShelter() {
        return "register-shelter";
    }

    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }
}
