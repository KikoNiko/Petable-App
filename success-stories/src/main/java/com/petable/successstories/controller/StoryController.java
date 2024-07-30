package com.petable.successstories.controller;

import com.petable.successstories.model.dto.AddStoryDTO;
import com.petable.successstories.model.dto.StoryDTO;
import com.petable.successstories.service.StoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/stories")
public class StoryController {
    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping("/add")
    public ResponseEntity<StoryDTO> addStory(
            @RequestBody AddStoryDTO addStoryDTO) {
        StoryDTO storyDTO = storyService.addStory(addStoryDTO);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(storyDTO.id())
                        .toUri()
        ).body(storyDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoryDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok(storyService.getStoryById(id));
    }

}
