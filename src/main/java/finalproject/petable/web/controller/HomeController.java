package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.ClientProfileDTO;
import finalproject.petable.model.dto.ShelterProfileDTO;
import finalproject.petable.service.ClientService;
import finalproject.petable.service.ShelterService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ShelterService shelterService;
    private final ClientService clientService;

    public HomeController(ShelterService shelterService, ClientService clientService) {
        this.shelterService = shelterService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal AppUserDetails userDetails,
                       Model model) {
        //TODO redirect to profile page according to role
        return "index";
    }

    @GetMapping("/client-profile")
    public String viewClientHome(@AuthenticationPrincipal AppUserDetails userDetails, Model model) {
        ClientProfileDTO clientProfileInfo = clientService.getClientInfo(userDetails.getUsername());
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
