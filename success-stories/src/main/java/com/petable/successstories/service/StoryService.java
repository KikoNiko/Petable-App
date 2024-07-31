package com.petable.successstories.service;

import com.petable.successstories.model.dto.AddStoryDTO;
import com.petable.successstories.model.dto.StoryDTO;

import java.util.List;

public interface StoryService {

    StoryDTO addStory(AddStoryDTO addStoryDTO);

    StoryDTO getStoryById(Long id);
    List<StoryDTO> getAll();
    void deleteStory(Long id);

    List<StoryDTO> getTopStories();

}
