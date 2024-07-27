package finalproject.petable.service;

import finalproject.petable.model.entity.Image;

public interface ImageService {
    Image save(Image image);
    void assignImageToUser(String imageUrl, String username);
    void assignImageToPet(Image image, Long id);
    Image getByName(String name);
}
