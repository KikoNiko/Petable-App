package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails,
                       Model model) {

        return "index";
    }

    @GetMapping("/client-profile")
    public String viewHome() {
        return "client-profile";
    }

    @GetMapping("/shelter-profile")
    public String viewShelterProfile() {
        return "shelter-profile";
    }

    @GetMapping("/pet-registry")
    public String viewPetRegistry() {
        return "pet-registry";
    }

    @GetMapping("/shelters/info")
    public String viewSheltersPage() {
        return "shelters";
    }

    @GetMapping("/shelters/contact-form")
    public String viewContactPage() {
        return "contact-form";
    }

    @GetMapping("/pet-profile")
    public String viewPetProfile() {
        return "pet-profile";
    }

    @GetMapping("/member")
    public String viewMemberPage() {
        return "member";
    }
}
