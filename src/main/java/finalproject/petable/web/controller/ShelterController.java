package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.dto.ShelterDisplayInfoDTO;
import finalproject.petable.model.dto.ShelterProfileDTO;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.service.PetService;
import finalproject.petable.service.ShelterService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shelters")
public class ShelterController {

    private final ShelterService shelterService;
    private final PetService petService;

    public ShelterController(ShelterService shelterService, PetService petService) {
        this.shelterService = shelterService;
        this.petService = petService;
    }

    @GetMapping("/info")
    public String viewSheltersPage(Model model) {
        List<ShelterDisplayInfoDTO> allSheltersInfo = shelterService.getAllSheltersInfo();
        model.addAttribute("allSheltersInfo", allSheltersInfo);
        return "shelters-info";
    }

    @GetMapping("/shelter/my-animals")
    public String viewMyAnimals(@AuthenticationPrincipal AppUserDetails userDetails,
                                Model model) {
        Shelter shelter = shelterService.getByUsername(userDetails.getUsername());
        List<PetDisplayInfoDTO> allCatsByShelter = petService.getAllByShelterIdAndType(shelter.getId(), PetType.CAT);
        List<PetDisplayInfoDTO> allDogsByShelter = petService.getAllByShelterIdAndType(shelter.getId(), PetType.DOG);
        model.addAttribute("allCatsByShelter", allCatsByShelter);
        model.addAttribute("allDogsByShelter", allDogsByShelter);
        return "shelter-animal-list";
    }

}
