package finalproject.petable.web.controller;

import finalproject.petable.model.dto.stories.AddStoryDTO;
import finalproject.petable.service.SuccessStoriesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("stories")
public class SuccessStoriesController {
    private final SuccessStoriesService storiesService;

    public SuccessStoriesController(SuccessStoriesService storiesService) {
        this.storiesService = storiesService;
    }

    @GetMapping("/add")
    public String addStoryView(Model model) {
        model.addAttribute("addStoryData", new AddStoryDTO());
        return "add-story";
    }

    @PostMapping("/add")
    public String addStory(@Valid AddStoryDTO addStoryData,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addStoryData", addStoryData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addStoryData", bindingResult);
            return "redirect:/stories/add";
        }
        storiesService.addStory(addStoryData);
        return "redirect:/stories/all";
    }

    @GetMapping("/{id}")
    public String getStory(@PathVariable Long id, Model model) {
        model.addAttribute("storyData", storiesService.getStoryById(id));
        return "story-page";
    }

    @GetMapping("/all")
    public String getAllStories(Model model) {
        model.addAttribute("allStories", storiesService.getAllStories());
        return "success-stories";
    }

    @DeleteMapping("/{id}")
    public String deleteStory(@PathVariable Long id) {
        storiesService.deleteStory(id);
        return "redirect:/stories/all";
    }

}
