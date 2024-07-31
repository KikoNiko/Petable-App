package finalproject.petable.web.controller;

import finalproject.petable.model.dto.messages.SendMessageDTO;
import finalproject.petable.model.entity.enums.PetType;
import finalproject.petable.service.MessageService;
import finalproject.petable.service.ShelterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MessageController {

    private final MessageService messageService;
    private final ShelterService shelterService;

    public MessageController(MessageService messageService, ShelterService shelterService) {
        this.messageService = messageService;
        this.shelterService = shelterService;
    }

    @ModelAttribute("messageData")
    public SendMessageDTO messageData() {
        return new SendMessageDTO();
    }

    @ModelAttribute("sheltersNames")
    public List<String> getAllSheltersNames() {
        return shelterService.getAllSheltersNames();
    }

    @ModelAttribute("petTypes")
    public PetType[] petTypes() {
        return PetType.values();
    }

    @GetMapping("/contact-form")
    public String viewContactPage() {
        return "contact-form";
    }

    @PostMapping("/contact-form")
    public String sendMessage(@Valid SendMessageDTO messageData,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("messageData", messageData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageData", bindingResult);
            return "redirect:/contact-form";
        }
        messageService.sendMessage(messageData);
        return "redirect:/home";
    }

    @DeleteMapping("/messages/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return "redirect:/home";
    }
}
