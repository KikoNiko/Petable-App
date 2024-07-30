package com.petable.successstories.service;

import com.petable.successstories.model.dto.AddStoryDTO;
import com.petable.successstories.model.dto.StoryDTO;

public interface StoryService {

    StoryDTO addStory(AddStoryDTO addStoryDTO);

    StoryDTO getStoryById(Long id);
}
