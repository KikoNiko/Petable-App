package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.ClientProfileDTO;
import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.dto.ShelterProfileDTO;
import finalproject.petable.service.ClientService;
import finalproject.petable.service.ShelterService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Set;

@Controller
public class HomeController {
    private final ShelterService shelterService;
    private final ClientService clientService;

    public HomeController(ShelterService shelterService, ClientService clientService) {
        this.shelterService = shelterService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal AppUserDetails userDetails) {
        if (userDetails != null) {
            Collection<GrantedAuthority> authorities = userDetails.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_CLIENT")) {
                    return "redirect:/client-profile";
                } else if (authority.getAuthority().equals("ROLE_SHELTER")) {
                    return "redirect:/shelter-profile";
                }
            }
        }
        return "index";
    }

    @GetMapping("/client-profile")
    public String viewClientHome(@AuthenticationPrincipal AppUserDetails userDetails, Model model) {
        ClientProfileDTO clientProfileInfo = clientService.getClientInfo(userDetails.getUsername());
        Set<PetDisplayInfoDTO> favoritePets = clientService.getAllFavoritePets(userDetails.getUsername());
        model.addAttribute("favoritePets", favoritePets);
        model.addAttribute("clientProfileInfo", clientProfileInfo);
        return "client-profile";
    }

    @GetMapping("/shelter-profile")
    public String viewShelterProfile(@AuthenticationPrincipal AppUserDetails userDetails, Model model) {
        ShelterProfileDTO shelterProfileInfo = shelterService.getInfoByUsername(userDetails.getUsername());
        model.addAttribute("shelterProfileInfo", shelterProfileInfo);
        return "shelter-profile";
    }

}
