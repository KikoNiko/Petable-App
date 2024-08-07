package finalproject.petable.service;

import finalproject.petable.model.entity.Image;

public interface ImageService {
    Image save(Image image);
    void assignImageToUser(String imageUrl, String username);
    Image getByName(String name);

    void delete(Long id);
}
