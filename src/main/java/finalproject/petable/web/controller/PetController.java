package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.dto.PetRegistryDisplayInfoDTO;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.service.PetService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @ModelAttribute("petData")
    public PetAddDTO petData() {
        return new PetAddDTO();
    }

    @ModelAttribute("petStatuses")
    public PetStatus[] petStatuses() {
        return PetStatus.values();
    }

    @ModelAttribute("petTypes")
    public PetType[] petTypes() {
        return PetType.values();
    }

    @ModelAttribute("petGenders")
    public Gender[] genders() {
        return Gender.values();
    }

    @GetMapping("/add-pet")
    public String viewAddPet() {
        return "add-pet";
    }

    @PostMapping("/add-pet")
    public String addPet(@AuthenticationPrincipal AppUserDetails userDetails,
                         @Valid PetAddDTO petData,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("petData", petData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.petData", bindingResult);
            return "redirect:/add-pet";
        }
        petService.addPet(userDetails, petData);
        return "redirect:/shelter-profile";
    }

    @GetMapping("/pet-registry")
    public String viewPetRegistry(Model model) {
        List<PetRegistryDisplayInfoDTO> allRegisteredDogs = petService.getAllByType(PetType.DOG);
        model.addAttribute("allRegisteredDogs", allRegisteredDogs);

        List<PetRegistryDisplayInfoDTO> allRegisteredCats = petService.getAllByType(PetType.CAT);
        model.addAttribute("allRegisteredCats", allRegisteredCats);

        return "pet-registry";
    }



}
