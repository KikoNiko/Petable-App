package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.*;
import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.service.*;
import jakarta.validation.Valid;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {
    private final ShelterService shelterService;
    private final ClientService clientService;
    private final MessageService messageService;
    private final UserService userService;

    public HomeController(ShelterService shelterService, ClientService clientService, MessageService messageService, UserService userService) {
        this.shelterService = shelterService;
        this.clientService = clientService;
        this.messageService = messageService;
        this.userService = userService;
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
                } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    return "redirect:/admin-page";
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
        BaseUser user = userService.getByUsername(userDetails.getUsername());
        Long clientId = user.getId();
        Map<String, List<ShowMessageDTO>> allMessages = messageService.getAllMessages(clientId);
        model.addAttribute("allMessages", allMessages);
        return "client-profile";
    }

    @GetMapping("/shelter-profile")
    public String viewShelterProfile(@AuthenticationPrincipal AppUserDetails userDetails, Model model) {
        ShelterProfileDTO shelterProfileInfo = shelterService.getInfoByUsername(userDetails.getUsername());
        model.addAttribute("shelterProfileInfo", shelterProfileInfo);
        Shelter shelter = shelterService.getByUsername(userDetails.getUsername());
        Long shelterId = shelter.getId();
        Map<String, List<ShowMessageDTO>> allMessages = messageService.getAllMessages(shelterId);
        model.addAttribute("allMessages", allMessages);
        return "shelter-profile";
    }

    @PostMapping("/shelter-profile/{shelterId}")
    public String editShelterProfileInfo(@PathVariable Long shelterId,
                                  @ModelAttribute("shelterProfileInfo") ShelterProfileDTO shelterProfileInfo) {
        String username = shelterService.findById(shelterId).getUsername();
        shelterProfileInfo.setId(shelterId);
        shelterService.editShelterInfo(shelterProfileInfo);
        if (!username.equals(shelterProfileInfo.getUsername())) {
            return "redirect:/users/login";
        }
        return "redirect:/shelter-profile";
    }

    @PostMapping("/client-profile/{clientId}")
    public String editClientProfileInfo(@PathVariable Long clientId,
                                         @ModelAttribute("clientProfileInfo") ClientProfileDTO clientProfileInfo) {
        String username = clientService.findById(clientId).getUsername();
        clientProfileInfo.setId(clientId);
        clientService.editClientInfo(clientProfileInfo);
        if (!username.equals(clientProfileInfo.getUsername())) {
            return "redirect:/users/login";
        }
        return "redirect:/client-profile";
    }


    @PostMapping("/messages/reply/{id}")
    public String replyMessage(@PathVariable Long id,
                               @Valid ReplyMessageDTO replyMessage,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("replyMessage", replyMessage);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.replyMessage", bindingResult);
            return "redirect:/messages/reply/{id}";
        }
        messageService.replyMessage(id, replyMessage);
        return "redirect:/home";
    }

    @ModelAttribute("replyMessage")
    public ReplyMessageDTO replyMessage() {
        return new ReplyMessageDTO();
    }

}
