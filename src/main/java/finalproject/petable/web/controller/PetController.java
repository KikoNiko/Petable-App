package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.dto.PetAddDTO;
import finalproject.petable.model.dto.PetProfileDTO;
import finalproject.petable.model.dto.PetDisplayInfoDTO;
import finalproject.petable.model.entity.Image;
import finalproject.petable.model.entity.enums.Gender;
import finalproject.petable.model.entity.enums.PetStatus;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.service.CloudinaryService;
import finalproject.petable.service.ImageService;
import finalproject.petable.service.PetService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Controller
public class PetController {
    private final PetService petService;
    private final CloudinaryService cloudinaryService;

    public PetController(PetService petService, CloudinaryService cloudinaryService) {
        this.petService = petService;
        this.cloudinaryService = cloudinaryService;
    }

    @ModelAttribute("petData")
    public PetAddDTO petData() {
        return new PetAddDTO();
    }

    @ModelAttribute("petStatuses")
    public List<String> petStatuses() {
        return Arrays.stream(PetStatus.values())
                .map(PetStatus::getLabel)
                .collect(Collectors.toList());
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
        List<PetDisplayInfoDTO> allRegisteredDogs = petService.getAllByType(PetType.DOG);
        model.addAttribute("allRegisteredDogs", allRegisteredDogs);

        List<PetDisplayInfoDTO> allRegisteredCats = petService.getAllByType(PetType.CAT);
        model.addAttribute("allRegisteredCats", allRegisteredCats);
        return "pet-registry";
    }

    @GetMapping("/pet-profile/{id}")
    public String viewPetProfile(@PathVariable Long id, Model model) {
        PetProfileDTO petProfileData = petService.getPetById(id);
        model.addAttribute("petProfileData", petProfileData);
        return "pet-profile";
    }

    @PostMapping("/pet-profile/{id}")
    public String ediPetInfo(@PathVariable Long id,
                             @ModelAttribute("petProfileData") PetProfileDTO petProfileInfo) {
        if (!Objects.equals(petProfileInfo.getId(), id)) {
            return "redirect:/pet-registry";
        }
        petService.editPetInfo(petProfileInfo);
        return "redirect:/pet-profile/{id}";
    }

    @DeleteMapping("/pet-profile/remove-pet/{id}")
    public String removePet(@PathVariable Long id) {
        petService.removePet(id);
        return "redirect:/pet-registry";
    }

    @PostMapping("/pet-profile/upload/{id}")
    public String uploadPetPhoto(@PathVariable Long id,
                                 @RequestParam("petPicture") MultipartFile multipartFile) throws IOException {
        Image image = cloudinaryService.upload(multipartFile);
        petService.assignImageToPet(image, id);
        return "redirect:/pet-profile/{id}";
    }

}
