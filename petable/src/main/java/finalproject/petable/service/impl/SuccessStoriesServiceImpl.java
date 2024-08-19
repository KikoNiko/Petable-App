package finalproject.petable.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import finalproject.petable.model.dto.stories.AddStoryDTO;
import finalproject.petable.model.dto.stories.StoryDTO;
import finalproject.petable.service.SuccessStoriesService;
import finalproject.petable.service.exception.StoryNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
public class SuccessStoriesServiceImpl implements SuccessStoriesService {
    private final RestClient restClient;

    public SuccessStoriesServiceImpl(@Qualifier("ssRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void addStory(AddStoryDTO addStoryDTO) {
        restClient
                .post()
                .uri("/add")
                .body(addStoryDTO)
                .retrieve();
    }

    @Override
    public void deleteStory(Long storyId) {
        restClient
                .delete()
                .uri("/{id}", storyId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public StoryDTO getStoryById(Long storyId) {
        return restClient
                .get()
                .uri("/{id}", storyId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange(((request, response) -> {
                    if (response.getStatusCode().is4xxClientError()) {
                        throw new StoryNotFoundException("Story not found :(");
                    } else {
                        ObjectMapper objectMapper = new ObjectMapper();
                        return objectMapper.readValue(response.getBody(), StoryDTO.class);
                    }
                }));
    }

    @Override
    public List<StoryDTO> getAllStories() {
        return restClient
                .get()
                .uri("/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public List<StoryDTO> getTopStories() {
        return restClient
                .get()
                .uri("/top")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<StoryDTO>>() {});
    }

}
