package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.dto.PetProfileDTO;
import finalproject.petable.model.dto.PetRegistryDisplayInfoDTO;
import finalproject.petable.model.entity.Shelter;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.repository.ShelterRepository;
import finalproject.petable.service.PetService;
import finalproject.petable.service.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
public class PetController {
    private final PetService petService;
    private final ShelterRepository shelterRepository;

    public PetController(PetService petService, ShelterRepository shelterRepository) {
        this.petService = petService;
        this.shelterRepository = shelterRepository;
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
        petService.addPet(userDetails.getUsername(), petData);
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

    @GetMapping("/pet-profile/{id}")
    public String viewPetProfile(@PathVariable Long id, Model model) {
        PetProfileDTO petProfileData = petService.getPetById(id);
        model.addAttribute("petProfileData", petProfileData);

        return "pet-profile";
    }

    @GetMapping("/shelter/my-animals")
    public String viewMyAnimals(@AuthenticationPrincipal AppUserDetails userDetails, Model model) {
        Optional<Shelter> optionalShelter = shelterRepository.findByUsername(userDetails.getUsername());
        if (optionalShelter.isEmpty()) {
            throw new UserNotFoundException("Shelter not found!");
        }
        Long shelterId = optionalShelter.get().getId();
        List<PetRegistryDisplayInfoDTO> allCatsByShelter = petService.getAllByShelterIdAndType(shelterId, PetType.CAT);
        List<PetRegistryDisplayInfoDTO> allDogsByShelter = petService.getAllByShelterIdAndType(shelterId, PetType.DOG);
        model.addAttribute("allCatsByShelter", allCatsByShelter);
        model.addAttribute("allDogsByShelter", allDogsByShelter);
        return "shelter-animal-list";
    }

}
