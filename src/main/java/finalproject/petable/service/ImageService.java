package finalproject.petable.service;

import finalproject.petable.model.entity.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    List<Image> getAllImages();
    Optional<Image> findImageById(Long id);
    void save(Image image);
    void delete(Long id);
    boolean exists(Long id);
}
