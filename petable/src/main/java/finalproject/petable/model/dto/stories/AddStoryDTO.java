package finalproject.petable.model.dto.stories;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AddStoryDTO {
    @NotEmpty(message = "Title cannot be empty!")
    @Size(min = 3, message = "Title should be at leas 3 characters long.")
    private String title;

    @NotEmpty(message = "Story cannot be empty!")
    @Size(min = 10, message = "Story should be at least 10 characters long.")
    private String story;

    @Size(max = 1000, message = "Url is too long!")
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
