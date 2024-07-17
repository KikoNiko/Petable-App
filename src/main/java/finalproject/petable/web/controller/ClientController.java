package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.service.ClientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/pet-profile/add-to-favorites/{id}")
    public String addPetToFavorites(@PathVariable Long id,
                                    @AuthenticationPrincipal AppUserDetails userDetails) {
        clientService.addPetToFavorites(userDetails.getUsername(), id);
        return "redirect:/client-profile";
    }

    @DeleteMapping("/client-profile/remove-pet/{id}")
    public String removePetFromFavorites(@PathVariable Long id,
                                         @AuthenticationPrincipal AppUserDetails userDetails) {
        clientService.removePetFromFavorites(userDetails.getUsername(), id);
        return "redirect:/client-profile";
    }
}
