package finalproject.petable.service;

import finalproject.petable.model.dto.stories.AddStoryDTO;
import finalproject.petable.model.dto.stories.StoryDTO;

import java.util.List;

public interface SuccessStoriesService {
    void addStory(AddStoryDTO addStoryDTO);
    void deleteStory(Long storyId);
    StoryDTO getStoryById(Long storyId);
    List<StoryDTO> getAllStories();
    List<StoryDTO> getTopStories();
}
