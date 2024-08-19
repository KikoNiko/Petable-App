package com.petable.successstories.service.impl;

import com.petable.successstories.exception.StoryNotFoundException;
import com.petable.successstories.model.dto.AddStoryDTO;
import com.petable.successstories.model.dto.StoryDTO;
import com.petable.successstories.model.entity.Story;
import com.petable.successstories.repository.StoryRepository;
import com.petable.successstories.service.StoryService;
import com.petable.successstories.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;

    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public StoryDTO addStory(AddStoryDTO addStoryDTO) {
        Story story = map(addStoryDTO);
        storyRepository.save(story);
        return map(story);
    }

    @Override
    public StoryDTO getStoryById(Long id) {
        return storyRepository.findById(id).map(StoryServiceImpl::map)
                .orElseThrow(() -> new StoryNotFoundException("Story was not found :("));
    }

    @Override
    public List<StoryDTO> getAll() {
        return storyRepository.findAll()
                .stream()
                .map(StoryServiceImpl::map)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStory(Long id) {
        storyRepository.deleteById(id);
    }

    @Override
    public List<StoryDTO> getTopStories() {
        return storyRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Story::getId))
                .limit(3)
                .map(StoryServiceImpl::map)
                .collect(Collectors.toList());
    }

    private static Story map(AddStoryDTO addStoryDTO) {
        Story story = new Story();
        story.setTitle(addStoryDTO.getTitle());
        story.setStory(addStoryDTO.getStory());
        story.setImageUrl(addStoryDTO.getImageUrl());
        return story;
    }

    private static StoryDTO map(Story story) {
        return new StoryDTO(
                story.getId(),
                story.getTitle(),
                story.getStory(),
                story.getImageUrl()
        );
    }
}
