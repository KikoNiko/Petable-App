package com.petable.successstories.web;

import com.jayway.jsonpath.JsonPath;
import com.petable.successstories.model.entity.Story;
import com.petable.successstories.repository.StoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StoryControllerIT {
    private static final String TEST_IMAGE_URL = "https://www.shutterstock.com/image-photo/pure-youth-crazy-english-cocker-600nw-1424153078.jpg";

    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        storyRepository.deleteAll();
    }

    @Test
    void testAddStory() throws Exception {
        MvcResult result = mockMvc.perform(post("/stories/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                  {
                    "title": "Test Title",
                    "story": "This is a test story",
                    "imageUrl": "https://www.shutterstock.com/image-photo/pure-youth-crazy-english-cocker-600nw-1424153078.jpg"
                  }
                """)
                ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String body = result.getResponse().getContentAsString();

        int id = JsonPath.read(body, "$.id");

        Optional<Story> optAddedStory = storyRepository.findById((long) id);

        Assertions.assertTrue(optAddedStory.isPresent());

        Story addedStory = optAddedStory.get();

        Assertions.assertEquals("Test Title", addedStory.getTitle());
        Assertions.assertEquals("This is a test story", addedStory.getStory());
        Assertions.assertEquals(TEST_IMAGE_URL, addedStory.getImageUrl());

    }

    @Test
    public void testStoryNotFound() throws Exception {
        mockMvc
                .perform(get("/stories/{id}", "1000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteStory() throws Exception {
        Story actualStory = createTestStory();

        mockMvc.perform(delete("/stories/{id}", actualStory.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        Assertions.assertTrue(
                storyRepository.findById(actualStory.getId()).isEmpty()
        );
    }

    private Story createTestStory() {
        Story story = new Story();
        story.setId(1L);
        story.setTitle("Title_Test");
        story.setStory("This is a test story");
        story.setImageUrl(TEST_IMAGE_URL);
        return story;
    }
}
