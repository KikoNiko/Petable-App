package finalproject.petable.web.controller;

import finalproject.petable.model.dto.ShelterDisplayInfoDTO;
import finalproject.petable.service.ShelterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shelters")
public class ShelterController {

    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/info")
    public String viewSheltersPage(Model model) {
        List<ShelterDisplayInfoDTO> allSheltersInfo = shelterService.getAllSheltersInfo();
        model.addAttribute("allSheltersInfo", allSheltersInfo);
        return "shelters-info";
    }

    @GetMapping("/contact-form")
    public String viewContactPage() {
        return "contact-form";
    }


}
