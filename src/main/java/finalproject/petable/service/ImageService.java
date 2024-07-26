package finalproject.petable.service;

import finalproject.petable.model.entity.Image;

public interface ImageService {
    void save(Image image);
    void assignImageToUser(String imageUrl, String username);
}
